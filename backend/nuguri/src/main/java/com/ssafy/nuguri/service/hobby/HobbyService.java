package com.ssafy.nuguri.service.hobby;

import com.ssafy.nuguri.domain.hobby.Hobby;
import com.ssafy.nuguri.dto.hobby.HobbyDto;
import com.ssafy.nuguri.repository.baseaddress.BaseaddressRepository;
import com.ssafy.nuguri.repository.category.CategoryRepository;
import com.ssafy.nuguri.repository.hobby.HobbyRepository;
import com.ssafy.nuguri.repository.hobby.HobbyRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HobbyService {

    private final HobbyRepository hobbyRepository;
    private final BaseaddressRepository baseaddressRepository;
    private final CategoryRepository categoryRepository;

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
    public Long createHobby(HobbyDto hobbyDto){ // 취미방 생성
        Hobby hobbyEntity = Hobby.builder()
                .baseAddress(baseaddressRepository.findById(hobbyDto.getLocalId()).orElseThrow())
                .category(categoryRepository.findById(hobbyDto.getCategoryId()).orElseThrow())
                .title(hobbyDto.getTitle())
                .content(hobbyDto.getContent())
                .endDate(hobbyDto.getEndDate())
                .meetingPlace(hobbyDto.getMeetingPlace())
                .isClosed(hobbyDto.isClosed())
                .curNum(hobbyDto.getCurNum()) // 그냥 1을 넣어도 될듯
                .maxNum(hobbyDto.getMaxNum())
                .fee(hobbyDto.getFee())
                .ageLimit(hobbyDto.getAgeLimit())
                .sexLimit(hobbyDto.getSexLimit())
                .hobbyImage(hobbyDto.getHobbyImage())
                .build();
        return hobbyRepository.save(hobbyEntity).getId();
    }


}
