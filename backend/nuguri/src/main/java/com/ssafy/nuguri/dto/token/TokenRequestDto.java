package com.ssafy.nuguri.dto.token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
public class TokenRequestDto {
    String accessToken;
    String refreshToken;
}
