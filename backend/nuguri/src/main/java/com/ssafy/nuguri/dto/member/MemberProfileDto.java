package com.ssafy.nuguri.dto.member;

import com.ssafy.nuguri.domain.baseaddress.BaseAddress;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberProfileDto {
    private String email;
    private String name;
    private String nickname;
    private String description;
    private Double temperature;
    private String profileImage;
    private String baseAddress;
}
