package com.ssafy.nuguri.repository.baseaddress;

import com.ssafy.nuguri.dto.baseaddress.BaseAddressResponseDto;
import com.ssafy.nuguri.dto.baseaddress.BaseAddressSearchDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class BaseaddressRepositoryImplTest {
    @Autowired
    BaseaddressRepository baseaddressRepository;

    @Test
    public void 지역검색() throws Exception{
        List<BaseAddressResponseDto> baseAddressResponseDtoList = baseaddressRepository.findBaseaddressByDong("종로");
        for (BaseAddressResponseDto baseAddressResponseDto : baseAddressResponseDtoList) {
            System.out.println("baseAddressResponseDto = " + baseAddressResponseDto);
        }
    }
}