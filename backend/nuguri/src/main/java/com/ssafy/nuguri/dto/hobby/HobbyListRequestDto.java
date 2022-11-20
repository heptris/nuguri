package com.ssafy.nuguri.dto.hobby;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class HobbyListRequestDto {
    private Long localId;
    private Long categoryId;
}
