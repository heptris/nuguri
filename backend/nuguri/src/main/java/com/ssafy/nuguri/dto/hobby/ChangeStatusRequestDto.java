package com.ssafy.nuguri.dto.hobby;

import com.ssafy.nuguri.domain.hobby.ApproveStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ChangeStatusRequestDto {
    Long hobbyId;
    Long participantId;
    ApproveStatus approveStatus;
}
