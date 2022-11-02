package com.ssafy.nuguri.dto.hobby;

import com.ssafy.nuguri.domain.hobby.ApproveStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class HobbyHistoryResponseDto {

    private Long categoryId;

    private String title;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;

    private int curNum;

    private int maxNum;

    private int wishlistNum;

    private int chatNum;

    private String imageurl;

    private ApproveStatus approveStatus;


}
