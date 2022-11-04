package com.ssafy.nuguri.dto.baseaddress;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseAddressSearchDto {

    private Long localId;
    private String sido;
    private String gugun;
    private String dong;

    public BaseAddressResponseDto toBaseAddressResponseDto(){
        return BaseAddressResponseDto.builder()
                .localId(localId)
                .baseAddress(sido+" "+gugun+" "+dong)
                .build();
    }

}
