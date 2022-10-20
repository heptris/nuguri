package com.ssafy.nuguri.service.group.hobby;

import com.ssafy.nuguri.domain.hobby.Hobby;
import com.ssafy.nuguri.repository.hobby.HobbyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HobbyService {

    private final HobbyRepository hobbyRepository;

    @Transactional
    public void createHobby(Hobby hobby){ // 취미방 생성
        hobbyRepository.save(hobby);
    }

    @Transactional
    public Hobby searchHobby(Long id){ // id로 취미방 찾기
        Hobby result = hobbyRepository.findById(id).orElseThrow();
        return result;
    }

    @Transactional
    public List<Hobby> searchAllHobbyByRegion(String region){ // 지역으로 취미 찾기
        List<Hobby> result = hobbyRepository.findByRegion(region);
        return result;
    }

    @Transactional
    public List<Hobby> searchAllHobbyByCategory(String category){ // 카테고리로 취미 찾기 -> 지역 필터링 없이 카테고리로만 데이터를 가져오는건데 필요할지 의문
        List<Hobby> result = hobbyRepository.findByCategory(category);
        return result;
    }


    @Transactional
    public List<Hobby> searchAllHobbyByRegionAndCategory(String region,String category){ // 지역과 카테고리로 취미 찾기
        List<Hobby> result = hobbyRepository.findByRegionAndCategory(region, category);
        return result;
    }
}
