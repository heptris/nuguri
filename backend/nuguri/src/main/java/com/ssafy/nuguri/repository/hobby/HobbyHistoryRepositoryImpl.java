package com.ssafy.nuguri.repository.hobby;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.nuguri.domain.hobby.ApproveStatus;
import com.ssafy.nuguri.domain.hobby.Hobby;
import com.ssafy.nuguri.domain.hobby.QHobbyFavorite;
import com.ssafy.nuguri.dto.hobby.HobbyHistoryDto;
import com.ssafy.nuguri.dto.hobby.HobbyHistoryListDto;
import com.ssafy.nuguri.dto.hobby.HobbyHistoryResponseDto;

import javax.persistence.EntityManager;
import java.util.List;

import static com.querydsl.core.types.ExpressionUtils.count;
import static com.ssafy.nuguri.domain.baseaddress.QBaseAddress.baseAddress;
import static com.ssafy.nuguri.domain.category.QCategory.category;
import static com.ssafy.nuguri.domain.hobby.QHobby.hobby;
import static com.ssafy.nuguri.domain.hobby.QHobbyFavorite.hobbyFavorite;
import static com.ssafy.nuguri.domain.hobby.QHobbyHistory.hobbyHistory;
import static com.ssafy.nuguri.domain.member.QMember.member;
import static java.lang.Boolean.*;

public class HobbyHistoryRepositoryImpl implements HobbyHistoryRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public HobbyHistoryRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<HobbyHistoryListDto> userByStatus(Long hobbyId, ApproveStatus approveStatus) {
        List<HobbyHistoryListDto> hobbyHistoryDtoList = queryFactory.select(Projections.constructor(HobbyHistoryListDto.class,
                        hobbyHistory.id,
                        hobby.id,
                        member.id,
                        hobbyHistory.isPromoter,
                        hobbyHistory.approveStatus,
                        member.nickname,
                        member.profileImage
                ))
                .from(hobbyHistory)
                .innerJoin(hobbyHistory.hobby,hobby)
                .innerJoin(hobbyHistory.member,member)
                .where(hobby.id.eq(hobbyId)
                        .and(hobbyHistory.approveStatus.eq(approveStatus)))
                .fetch();
        return hobbyHistoryDtoList;
    }
    @Override
    public ApproveStatus changeStatus(Long hobbyHistoryId, ApproveStatus status) {
        queryFactory.selectFrom(hobbyHistory)
                .where(hobbyHistory.id.eq(hobbyHistoryId))
                .fetchOne()
                .updateApproveStatus(status);
        return status;
    }

    @Override
    public List<HobbyHistoryResponseDto> findByStatus(Long userId, ApproveStatus status) {
        List<HobbyHistoryResponseDto> hobbyHistoryResponseDtoList = queryFactory.select(Projections.constructor(HobbyHistoryResponseDto.class,
                        hobby.id,
                        hobby.baseAddress.id,
                        category.id,
                        hobby.title,
                        hobby.endDate,
                        hobby.isClosed,
                        hobby.curNum,
                        hobby.maxNum,
                        ExpressionUtils.as(
                                JPAExpressions
                                        .select(count(hobbyFavorite.id))
                                        .from(hobbyFavorite)
                                        .where(hobbyFavorite.hobby.id.eq(hobby.id)),"wishlistNum"
                        ),
                        hobby.rowAgeLimit,
                        hobby.highAgeLimit,
                        hobby.sexLimit,
                        hobby.hobbyImage,
                        hobbyHistory.approveStatus
                ))
                .from(hobbyHistory)
                .innerJoin(hobbyHistory.hobby, hobby)
                .innerJoin(hobby.category, category)
                .where(hobbyHistory.member.id.eq(userId)
                        ,hobby.isClosed.eq(false)
                        ,hobbyHistory.approveStatus.eq(status)
                )
                .fetch();

        return hobbyHistoryResponseDtoList;
    }

    @Override
    public List<HobbyHistoryResponseDto> findOperatings(Long userId) {

        List<HobbyHistoryResponseDto> hobbyHistoryResponseDtoList = queryFactory.select(Projections.constructor(HobbyHistoryResponseDto.class,
                        hobby.id,
                        hobby.baseAddress.id,
                        category.id,
                        hobby.title,
                        hobby.endDate,
                        hobby.isClosed,
                        hobby.curNum,
                        hobby.maxNum,
                        ExpressionUtils.as(
                                JPAExpressions
                                        .select(count(hobbyFavorite.id))
                                        .from(hobbyFavorite)
                                        .where(hobbyFavorite.hobby.id.eq(hobby.id)),"wishlistNum"
                        ),
                        hobby.rowAgeLimit,
                        hobby.highAgeLimit,
                        hobby.sexLimit,
                        hobby.hobbyImage,
                        hobbyHistory.approveStatus
                ))
                .from(hobbyHistory)
                .innerJoin(hobbyHistory.hobby, hobby)
                .innerJoin(hobby.category, category)
                .where(hobbyHistory.member.id.eq(userId)
                        ,hobby.isClosed.eq(false) // 안닺힘
                        ,hobbyHistory.isPromoter.eq(true)) // 방장

                .fetch();

        return hobbyHistoryResponseDtoList;
    }

    public HobbyHistoryDto findByHobbyAndMemberIdDto(Long hobbyId, Long memberId){
        HobbyHistoryDto hobbyHistoryDto = queryFactory
                .select(Projections.constructor(HobbyHistoryDto.class,
                        hobbyHistory.id,
                        hobbyHistory.hobby.id,
                        hobbyHistory.member.id,
                        hobbyHistory.isPromoter,
                        hobbyHistory.approveStatus
                ))
                .from(hobbyHistory)
//                .innerJoin(hobbyHistory.hobby,hobby)
//                .innerJoin(hobbyHistory.member,member)
                .where(hobbyHistory.hobby.id.eq(hobbyId),
                        hobbyHistory.member.id.eq(memberId),
                        hobbyHistory.approveStatus.eq(ApproveStatus.READY))
                .fetchOne();
        return hobbyHistoryDto;
    }

    @Override
    public HobbyHistoryDto findByIdDto(Long hobbyHistoryId) {
        HobbyHistoryDto hobbyHistoryDto = queryFactory
                .select(Projections.constructor(HobbyHistoryDto.class,
                        hobbyHistory.id,
                        hobby.id,
                        member.id,
                        hobbyHistory.isPromoter,
                        hobbyHistory.approveStatus
                        ))
                .from(hobbyHistory)
                .innerJoin(hobbyHistory.hobby,hobby)
                .innerJoin(hobbyHistory.member,member)
                .where(hobbyHistory.id.eq(hobbyHistoryId))
                .fetchOne();
        return hobbyHistoryDto;
    }

    @Override
    public boolean DuplicateCheck(Long memberId, Long hobbyId) {
        int result = queryFactory.selectFrom(hobbyHistory)
                .where(hobbyHistory.member.id.eq(memberId)
                        ,hobbyHistory.hobby.id.eq(hobbyId)).fetch().size();
        if(result == 0) return false; // 신규
        else return true; // 이미 있음
    }

    @Override
    public Long findOwnerId(Hobby hobby) {
        Long ownerId = queryFactory
                .select(hobbyHistory.member.id)
                .from(hobbyHistory)
                .where(hobbyHistory.hobby.eq(hobby).and(hobbyHistory.isPromoter.eq(TRUE)))
                .fetchOne();
        return ownerId;
    }


}
