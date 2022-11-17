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

    private Long hobbyId;

    private Long localId;

    private Long categoryId;

    private String title;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;

    private boolean isClosed;

    private Integer curNum;

    private Integer maxNum;

    private Integer wishlistNum;

    private Integer chatNum;

    private String imageurl;

    private ApproveStatus approveStatus;


}
