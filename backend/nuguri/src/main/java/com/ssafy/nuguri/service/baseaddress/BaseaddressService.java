package com.ssafy.nuguri.service.baseaddress;

import com.ssafy.nuguri.dto.baseaddress.*;
import com.ssafy.nuguri.repository.baseaddress.BaseaddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BaseaddressService {
    private final BaseaddressRepository baseaddressRepository;

    public List<BaseAddressResponseDto> findBaseaddressByDong(String keyword){
        return baseaddressRepository.findBaseaddressByDong(keyword);
    }

    public List<BaseAddressSidoDto> sidoList(){
        return baseaddressRepository.findSidoList();
    }

    public List<BaseAddressGugunDto> gugunList(String sido){
        return baseaddressRepository.findGugunList(sido);
    }

    public List<BaseAddressDongDto> dongList(String gugun){
        return baseaddressRepository.findDongList(gugun);
    }

    public List<BaseAddressDto> findAllBaseAddress(){
        return baseaddressRepository.findAllBaseAddress();
    }
}
