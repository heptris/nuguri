package com.ssafy.nuguri.domain.member;

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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String nickname;

    private String description;

    private Double temperature;

    private Integer age;

    private Character sex;

    private String profileImage;

    public void changeProfileImage(String profileImage){
        this.profileImage = profileImage;
    }

    public void changeBaseAddress(BaseAddress baseAddress){
        this.baseAddress = baseAddress;
    }

    public void changeTemperature(Double temperature){
        this.temperature = temperature;
    }

    public void changeMemberId(Long memberId) {
        this.id = memberId;
    }

    public void profileModify(String profileImage, String nickname) {
        this.profileImage = profileImage;
        this.nickname = nickname;
    }

    public void baseAddressModify(BaseAddress baseAddress) {
        this.baseAddress = baseAddress;
    }
}
