package com.ssafy.nuguri.service.deal;

import com.ssafy.nuguri.domain.baseaddress.BaseAddress;
import com.ssafy.nuguri.domain.category.Category;
import com.ssafy.nuguri.domain.deal.Deal;
import com.ssafy.nuguri.domain.deal.DealFavorite;
import com.ssafy.nuguri.domain.member.Member;
import com.ssafy.nuguri.domain.s3.AwsS3;
import com.ssafy.nuguri.dto.auth.MemberJoinDto;
import com.ssafy.nuguri.dto.deal.*;
import com.ssafy.nuguri.exception.ex.CustomException;
import com.ssafy.nuguri.repository.baseaddress.BaseaddressRepository;
import com.ssafy.nuguri.repository.category.CategoryRepository;
import com.ssafy.nuguri.repository.deal.DealFavoriteRepository;
import com.ssafy.nuguri.repository.deal.DealRepository;
import com.ssafy.nuguri.repository.member.MemberRepository;
import com.ssafy.nuguri.service.s3.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static com.ssafy.nuguri.exception.ex.ErrorCode.*;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class DealService {

    private final DealRepository dealRepository;
    private final MemberRepository memberRepository;
    private final DealFavoriteRepository dealFavoriteRepository;
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

    @Transactional
    public void updateDealDetail(DealUpdateDto dealUpdateDto, MultipartFile dealImage){
        Deal deal = dealRepository.findById(dealUpdateDto.getDealId())
                .orElseThrow(() -> new CustomException(DEAL_NOT_FOUND));

        // 중고거래 이미지
        AwsS3 awsS3 = new AwsS3();
        try {
            awsS3 = awsS3Service.upload(dealImage, "dealImage");
        }catch (IOException e){
            System.out.println(e);
        }
        String dealImageUrl = awsS3.getPath();

        deal.updateDeal(dealUpdateDto.getTitle(), dealUpdateDto.getDescription(), dealUpdateDto.getPrice(), dealImageUrl);
    }

    public Page<DealListDto> findLocalCategoryDealList(DealListRequestCondition condition, Pageable pageable){
        if(condition.getLocalId() != null) {
            return dealRepository.findLocalCategoryDealList(condition, pageable);
        }else {
            return dealRepository.findDealListIfLocalIdNull(condition.getCategoryId(), pageable);
        }
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

    @Transactional
    public void createOrModifyDealFavorite(Long memberId, Long dealId){
        DealFavorite dealFavorite = dealFavoriteRepository.findByMemberIdAndDealId(memberId, dealId);
        if(dealFavorite == null) {
            Member member = memberRepository.findById(memberId).orElseThrow(()-> new CustomException(MEMBER_NOT_FOUND));
            Deal deal = dealRepository.findById(dealId).orElseThrow(()-> new CustomException(DEAL_NOT_FOUND));
            DealFavorite newDealFavorite = DealFavorite.builder()
                    .member(member)
                    .deal(deal)
                    .isFavorite(true)
                    .build();
            dealFavoriteRepository.save(newDealFavorite);
        }else{
            dealFavorite.changeFavorite();
        }
    }

    private final static String VIEWCOOKIENAME = "alreadyViewCookie";

    @Transactional
    public void increaseHit(Long dealId, HttpServletRequest request, HttpServletResponse response){
        Deal deal = dealRepository.findById(dealId).orElseThrow(()-> new CustomException(DEAL_NOT_FOUND));
        Cookie[] cookies = request.getCookies();
        boolean checkCookie = false;
        if(cookies != null){
            for (Cookie cookie : cookies)
            {
                // 이미 조회를 한 경우 체크
                if (cookie.getName().equals(VIEWCOOKIENAME+dealId)) checkCookie = true;

            }
            if(!checkCookie){
                Cookie newCookie = createCookieForForNotOverlap(dealId);
                response.addCookie(newCookie);
                deal.increaseHit();
            }
        } else {
            Cookie newCookie = createCookieForForNotOverlap(dealId);
            response.addCookie(newCookie);
            deal.increaseHit();
        }
    }

    /*
     * 조회수 중복 방지를 위한 쿠키 생성 메소드
     * @param cookie
     * @return
     * */
    private Cookie createCookieForForNotOverlap(Long dealId) {
        Cookie cookie = new Cookie(VIEWCOOKIENAME+dealId, String.valueOf(dealId));
        cookie.setComment("조회수 중복 증가 방지 쿠키");	// 쿠키 용도 설명 기재
        cookie.setMaxAge(getRemainSecondForTommorow()); 	// 하루를 준다.
        cookie.setHttpOnly(true);				// 서버에서만 조작 가능
        return cookie;
    }

    // 다음 날 정각까지 남은 시간(초)
    private int getRemainSecondForTommorow() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tommorow = LocalDateTime.now().plusDays(1L).truncatedTo(ChronoUnit.DAYS);
        return (int) now.until(tommorow, ChronoUnit.SECONDS);
    }

}
