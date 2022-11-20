package com.ssafy.nuguri.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberProfileModifyResponseDto {
    private String profileImage;
    private String nickname;
}
