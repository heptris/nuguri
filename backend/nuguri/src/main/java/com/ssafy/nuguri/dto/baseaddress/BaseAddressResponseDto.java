package com.ssafy.nuguri.dto.baseaddress;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseAddressResponseDto {

    private Long localId;
    private String baseAddress;
}
