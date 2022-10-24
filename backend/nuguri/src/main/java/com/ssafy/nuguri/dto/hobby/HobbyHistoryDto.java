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
public class HobbyHistoryDto {
    private Long id;
    private Long Id;
    private Long hobbyId;
    private boolean isPromoter;
    private ApproveStatus approveStatus;

}
