package com.ssafy.nuguri.service.group;

import com.ssafy.nuguri.domain.baseaddress.BaseAddress;
import com.ssafy.nuguri.domain.category.Category;
import com.ssafy.nuguri.domain.group.GroupPurchase;
import com.ssafy.nuguri.domain.member.Member;
import com.ssafy.nuguri.domain.s3.AwsS3;
import com.ssafy.nuguri.dto.group.GroupPurchaseListDto;
import com.ssafy.nuguri.dto.group.GroupPurchaseRegistRequestDto;
import com.ssafy.nuguri.exception.ex.CustomException;
import com.ssafy.nuguri.exception.ex.ErrorCode;
import com.ssafy.nuguri.repository.baseaddress.BaseaddressRepository;
import com.ssafy.nuguri.repository.category.CategoryRepository;
import com.ssafy.nuguri.repository.deal.DealFavoriteRepository;
import com.ssafy.nuguri.repository.group.GroupPurchaseRepository;
import com.ssafy.nuguri.repository.member.MemberRepository;
import com.ssafy.nuguri.service.s3.AwsS3Service;
import com.ssafy.nuguri.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GroupPurchaseService {

    private final GroupPurchaseRepository groupPurchaseRepository;

    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;
    private final BaseaddressRepository baseaddressRepository;
    private final AwsS3Service awsS3Service;

    public List<GroupPurchaseListDto> findGroupByLocalAndCategory(Long localId, Long categoryId){
        return groupPurchaseRepository.findGroupByLocalAndCategory(localId, categoryId);
    }

    @Transactional
    public void registGroupPurchase(GroupPurchaseRegistRequestDto groupPurchaseRegistRequestDto, MultipartFile groupImage){
        GroupPurchase groupPurchase = groupPurchaseRegistRequestDto.toGroupPurchase();
        Long memberId = SecurityUtil.getCurrentMemberId();
        Member member = memberRepository.findById(memberId).orElseThrow(()-> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        Category category = categoryRepository.findById(groupPurchaseRegistRequestDto.getCategoryId())
                .orElseThrow(()-> new CustomException(ErrorCode.CATEGORY_NOT_FOUND));


        BaseAddress baseAddress = baseaddressRepository.findById(member.getBaseAddress().getId())
                .orElseThrow(()-> new CustomException(ErrorCode.BASEADDRESS_NOT_FOUND));

        // 중고거래 이미지
        AwsS3 awsS3 = new AwsS3();
        try {
            awsS3 = awsS3Service.upload(groupImage, "groupImage");
        }catch (IOException e){
            System.out.println(e);
        }
        String groupImageUrl = awsS3.getPath();

        groupPurchase.registGroupPurchase(member, category, baseAddress, groupImageUrl);
        groupPurchaseRepository.save(groupPurchase);
    }


}
