package com.ssafy.nuguri.service.hobby;

import com.ssafy.nuguri.domain.hobby.Hobby;
import com.ssafy.nuguri.dto.hobby.HobbyDto;
import com.ssafy.nuguri.repository.baseaddress.BaseaddressRepository;
import com.ssafy.nuguri.repository.category.CategoryRepository;
import com.ssafy.nuguri.repository.hobby.HobbyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class HobbyServiceTest {

    @Autowired
    HobbyRepository hobbyRepository;
    @Autowired
    BaseaddressRepository baseaddressRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public List<HobbyDto> findLocalHobbyList(Long regionId){ // 지역으로 취미방 찾기
        return hobbyRepository.findByRegion(regionId);
    }
    @Test
    public void test(){

    }

    public List<HobbyDto> findLocalCategoryHobbyList(Long regionId, Long categoryId){ // 지역과 카테고리로 취미방 찾기
        return hobbyRepository.findByRegionAndCategory(regionId,categoryId);
    }
    @Test
    public void test1(){

    }

    public HobbyDto findHobbyDetail(Long hobbyId){ // 취미방 상세보기
        return hobbyRepository.hobbyDetail(hobbyId);
    }
    @Test
    public void test2(){

    }

    public Long createHobby(HobbyDto hobbyDto){ // 취미방 생성
        Hobby hobbyEntity = Hobby.builder()
                .baseAddress(baseaddressRepository.findById(hobbyDto.getLocalId()).orElseThrow()) // 여기서 orElseThrow를 써도 됨? Throw가 발생하면 어떻게 코드가 실행되는지?
                .category(categoryRepository.findById(hobbyDto.getCategoryId()).orElseThrow())
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
        return hobbyRepository.save(hobbyEntity).getId();
    }
    @Test
    public void test3(){

    }
}