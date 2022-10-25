package com.ssafy.nuguri.service.deal;

import com.ssafy.nuguri.domain.deal.DealFavorite;
import com.ssafy.nuguri.dto.deal.DealDetailDto;
import com.ssafy.nuguri.dto.deal.DealLoginDetailDto;
import com.ssafy.nuguri.dto.deal.DealRegistRequestDto;
import com.ssafy.nuguri.dto.deal.DealUpdateDto;
import com.ssafy.nuguri.repository.deal.DealFavoriteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class DealServiceTest {

    @Autowired
    DealService dealService;
    @Autowired
    DealFavoriteRepository dealFavoriteRepository;

    @Test
    public void 중고거래등록() throws Exception{
        //given
        DealRegistRequestDto dealRegistRequestDto = new DealRegistRequestDto(7L, "연필 팔아용", "연필이 아주 야무집니다",
                1000);

        dealService.dealRegist(1L, dealRegistRequestDto, null);

     }

    @Test
    public void 비로그인시중고거래상세페이지() throws Exception{
        DealDetailDto dealDetail = dealService.findDealDetail(4L);
        System.out.println("dealDetail = " + dealDetail);
    }

    @Test
    public void 로그인시중고거래상세페이지() throws Exception{
        DealLoginDetailDto loginDealDetail = dealService.findLoginDealDetail(2L, 4L);
        System.out.println("loginDealDetail = " + loginDealDetail);
    }

    @Test
    @Commit
    public void 즐겨찾기등록해제() throws Exception{
        dealService.createOrModifyDealFavorite(4L, 4L);
        dealService.createOrModifyDealFavorite(6L, 3L);

        Optional<DealFavorite> dealFavorite = dealFavoriteRepository.findById(9L);
        System.out.println("dealFavorite.get().isFavorite() = " + dealFavorite.get().isFavorite());
    }

    @Test
    @Commit
    public void 조회수증가() throws Exception{
        dealService.increaseHit(3L, null, null);
     }

     @Test
     @Commit
     public void 중고거래수정() throws Exception{

        dealService.updateDealDetail(DealUpdateDto.builder()
                .dealId(3L)
                .title("변경")
                .description("변경되나요")
                .price(24581000)
                .build(), null);
      }

}