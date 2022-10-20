package com.ssafy.nuguri.domain.deal;

import com.ssafy.nuguri.domain.BaseEntity;
import com.ssafy.nuguri.domain.baseaddress.BaseAddress;
import com.ssafy.nuguri.domain.category.Category;
import com.ssafy.nuguri.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Entity
public class Deal extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deal_id")
    private Long id;

    @Builder.Default
    @OneToMany(mappedBy = "deal", cascade = CascadeType.ALL)
    private List<DealHistory> dealHistoryList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "deal", cascade = CascadeType.ALL)
    private List<DealFavorite> dealFavoriteList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "local_id")
    private BaseAddress baseAddress;

    private String title;

    private String description;

    private int price;

    private int hit;

    private boolean isDeal;

    private String dealImage;

    public void registDeal(Member member, Category category, BaseAddress baseAddress, String dealImage){
        this.member = member;
        this.category = category;
        this.baseAddress = baseAddress;
        this.dealImage = dealImage;
    }
}
