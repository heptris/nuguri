package com.ssafy.nuguri.repository.hobby;


import com.ssafy.nuguri.domain.hobby.ApproveStatus;
import com.ssafy.nuguri.domain.hobby.Hobby;
import com.ssafy.nuguri.dto.hobby.HobbyHistoryDto;
import com.ssafy.nuguri.dto.hobby.HobbyHistoryListDto;
import com.ssafy.nuguri.dto.hobby.HobbyHistoryResponseDto;

import java.util.List;

public interface HobbyHistoryRepositoryCustom {

    List<HobbyHistoryListDto> userByStatus(Long hobbyId, ApproveStatus approveStatus);


    // 방장이 승인여부를 변경했을 때 (True -> 승인, False -> 반려)
    ApproveStatus changeStatus(Long hobbyHistoryId, ApproveStatus status);

    // 상태별로 취미방 뿌려주기(유저페이지에서 상태별로 취미방 확인 가능해야 함)
    List<HobbyHistoryResponseDto> findByStatus(Long userId, ApproveStatus status);

    // 운영중인 취미방
    List<HobbyHistoryResponseDto> findOperatings(Long userId);

    HobbyHistoryDto findByHobbyAndMemberIdDto(Long hobbyId, Long memberId);


    HobbyHistoryDto findByIdDto(Long hobbyHistoryId);

    Long findOwnerId(Hobby hobby);
}
