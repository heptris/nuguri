package com.ssafy.nuguri.service.hobby;

import com.ssafy.nuguri.domain.baseaddress.BaseAddress;
import com.ssafy.nuguri.domain.category.Category;
import com.ssafy.nuguri.domain.hobby.ApproveStatus;
import com.ssafy.nuguri.domain.hobby.Hobby;
import com.ssafy.nuguri.domain.hobby.HobbyHistory;
import com.ssafy.nuguri.domain.member.Member;
import com.ssafy.nuguri.domain.s3.AwsS3;
import com.ssafy.nuguri.dto.hobby.HobbyDto;
import com.ssafy.nuguri.exception.ex.CustomException;
import com.ssafy.nuguri.repository.baseaddress.BaseaddressRepository;
import com.ssafy.nuguri.repository.category.CategoryRepository;
import com.ssafy.nuguri.repository.hobby.HobbyHistoryRepository;
import com.ssafy.nuguri.repository.hobby.HobbyRepository;
import com.ssafy.nuguri.repository.hobby.HobbyRepositoryImpl;
import com.ssafy.nuguri.repository.member.MemberRepository;
import com.ssafy.nuguri.service.s3.AwsS3Service;
import com.ssafy.nuguri.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.ssafy.nuguri.exception.ex.ErrorCode.BASEADDRESS_NOT_FOUND;
import static com.ssafy.nuguri.exception.ex.ErrorCode.CATEGORY_NOT_FOUND;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HobbyService {

    private final HobbyRepository hobbyRepository;
    private final HobbyHistoryRepository hobbyHistoryRepository;
    private final MemberRepository memberRepository;
    private final BaseaddressRepository baseaddressRepository;
    private final CategoryRepository categoryRepository;
    private final AwsS3Service awsS3Service;

    @Transactional
    public List<HobbyDto> findLocalHobbyList(Long regionId){ // 지역으로 취미방 찾기
        return hobbyRepository.findByRegion(regionId);
    }

    @Transactional
    public List<HobbyDto> findLocalCategoryHobbyList(Long regionId, Long categoryId){ // 지역과 카테고리로 취미방 찾기
        return hobbyRepository.findByRegionAndCategory(regionId,categoryId);
    }

    @Transactional
    public HobbyDto findHobbyDetail(Long hobbyId){ // 취미방 상세보기
        return hobbyRepository.hobbyDetail(hobbyId);
    }

    @Transactional
    public void createHobby(HobbyDto hobbyDto, MultipartFile hobbyImage){ // 취미방 생성
        BaseAddress baseAddress = baseaddressRepository.findById(hobbyDto.getLocalId()).orElseThrow(()->new CustomException(BASEADDRESS_NOT_FOUND));
        Category category = categoryRepository.findById(hobbyDto.getCategoryId()).orElseThrow(()->new CustomException(CATEGORY_NOT_FOUND));
        // 중고거래 이미지
        AwsS3 awsS3 = new AwsS3();
        try {
            awsS3 = awsS3Service.upload(hobbyImage, "hobbyImage");
        }catch (IOException e){
            System.out.println(e);
        }
        String dealImageUrl = awsS3.getPath();

        Hobby hobbyEntity = Hobby.builder()
                .baseAddress(baseAddress)
                .category(category)
                .title(hobbyDto.getTitle())
                .content(hobbyDto.getContent())
                .endDate(hobbyDto.getEndDate())
                .meetingPlace(hobbyDto.getMeetingPlace())
                .isClosed(hobbyDto.isClosed())
                .curNum(hobbyDto.getCurNum())
                .maxNum(hobbyDto.getMaxNum())
                .fee(hobbyDto.getFee())
                .ageLimit(hobbyDto.getAgeLimit())
                .sexLimit(hobbyDto.getSexLimit())
                .hobbyImage(hobbyDto.getHobbyImage())
                .build();
        // hobbyhistorySerivice를 생성
        Hobby hobby = hobbyRepository.save(hobbyEntity);
        Long memberId = SecurityUtil.getCurrentMemberId();

        // 쿼리를 한번 덜 쓰는 방법, db에는 어차피 id만 있어서
        Member member = new Member();
        member.changeMemberId(memberId);

        HobbyHistory hobbyHistoryEntity = HobbyHistory.builder()
                .member(member)
                .hobby(hobby)
                .isPromoter(true)
                .approveStatus(ApproveStatus.APPROVE)
                .build();
        hobbyHistoryRepository.save(hobbyHistoryEntity);
    }


}
