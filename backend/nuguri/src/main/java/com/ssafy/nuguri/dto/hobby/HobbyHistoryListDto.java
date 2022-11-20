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
public class HobbyHistoryListDto {
    private Long hobbyHistoryId;
    private Long hobbyId;
    private Long memberId;
    private boolean isPromoter;
    private ApproveStatus approveStatus;
    private String nickname;
    private String imageUrl;
}
