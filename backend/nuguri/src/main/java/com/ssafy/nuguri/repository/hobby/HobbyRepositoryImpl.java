package com.ssafy.nuguri.repository.hobby;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.nuguri.domain.hobby.ApproveStatus;
import com.ssafy.nuguri.dto.hobby.HobbyDto;
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

public class HobbyRepositoryImpl implements HobbyRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public HobbyRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }



    public List<HobbyHistoryResponseDto> findByRegionAndCategory(Long RegionId, Long CategoryId) {
        List<HobbyHistoryResponseDto> hobbyHistoryResponseDtoList = queryFactory.select(Projections.constructor(HobbyHistoryResponseDto.class,
                        hobby.id,
                        baseAddress.id,
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
                .from(hobby)
                .innerJoin(hobby.hobbyHistoryList, hobbyHistory)
                .innerJoin(hobbyHistory.member, member)
                .innerJoin(hobby.category, category)
                .innerJoin(hobby.baseAddress, baseAddress)
                .where(RegionEq(RegionId),
                        CategoryEq(CategoryId),
                        hobby.isClosed.eq(Boolean.FALSE)
                )
                .fetch();
        return hobbyHistoryResponseDtoList;
    }

    private BooleanExpression RegionEq(Long RegionId) {
        return RegionId == null ? null : baseAddress.id.eq(RegionId);
    }
    private BooleanExpression CategoryEq(Long CategoryId) {
        return CategoryId == null ? null : category.id.eq(CategoryId);
    }

    @Override
    public HobbyDto hobbyDetail(Long hobbyId) {
        HobbyDto hobbyDto = queryFactory.select(Projections.constructor(HobbyDto.class,
                        hobby.id,
                        baseAddress.id,
                        category.id,
                        hobby.member.id,
                        hobby.member.nickname,
                        hobby.title,
                        hobby.content,
                        hobby.endDate,
                        hobby.meetingPlace,
                        hobby.isClosed,
                        hobby.curNum,
                        hobby.maxNum,
                        hobby.fee,
                        hobby.rowAgeLimit,
                        hobby.highAgeLimit,
                        hobby.sexLimit,
                        hobby.hobbyImage,
                        member.profileImage
                ))
                .from(hobby)
                .innerJoin(hobby.baseAddress, baseAddress)
                .innerJoin(hobby.category, category)
                .innerJoin(hobby.member,member)
                .where(
                        hobby.id.eq(hobbyId)
                )
                .fetchOne();
        return hobbyDto;
    }


    @Override
    public List<HobbyHistoryResponseDto> findByMemberIdAndStatus(Long memberId, ApproveStatus approveStatus) {
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
                .from(hobby)
                .innerJoin(hobby.hobbyHistoryList, hobbyHistory)
                .innerJoin(hobbyHistory.member, member)
                .innerJoin(hobby.category, category)
                .where(
                        member.id.eq(memberId)
                                .and(hobbyHistory.approveStatus.eq(approveStatus))
                )
                .fetch();
        return hobbyHistoryResponseDtoList;
    }

    @Override
    public List<HobbyHistoryResponseDto> findByMemberIdAndPromoter(Long memberId, boolean isPromoter) {
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
                .from(hobby)
                .innerJoin(hobby.hobbyHistoryList, hobbyHistory)
                .innerJoin(hobbyHistory.member, member)
                .innerJoin(hobby.category, category)
                .where(
                        member.id.eq(memberId)
                                .and(hobbyHistory.isPromoter.eq(isPromoter))
                )
                .fetch();
        return hobbyHistoryResponseDtoList;
    }

    @Override
    public List<HobbyHistoryResponseDto> findByMemberIdAndFavorite(Long memberId, boolean isFavorite) {
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
                .from(hobby)
//                .leftJoin(hobby.hobbyHistoryList, hobbyHistory)
//                .innerJoin(hobby.hobbyFavoriteList, hobbyFavorite)
//                .innerJoin(hobbyFavorite.member, member)
//                .innerJoin(hobby.category, category)
                .where(
                        member.id.eq(memberId)
                                .and(hobbyFavorite.isFavorite.eq(isFavorite))
                )
                .fetch();
        return hobbyHistoryResponseDtoList;
    }

}
