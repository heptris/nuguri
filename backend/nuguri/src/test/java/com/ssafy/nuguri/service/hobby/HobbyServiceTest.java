package com.ssafy.nuguri.service.hobby;

import com.ssafy.nuguri.domain.baseaddress.BaseAddress;
import com.ssafy.nuguri.domain.category.Category;
import com.ssafy.nuguri.domain.hobby.ApproveStatus;
import com.ssafy.nuguri.domain.hobby.Hobby;
import com.ssafy.nuguri.domain.hobby.HobbyHistory;
import com.ssafy.nuguri.domain.member.Member;
import com.ssafy.nuguri.domain.s3.AwsS3;
import com.ssafy.nuguri.dto.hobby.HobbyCreateRequestDto;
import com.ssafy.nuguri.dto.hobby.HobbyDto;
import com.ssafy.nuguri.exception.ex.CustomException;
import com.ssafy.nuguri.repository.baseaddress.BaseaddressRepository;
import com.ssafy.nuguri.repository.category.CategoryRepository;
import com.ssafy.nuguri.repository.hobby.HobbyHistoryRepository;
import com.ssafy.nuguri.repository.hobby.HobbyRepository;
import com.ssafy.nuguri.util.SecurityUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.ssafy.nuguri.exception.ex.ErrorCode.BASEADDRESS_NOT_FOUND;
import static com.ssafy.nuguri.exception.ex.ErrorCode.CATEGORY_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.*;
@Commit
@SpringBootTest
@Transactional
@Sql("classpath:tableInit.sql")
class HobbyServiceTest {
    @Autowired
    HobbyService hobbyService;

    @Autowired
    HobbyRepository hobbyRepository;
    @Autowired
    HobbyHistoryRepository hobbyHistoryRepository;
    @Autowired
    BaseaddressRepository baseaddressRepository;
    @Autowired
    CategoryRepository categoryRepository;


    // SecurityUtil.getCurrentMemberId(); 대신
    public Long createHobbyForTest(HobbyCreateRequestDto hobbyCreateRequestDto){
        BaseAddress baseAddress = baseaddressRepository.findById(hobbyCreateRequestDto.getLocalId()).orElseThrow(()->new CustomException(BASEADDRESS_NOT_FOUND));
        Category category = categoryRepository.findById(hobbyCreateRequestDto.getCategoryId()).orElseThrow(()->new CustomException(CATEGORY_NOT_FOUND));

        Hobby hobbyEntity = Hobby.builder()
                .baseAddress(baseAddress)
                .category(category)
                .title(hobbyCreateRequestDto.getTitle())
                .content(hobbyCreateRequestDto.getContent())
                .endDate(hobbyCreateRequestDto.getEndDate())
                .meetingPlace(hobbyCreateRequestDto.getMeetingPlace())
                .isClosed(false)
                .curNum(1)
                .maxNum(hobbyCreateRequestDto.getMaxNum())
                .fee(hobbyCreateRequestDto.getFee())
                .ageLimit(hobbyCreateRequestDto.getAgeLimit())
                .sexLimit(hobbyCreateRequestDto.getSexLimit())
                .hobbyImage("")
                .build();

        // hobby를 생성하면서 hobbyHistory도 같이 생성
        Hobby hobby = hobbyRepository.save(hobbyEntity);
        Long memberId = 1L;

        Member member = new Member();
        member.changeMemberId(memberId);

        HobbyHistory hobbyHistoryEntity = HobbyHistory.builder()
                .member(member)
                .hobby(hobby)
                .isPromoter(true)
                .approveStatus(ApproveStatus.APPROVE)
                .build();

        hobbyHistoryRepository.save(hobbyHistoryEntity);

        return hobbyEntity.getId();
    }

    @Test
    @BeforeEach
    public void before(){
        System.out.println("======취미방 생성 시작======");
        HobbyCreateRequestDto hobbyDto2 = HobbyCreateRequestDto.builder()
                .localId(20L)
                .categoryId(3L)
                .title("취미방 1")
                .content("놀자")
                .endDate(LocalDateTime.now())
                .meetingPlace("멀티캠퍼스")
                .maxNum(10)
                .fee(0)
                .ageLimit(100)
                .sexLimit((char)1)
                .build();

        HobbyCreateRequestDto hobbyDto3 = HobbyCreateRequestDto.builder()
                .localId(10L)
                .categoryId(2L)
                .title("취미방 1")
                .content("놀자")
                .endDate(LocalDateTime.now())
                .meetingPlace("멀티캠퍼스")
                .maxNum(10)
                .fee(0)
                .ageLimit(100)
                .sexLimit((char)1)
                .build();


        createHobbyForTest(hobbyDto2);
        createHobbyForTest(hobbyDto3);
    }

    @Test
    public void 지역으로_취미방_찾기(){
        List<HobbyDto> result = hobbyService.findLocalCategoryHobbyList(10L,null);
        for (HobbyDto h:result
        ) {
            System.out.println("지역이 10L인 데이터: "+h);
        }
    }

    @Test
    public void 지역과_카테고리로_취미방_찾기(){
        List<HobbyDto> result2 = hobbyService.findLocalCategoryHobbyList(10L, 2L);
        for (HobbyDto h:result2
        ) {
            System.out.println("지역이 10L, 카테고리가 30L인 데이터: "+h);
        }
    }

    @Test
    public void 취미_상세조회(){
        HobbyCreateRequestDto hobbyDto = HobbyCreateRequestDto.builder()
                .localId(10L)
                .categoryId(3L)
                .title("취미방 1")
                .content("놀자")
                .endDate(LocalDateTime.now())
                .meetingPlace("멀티캠퍼스")
                .maxNum(10)
                .fee(0)
                .ageLimit(100)
                .sexLimit((char)1)
                .build();

        Long hobbyId = createHobbyForTest(hobbyDto);;
        HobbyDto result3 = hobbyService.findHobbyDetail(hobbyId);
        System.out.println("아이디가 "+hobbyId+"인 취미방 상세조회: "+result3);

    }

    @Test
    public void test() {
        Map<Long, String> map = new ConcurrentHashMap<>();
        map.put(1L, "A");
        map.put(2L, "B");

        System.out.println(map.get(3L));
    }
}