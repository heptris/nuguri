package com.ssafy.nuguri.repository.hobby;


import com.ssafy.nuguri.domain.hobby.ApproveStatus;
import com.ssafy.nuguri.dto.hobby.HobbyHistoryDto;
import com.ssafy.nuguri.dto.hobby.HobbyStatusDto;

import java.util.List;

public interface HobbyHistoryRepositoryCustom {

    // 어떤 방에 참여를 신청한 사람들 목록(Status 대기중)
    List<HobbyHistoryDto> waitingPerson(Long hobbyId);

    // 어떤 방에 참여중인 사람들 목록(Status 승인)
    List<HobbyHistoryDto> participant(Long hobbyId);

    // 방장이 승인여부를 변경했을 때 (True -> 승인, False -> 반려)
    ApproveStatus changeStatus(Long hobbyHistoryId, ApproveStatus status);

    // 상태별로 취미방 뿌려주기(유저페이지에서 상태별로 취미방 확인 가능해야 함)
    List<HobbyStatusDto> findByStatus(Long userId, ApproveStatus status);

    HobbyHistoryDto findByIdDto(Long hobbyHistoryId);
}
