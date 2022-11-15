package com.ssafy.nuguri.domain.hobby;

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
public class Hobby extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hobby_id")
    private Long id;

    @Builder.Default
    @OneToMany(mappedBy = "hobby", cascade = CascadeType.ALL)
    private List<HobbyHistory> hobbyHistoryList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "hobby", cascade = CascadeType.ALL)
    private List<HobbyFavorite> hobbyFavoriteList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "local_id")
    private BaseAddress baseAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String title;

    private String content;

    private LocalDateTime endDate;

    private String meetingPlace;

    private boolean isClosed;

    private int curNum;

    private int maxNum;

    private int fee;

    private int ageLimit;

    private char sexLimit;

    private String hobbyImage;

}
