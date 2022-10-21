package com.ssafy.nuguri.repository.hobby;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.nuguri.domain.hobby.ApproveStatus;
import com.ssafy.nuguri.domain.hobby.HobbyHistory;
import com.ssafy.nuguri.dto.hobby.HobbyDto;
import com.ssafy.nuguri.dto.hobby.HobbyHistoryDto;
import com.ssafy.nuguri.dto.member.member.Member;

import javax.persistence.EntityManager;
import java.util.List;

import static com.ssafy.nuguri.domain.hobby.QHobby.hobby;
import static com.ssafy.nuguri.domain.hobby.QHobbyHistory.hobbyHistory;
import static com.ssafy.nuguri.domain.member.QMember.member;

public class HobbyHistoryRepositoryImpl implements HobbyHistoryRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public HobbyHistoryRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Integer create(HobbyHistoryDto hobbyHistoryDto) {
        //em.persist(HobbyHistoryEntity);
        return 1;
    }

    @Override
    public List<Member> waiter(Long hobbyId) {
        List<HobbyHistoryDto> hobbyHistoryDtoList = queryFactory.select(Projections.constructor(HobbyHistoryDto.class,
                        hobbyHistory.id,
                        hobby.id,
                        member.id,
                        hobbyHistory.isPromoter,
                        hobbyHistory.approveStatus
                ))
                .from(hobbyHistory)
                .where(hobby.id.eq(hobbyId)
                        .and(hobbyHistory.approveStatus.eq(ApproveStatus.READY)))
                .fetch();

        return null;
    }

    @Override
    public List<Member> participant(Long hobbyId) {
        List<HobbyHistoryDto> hobbyHistoryDtoList = queryFactory.select(Projections.constructor(HobbyHistoryDto.class,
                        hobbyHistory.id,
                        hobby.id,
                        member.id,
                        hobbyHistory.isPromoter,
                        hobbyHistory.approveStatus
                ))
                .from(hobbyHistory)
                .where(hobby.id.eq(hobbyId)
                        .and(hobbyHistory.approveStatus.eq(ApproveStatus.APPROVE)))
                .fetch();

        return null;
    }

    @Override
    public boolean changeStatus(Long hobbyHistoryId, ApproveStatus status) {
        HobbyHistoryDto hobbyHistoryDto = queryFactory.select(Projections.constructor(HobbyHistoryDto.class,
                        hobbyHistory.id,
                        hobby.id,
                        member.id,
                        hobbyHistory.isPromoter,
                        hobbyHistory.approveStatus
                ))
                .from(hobbyHistory)
                .where(hobbyHistory.id.eq(hobbyHistoryId))
                .fetchOne();

        hobbyHistoryDto.setApproveStatus(status);

        //em.persist(hobbyHistoryEntity);

        return false;
    }


    @Override
    public List<HobbyDto> findByStatus(Long userId, ApproveStatus status) {
        List<HobbyHistoryDto> hobbyHistoryDtoList = queryFactory.select(Projections.constructor(HobbyHistoryDto.class,
                        hobbyHistory.id,
                        hobby.id,
                        member.id,
                        hobbyHistory.isPromoter,
                        hobbyHistory.approveStatus
                ))
                .from(hobbyHistory)
                .where(member.id.eq(userId)
                        .and(hobbyHistory.approveStatus.eq(status)))
                .fetch();

        return null;
    }



}
