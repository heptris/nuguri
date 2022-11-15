package com.ssafy.nuguri.service.hobby;

import com.ssafy.nuguri.domain.hobby.Hobby;
import com.ssafy.nuguri.domain.hobby.HobbyFavorite;
import com.ssafy.nuguri.domain.member.Member;
import com.ssafy.nuguri.exception.ex.CustomException;
import com.ssafy.nuguri.repository.hobby.HobbyFavoriteRepository;
import com.ssafy.nuguri.repository.hobby.HobbyRepository;
import com.ssafy.nuguri.repository.member.MemberRepository;
import com.ssafy.nuguri.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ssafy.nuguri.exception.ex.ErrorCode.DEAL_NOT_FOUND;
import static com.ssafy.nuguri.exception.ex.ErrorCode.MEMBER_NOT_FOUND;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HobbyFavoriteService {
    private final HobbyFavoriteRepository hobbyFavoriteRepository;
    private final MemberRepository memberRepository;
    private final HobbyRepository hobbyRepository;

    @Transactional
    public boolean createOrModifyHobbyFavorite(Long hobbyId) {
        System.out.println("hobbyId = " + hobbyId);
        Long memberId = SecurityUtil.getCurrentMemberId();
        System.out.println("memberId = " + memberId);
        HobbyFavorite hobbyFavorite = hobbyFavoriteRepository.findByMemberIdAndHobbyId(memberId, hobbyId);
        if(hobbyFavorite == null){
            Member member = memberRepository.findById(memberId).orElseThrow(()-> new CustomException(MEMBER_NOT_FOUND));
            Hobby hobby = hobbyRepository.findById(hobbyId).orElseThrow(()-> new CustomException(DEAL_NOT_FOUND));
            HobbyFavorite newHobbyFavorite = HobbyFavorite.builder()
                    .member(member)
                    .hobby(hobby)
                    .isFavorite(true)
                    .build();
            hobbyFavoriteRepository.save(newHobbyFavorite);
            return true;
        }else{
            return hobbyFavorite.changeFavorite();
        }
    }
    public Integer getFavoriteCnt(Long hobbyId){
        return hobbyFavoriteRepository.getFavoriteNumberByHobbyId(hobbyId);
    }

}
