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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

    public List<HobbyDto> findLocalCategoryHobbyList(Long regionId, Long categoryId){ // 지역과 카테고리로 취미방 찾기
        return hobbyRepository.findByRegionAndCategory(regionId,categoryId);
    }

    public HobbyDto findHobbyDetail(Long hobbyId){ // 취미방 상세보기
        return hobbyRepository.hobbyDetail(hobbyId);
    }


    public Long createHobby(HobbyDto hobbyDto){ // 취미방 생성
        Hobby hobbyEntity = Hobby.builder()
                .baseAddress(baseaddressRepository.findById(hobbyDto.getLocalId()).orElseThrow())
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
    public void testflow(){
        System.out.println("======취미방 생성 시작======");
        HobbyDto hobbyDto = HobbyDto.builder()
                .localId(10L)
                .categoryId(3L)
                .title("취미방 1")
                .content("놀자")
                .endDate(LocalDateTime.now())
                .meetingPlace("멀티캠퍼스")
                .isClosed(false)
                .curNum(1)
                .maxNum(10)
                .fee(0)
                .ageLimit(100)
                .sexLimit((char)1)
                .hobbyImage("imageUrl")
                .build();

        HobbyDto hobbyDto2 = HobbyDto.builder()
                .localId(20L)
                .categoryId(3L)
                .title("취미방 1")
                .content("놀자")
                .endDate(LocalDateTime.now())
                .meetingPlace("멀티캠퍼스")
                .isClosed(false)
                .curNum(1)
                .maxNum(10)
                .fee(0)
                .ageLimit(100)
                .sexLimit((char)1)
                .hobbyImage("imageUrl")
                .build();

        HobbyDto hobbyDto3 = HobbyDto.builder()
                .localId(10L)
                .categoryId(2L)
                .title("취미방 1")
                .content("놀자")
                .endDate(LocalDateTime.now())
                .meetingPlace("멀티캠퍼스")
                .isClosed(false)
                .curNum(1)
                .maxNum(10)
                .fee(0)
                .ageLimit(100)
                .sexLimit((char)1)
                .hobbyImage("imageUrl")
                .build();

        createHobby(hobbyDto2);
        createHobby(hobbyDto3);

        Long hobbyId = createHobby(hobbyDto);

        System.out.println("======지역으로 취미방 찾기======");
        List<HobbyDto> result = findLocalHobbyList(10L);
        for (HobbyDto h:result
             ) {
            System.out.println("지역이 10L인 데이터: "+h);
        }

        System.out.println("======지역과 카테고리로 취미방 찾기======");
        List<HobbyDto> result2 = findLocalCategoryHobbyList(10L, 3L);
        for (HobbyDto h:result2
        ) {
            System.out.println("지역이 10L, 카테고리가 30L인 데이터: "+h);
        }

        System.out.println("======취미 상세조회======");
        HobbyDto result3 = findHobbyDetail(hobbyId);
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