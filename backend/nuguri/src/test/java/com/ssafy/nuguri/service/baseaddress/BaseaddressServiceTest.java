package com.ssafy.nuguri.service.baseaddress;

import com.ssafy.nuguri.dto.baseaddress.BaseAddressResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class BaseaddressServiceTest {

    @Autowired
    BaseaddressService baseaddressService;

    @Test
    public void 지역검색() throws Exception{
//        List<String> baseaddressList = baseaddressService.findBaseaddressByDong("종로");
//        for (String s : baseaddressList) {
//            System.out.println("s = " + s);
//        }
        List<BaseAddressResponseDto> baseAddressResponseDtoList = baseaddressService.findBaseaddressByDong("종로");
        for (BaseAddressResponseDto baseAddressResponseDto : baseAddressResponseDtoList) {
            System.out.println("baseAddressResponseDto = " + baseAddressResponseDto);
        }
    }

}