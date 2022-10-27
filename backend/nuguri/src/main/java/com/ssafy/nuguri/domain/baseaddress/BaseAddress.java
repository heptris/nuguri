package com.ssafy.nuguri.domain.baseaddress;

import com.ssafy.nuguri.domain.deal.Deal;
import com.ssafy.nuguri.domain.group.GroupPurchase;
import com.ssafy.nuguri.domain.hobby.Hobby;
import com.ssafy.nuguri.domain.member.Member;
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
public class BaseAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "local_id")
    private Long id;

    @Builder.Default
    @OneToMany(mappedBy = "baseAddress", cascade = CascadeType.ALL)
    private List<Hobby> hobbyList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "baseAddress", cascade = CascadeType.ALL)
    private List<Deal> dealList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "baseAddress", cascade = CascadeType.ALL)
    private List<GroupPurchase> groupPurchaseList = new ArrayList<>();

    private String sido;

    private String gugun;

    private String dong;

    private String dongcode;

    private String lat;

    private String lng;

}
