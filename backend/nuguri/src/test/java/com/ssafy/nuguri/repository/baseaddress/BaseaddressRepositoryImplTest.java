package com.ssafy.nuguri.repository.baseaddress;

import com.ssafy.nuguri.dto.baseaddress.*;
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

    @Test
    public void 시도목록조회() throws Exception{
        List<BaseAddressSidoDto> sidoList = baseaddressRepository.findSidoList();
        for (BaseAddressSidoDto baseAddressSidoDto : sidoList) {
            System.out.println("baseAddressSidoDto = " + baseAddressSidoDto);
        }
    }

    @Test
    public void 구군목록조회() throws Exception{
        List<BaseAddressGugunDto> baseAddressGugunDtoList = baseaddressRepository.findGugunList("서울특별시");
        for (BaseAddressGugunDto baseAddressGugunDto : baseAddressGugunDtoList) {
            System.out.println("baseAddressGugunDto = " + baseAddressGugunDto);
        }
    }

    @Test
    public void 동목록조회() throws Exception{
        List<BaseAddressDongDto> dongList = baseaddressRepository.findDongList("송파구");
        for (BaseAddressDongDto baseAddressDongDto : dongList) {
            System.out.println("baseAddressDongDto = " + baseAddressDongDto);
        }
    }

    @Test
    public void 전체지역조회() throws Exception{
        List<BaseAddressDto> allBaseAddress = baseaddressRepository.findAllBaseAddress();
        for (BaseAddressDto baseAddress : allBaseAddress) {
            System.out.println("baseAddress = " + baseAddress);
        }
    }
}