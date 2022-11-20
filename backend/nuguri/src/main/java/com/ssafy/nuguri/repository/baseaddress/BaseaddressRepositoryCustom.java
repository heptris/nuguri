package com.ssafy.nuguri.repository.baseaddress;

import com.ssafy.nuguri.domain.baseaddress.BaseAddress;
import com.ssafy.nuguri.dto.baseaddress.*;

import java.util.List;

public interface BaseaddressRepositoryCustom {
    List<BaseAddressResponseDto> findBaseaddressByDong(String keyword);

    List<BaseAddressSidoDto> findSidoList();

    List<BaseAddressGugunDto> findGugunList(String sido);

    List<BaseAddressDongDto> findDongList(String gugun);

    List<BaseAddressDto> findAllBaseAddress();
}
