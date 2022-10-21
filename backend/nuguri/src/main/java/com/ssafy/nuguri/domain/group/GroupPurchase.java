package com.ssafy.nuguri.domain.group;

import com.ssafy.nuguri.domain.BaseEntity;
import com.ssafy.nuguri.domain.baseaddress.BaseAddress;
import com.ssafy.nuguri.domain.category.Category;
import com.ssafy.nuguri.dto.member.member.Member;
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
public class GroupPurchase extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_purchase_id")
    private Long id;

    @Builder.Default
    @OneToMany(mappedBy = "groupPurchase", cascade = CascadeType.ALL)
    private List<GroupPurchaseHistory> groupPurchaseHistoryList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "local_id")
    private BaseAddress baseAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    private String title;

    private String description;

    private String productUrl;

    private LocalDateTime endDate;

    private int goalNumber;

    private int reservedNumber;

    private int price;

    private String groupPurchaseImage;

}
