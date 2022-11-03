package com.ssafy.nuguri.repository.baseaddress;

import com.ssafy.nuguri.domain.baseaddress.BaseAddress;
import com.ssafy.nuguri.dto.baseaddress.BaseAddressResponseDto;
import com.ssafy.nuguri.dto.baseaddress.BaseAddressSearchDto;

import java.util.List;

public interface BaseaddressRepositoryCustom {
    List<BaseAddressResponseDto> findBaseaddressByDong(String keyword);
}
