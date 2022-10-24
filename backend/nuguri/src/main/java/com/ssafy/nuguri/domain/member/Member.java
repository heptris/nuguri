package com.ssafy.nuguri.dto.member.member;

import com.ssafy.nuguri.domain.BaseEntity;
import com.ssafy.nuguri.domain.alarm.Alarm;
import com.ssafy.nuguri.domain.baseaddress.BaseAddress;
import com.ssafy.nuguri.domain.deal.Deal;
import com.ssafy.nuguri.domain.deal.DealFavorite;
import com.ssafy.nuguri.domain.deal.DealHistory;
import com.ssafy.nuguri.domain.group.GroupPurchase;
import com.ssafy.nuguri.domain.group.GroupPurchaseHistory;
import com.ssafy.nuguri.domain.hobby.HobbyHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Entity
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "local_id")
    private BaseAddress baseAddress;

    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Alarm> alarmList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<HobbyHistory> hobbyHistoryList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<DealHistory> dealHistoryList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<DealFavorite> dealFavoriteList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Deal> dealList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<GroupPurchase> groupPurchaseList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<GroupPurchaseHistory> groupPurchaseHistoryList = new ArrayList<>();

    @Column(unique = true)
    private String email;

    private String name;

    @Column(unique = true)
    private String nickname;

    private double temperature;

    private int age;

    private char sex;

    private String profileImage;

}
