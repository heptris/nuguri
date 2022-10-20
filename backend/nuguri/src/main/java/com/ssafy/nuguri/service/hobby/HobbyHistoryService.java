package com.ssafy.nuguri.service.group.hobby;

import com.ssafy.nuguri.domain.hobby.ApproveStatus;
import com.ssafy.nuguri.domain.hobby.Hobby;
import com.ssafy.nuguri.domain.hobby.HobbyHistory;
import com.ssafy.nuguri.dto.member.member.Member;
import com.ssafy.nuguri.repository.hobby.HobbyHistoryRepository;
import com.ssafy.nuguri.repository.hobby.HobbyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HobbyHistoryService {
    private final HobbyHistoryRepository hobbyHistoryRepository;
    private final HobbyRepository hobbyRepository;

    @Transactional
    public HobbyHistory createHobbyHistory(Hobby hobby, Member member, boolean promoter){ // 취미방 생성 또는 참여신청

//        // 내가 처음 참여하는 사람이면 방장으로 임명 -> 전체탐색말고 다른 효율적인 방법이 있나?
//        boolean promoter = false;
//        Long hobbyId = hobby.getId();
//        int result = hobbyHistoryRepository.findByHobbyId(hobbyId);
//        if(result == 0){
//            promoter = true;
//        }
        return HobbyHistory.builder().member(member).hobby(hobby).isPromoter(promoter).approveStatus(ApproveStatus.REJECT).build();

    }

    @Transactional
    public void updateHobbyHistory(Long hobbyId, Long memberId){ // 방장이 승인여부를 변경했을 때
        Hobby hobby = hobbyRepository.findById(hobbyId).orElseThrow();
        // Member member =  memberRepository.findById(memberId).orElseThrow();
//        if(hobby.getCurNum() >= hobby.getMaxNum() // 정원초과
//                || hobby.getAgeLimit() > member.  // 나이제한
//                || hobby.getSexLimit() == member. // 성별제한
//                || hobby.getEndDate() <= LocalDateTime.now() // 모임기간 만료
//                || hobby.getBaseAddress() != member. // 참여 불가능한 위치
//        ){}

//        HobbyHistory.builder().member(member).hobby(hobby);
//        hobbyHistoryRepository.save(hobbyHistory);

    }

    @Transactional
    public HobbyHistory searchHobbyHistory(Long hobbyHistoryId){ // 특정 이력 조회
        HobbyHistory result = hobbyHistoryRepository.findById(hobbyHistoryId).orElseThrow();
        return result;
    }

    @Transactional
    public List<Member> HobbyRoomMember(Long hobbyId){ // 특정 취미방에 참여중인 멤버
        List<Long> memberIdList = hobbyHistoryRepository.findMemberByHobbyId(hobbyId);
        List<Member> memberList = new ArrayList<Member>();
//        for (int i = 0; i < memberIdList.size(); i++) {
//            Long memberId = memberIdList.get(i);
//            memberList.add(memberRepository.findById(memberId));
//        }
        return memberList;
    }


}
