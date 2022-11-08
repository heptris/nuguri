package com.ssafy.nuguri.dto.baseaddress;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseAddressDto {

    private Long localId;
    private String sido;
    private String gugun;
    private String dong;
    private String lat;
    private String lng;

}
