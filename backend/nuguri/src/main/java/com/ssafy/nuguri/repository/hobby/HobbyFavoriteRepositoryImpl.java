package com.ssafy.nuguri.repository.hobby;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.nuguri.domain.hobby.HobbyFavorite;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

import static com.ssafy.nuguri.domain.hobby.QHobbyFavorite.hobbyFavorite;


public class HobbyFavoriteRepositoryImpl implements HobbyFavoriteRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Autowired
    public HobbyFavoriteRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public HobbyFavorite findByMemberIdAndHobbyId(Long memberId, Long hobbyId) {
        return queryFactory.selectFrom(hobbyFavorite)
                .where(hobbyFavorite.member.id.eq(memberId),
                        hobbyFavorite.hobby.id.eq(hobbyId))
                .fetchOne();
    }

    @Override
    public Integer getFavoriteNumberByHobbyId(Long hobbyId) {
        return queryFactory.selectFrom(hobbyFavorite)
                .where(hobbyFavorite.hobby.id.eq(hobbyId))
                .fetch().size();
    }
}
