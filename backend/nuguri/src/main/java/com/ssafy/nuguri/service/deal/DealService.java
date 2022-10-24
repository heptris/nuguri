package com.ssafy.nuguri.service.deal;

import com.ssafy.nuguri.domain.baseaddress.BaseAddress;
import com.ssafy.nuguri.domain.category.Category;
import com.ssafy.nuguri.domain.deal.Deal;
import com.ssafy.nuguri.domain.member.Member;
import com.ssafy.nuguri.domain.s3.AwsS3;
import com.ssafy.nuguri.dto.deal.DealDetailDto;
import com.ssafy.nuguri.dto.deal.DealListDto;
import com.ssafy.nuguri.dto.deal.DealLoginDetailDto;
import com.ssafy.nuguri.dto.deal.DealRegistRequestDto;
import com.ssafy.nuguri.exception.ex.CustomException;
import com.ssafy.nuguri.repository.baseaddress.BaseaddressRepository;
import com.ssafy.nuguri.repository.category.CategoryRepository;
import com.ssafy.nuguri.repository.deal.DealRepository;
import com.ssafy.nuguri.repository.member.MemberRepository;
import com.ssafy.nuguri.service.s3.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.ssafy.nuguri.exception.ex.ErrorCode.*;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class DealService {

    private final DealRepository dealRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;
    private final BaseaddressRepository baseaddressRepository;
    private final AwsS3Service awsS3Service;

    // 중고거래 등록
    @Transactional
    public void dealRegist(Long memberId, DealRegistRequestDto dealRegistRequestDto, MultipartFile dealImage){

        Deal deal = dealRegistRequestDto.toDeal();

        Member member = memberRepository.findById(memberId).orElseThrow(()->new CustomException(MEMBER_NOT_FOUND));

        Category category = categoryRepository.findById(dealRegistRequestDto.getCategoryId())
                .orElseThrow(()->new CustomException(CATEGORY_NOT_FOUND));
        // 멤버의 정해진 동네로 자동 등록
        BaseAddress baseAddress = baseaddressRepository.findById(member.getBaseAddress().getId())
                .orElseThrow(()->new CustomException(BASEADDRESS_NOT_FOUND));

        // 중고거래 이미지
        AwsS3 awsS3 = new AwsS3();
        try {
            awsS3 = awsS3Service.upload(dealImage, "dealImage");
        }catch (IOException e){
            System.out.println(e);
        }
        String dealImageUrl = awsS3.getPath();

        deal.registDeal(member, category, baseAddress, dealImageUrl);
        dealRepository.save(deal);
    }

    public List<DealListDto> findLocalCategoryDealList(Long localId, Long categoryId){
        return dealRepository.findLocalCategoryDealList(localId, categoryId);
    }

    /*
        비로그인시 중고거래 detail
     */
    public DealDetailDto findDealDetail(Long dealId){
        // Exception 처리 ?? -> Optional을 이용해서
        return dealRepository.dealDetail(dealId).orElseThrow(() -> new CustomException(DEAL_NOT_FOUND));
    }

    /*
        로그인시 중고거래 detail
     */
    public DealLoginDetailDto findLoginDealDetail(Long memberId, Long dealId){
//        long startTime = System.currentTimeMillis();

        DealDetailDto dealDetailDto = dealRepository.dealDetail(dealId).orElseThrow(()->new CustomException(DEAL_NOT_FOUND));
        boolean isDealFavorite = dealRepository.findIsDealFavorite(memberId, dealId);

//        long stopTime = System.currentTimeMillis();
//
//        long elapsedTime = stopTime - startTime;
//        System.out.println(elapsedTime);

        return DealLoginDetailDto.builder()
                .dealId(dealDetailDto.getDealId())
                .title(dealDetailDto.getTitle())
                .description(dealDetailDto.getDescription())
                .price(dealDetailDto.getPrice())
                .hit(dealDetailDto.getHit())
                .isDeal(dealDetailDto.isDeal())
                .dealImage(dealDetailDto.getDealImage())
                .dong(dealDetailDto.getDong())
                .isFavorite(isDealFavorite)
                .sellerId(dealDetailDto.getSellerId())
                .build();
    }


}
