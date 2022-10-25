package com.ssafy.nuguri.repository.hobby;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.nuguri.domain.hobby.ApproveStatus;
import com.ssafy.nuguri.dto.hobby.HobbyHistoryDto;
import com.ssafy.nuguri.dto.hobby.HobbyStatusDto;

import javax.persistence.EntityManager;
import java.util.List;

import static com.ssafy.nuguri.domain.category.QCategory.category;
import static com.ssafy.nuguri.domain.hobby.QHobby.hobby;
import static com.ssafy.nuguri.domain.hobby.QHobbyHistory.hobbyHistory;
import static com.ssafy.nuguri.domain.member.QMember.member;

public class HobbyHistoryRepositoryImpl implements HobbyHistoryRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public HobbyHistoryRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<HobbyHistoryDto> waiter(Long hobbyId) {
        List<HobbyHistoryDto> hobbyHistoryDtoList = queryFactory.select(Projections.constructor(HobbyHistoryDto.class,
                        hobbyHistory.id,
                        hobby.id,
                        member.id,
                        hobbyHistory.isPromoter,
                        hobbyHistory.approveStatus
                ))
                .from(hobbyHistory)
                .innerJoin(hobbyHistory.hobby,hobby)
                .innerJoin(hobbyHistory.member,member)
                .where(hobby.id.eq(hobbyId)
                        .and(hobbyHistory.approveStatus.eq(ApproveStatus.READY)))
                .fetch();
        return hobbyHistoryDtoList;
    }

    @Override
    public List<HobbyHistoryDto> participant(Long hobbyId) {
        List<HobbyHistoryDto> hobbyHistoryDtoList = queryFactory.select(Projections.constructor(HobbyHistoryDto.class,
                        hobbyHistory.id,
                        hobby.id,
                        member.id,
                        hobbyHistory.isPromoter,
                        hobbyHistory.approveStatus
                ))
                .from(hobbyHistory)
                .innerJoin(hobbyHistory.hobby,hobby)
                .innerJoin(hobbyHistory.member,member)
                .where(hobby.id.eq(hobbyId)
                        .and(hobbyHistory.approveStatus.eq(ApproveStatus.APPROVE)))
                .fetch();
        return hobbyHistoryDtoList;
    }

    @Override
    public boolean changeStatus(Long hobbyHistoryId, ApproveStatus status) {
        queryFactory.selectFrom(hobbyHistory)
                .where(hobbyHistory.id.eq(hobbyHistoryId))
                .fetchOne()
                .updateApproveStatus(status);
        return false;
    }


    @Override
    public List<HobbyStatusDto> findByStatus(Long userId, ApproveStatus status) {
        List<HobbyStatusDto> hobbyStatusDtoList = queryFactory.select(Projections.constructor(HobbyStatusDto.class,
                        hobby.id,
                        category.id,
                        hobby.title,
                        hobby.endDate,
                        hobby.curNum,
                        hobby.maxNum,
                        hobby.curNum, // wishlistNum으로 변경
                        hobby.curNum, // chatNum으로 변경
                        hobby.hobbyImage,
                        hobbyHistory.approveStatus
                ))
                .from(hobbyHistory)
                .innerJoin(hobbyHistory.hobby, hobby)
                .innerJoin(hobby.category, category)
                .where(hobbyHistory.member.id.eq(userId)
                        .and(hobbyHistory.approveStatus.eq(status)))
                .fetch();

        return hobbyStatusDtoList;
    }



}
