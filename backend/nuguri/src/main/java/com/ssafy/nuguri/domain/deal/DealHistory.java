package com.ssafy.nuguri.domain.deal;

import com.ssafy.nuguri.domain.BaseEntity;
import com.ssafy.nuguri.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Entity
public class DealHistory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deal_history_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deal_id")
    private Deal deal;

    @Enumerated(EnumType.STRING)
    private DealStatus dealStatus;

    private LocalDateTime promiseTime;

    private String promiseLocation;

    public void updateDealHistory(DealStatus dealStatus, LocalDateTime promiseTime, String promiseLocation){
        this.dealStatus = dealStatus;
        this.promiseTime = promiseTime;
        this.promiseLocation = promiseLocation;
    }

}
