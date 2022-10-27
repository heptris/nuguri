package com.ssafy.nuguri.repository.hobby;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.nuguri.domain.hobby.ApproveStatus;
import com.ssafy.nuguri.domain.hobby.Hobby;
import com.ssafy.nuguri.dto.hobby.HobbyDto;
import com.ssafy.nuguri.dto.hobby.HobbyStatusDto;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

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

    @Override
    public List<HobbyDto> findByRegion(Long RegionId) {
        List<HobbyDto> hobbyDtoList = queryFactory.select(Projections.constructor(HobbyDto.class,
                hobby.id,
                baseAddress.id,
                category.id,
                hobby.title,
                hobby.content,
                hobby.endDate,
                hobby.meetingPlace,
                hobby.isClosed,
                hobby.curNum,
                hobby.maxNum,
                hobby.fee,
                hobby.ageLimit,
                hobby.sexLimit,
                hobby.hobbyImage
                ))
                .from(hobby)
                .innerJoin(hobby.baseAddress, baseAddress)
                .innerJoin(hobby.category, category)
                .where(baseAddress.id.eq(RegionId))
                .fetch();
        return hobbyDtoList;
    }

    @Override
    public List<HobbyDto> findByRegionAndCategory(Long RegionId, Long CategoryId) {
        List<HobbyDto> hobbyDtoList = queryFactory.select(Projections.constructor(HobbyDto.class,
                        hobby.id,
                        baseAddress.id,
                        category.id,
                        hobby.title,
                        hobby.content,
                        hobby.endDate,
                        hobby.meetingPlace,
                        hobby.isClosed,
                        hobby.curNum,
                        hobby.maxNum,
                        hobby.fee,
                        hobby.ageLimit,
                        hobby.sexLimit,
                        hobby.hobbyImage
                ))
                .from(hobby)
                .innerJoin(hobby.baseAddress, baseAddress)
                .innerJoin(hobby.category, category)
                .where(
                    baseAddress.id.eq(RegionId)
                    .and(category.id.eq(CategoryId))
                )
                .fetch();
        return hobbyDtoList;
    }

    @Override
    public List<HobbyDto> findMultipleRegionAndCategory(List<Long> RegionIds, List<Long> CategoryIds) {
        return null;
    }

    @Override
    public HobbyDto hobbyDetail(Long hobbyId) {
        HobbyDto hobbyDto = queryFactory.select(Projections.constructor(HobbyDto.class,
                        hobby.id,
                        baseAddress.id,
                        category.id,
                        hobby.title,
                        hobby.content,
                        hobby.endDate,
                        hobby.meetingPlace,
                        hobby.isClosed,
                        hobby.curNum,
                        hobby.maxNum,
                        hobby.fee,
                        hobby.ageLimit,
                        hobby.sexLimit,
                        hobby.hobbyImage
                ))
                .from(hobby)
                .innerJoin(hobby.baseAddress, baseAddress)
                .innerJoin(hobby.category, category)
                .where(
                        hobby.id.eq(hobbyId)
                )
                .fetchOne();
        return hobbyDto;
    }

    @Override
    public List<HobbyStatusDto> findByMemberIdAndStatus(Long memberId, ApproveStatus approveStatus) {
        List<HobbyStatusDto> hobbyStatusDtoList = queryFactory.select(Projections.constructor(HobbyStatusDto.class,
                        hobby.id,
                        category.id,
                        hobby.title,
                        hobby.endDate,
                        hobby.curNum,
                        hobby.maxNum,
                        hobby.maxNum,
                        hobby.maxNum,
                        hobby.hobbyImage,
                        hobbyHistory.approveStatus
                ))
                .from(hobby)
                .innerJoin(hobby.hobbyHistoryList, hobbyHistory)
                .innerJoin(hobby.hobbyFavoriteList, hobbyFavorite)
                .innerJoin(hobbyHistory.member, member)
                .innerJoin(hobby.category, category)
                .where(
                        member.id.eq(memberId)
                                .and(hobbyHistory.approveStatus.eq(approveStatus))
                )
                .fetch();
        return hobbyStatusDtoList;
    }

    @Override
    public List<HobbyStatusDto> findByMemberIdAndPromoter(Long memberId, boolean isPromoter) {
        List<HobbyStatusDto> hobbyStatusDtoList = queryFactory.select(Projections.constructor(HobbyStatusDto.class,
                        hobby.id,
                        category.id,
                        hobby.title,
                        hobby.endDate,
                        hobby.curNum,
                        hobby.maxNum,
                        hobby.maxNum,
                        hobby.maxNum,
                        hobby.hobbyImage,
                        hobbyHistory.approveStatus
                ))
                .from(hobby)
                .innerJoin(hobby.hobbyHistoryList, hobbyHistory)
                .innerJoin(hobby.hobbyFavoriteList, hobbyFavorite)
                .innerJoin(hobbyHistory.member, member)
                .innerJoin(hobby.category, category)
                .where(
                        member.id.eq(memberId)
                                .and(hobbyHistory.isPromoter.eq(isPromoter))
                )
                .fetch();
        return hobbyStatusDtoList;
    }

    @Override
    public List<HobbyStatusDto> findByMemberIdAndFavorite(Long memberId, boolean isFavorite) {
        List<HobbyStatusDto> hobbyStatusDtoList = queryFactory.select(Projections.constructor(HobbyStatusDto.class,
                        hobby.id,
                        category.id,
                        hobby.title,
                        hobby.endDate,
                        hobby.curNum,
                        hobby.maxNum,
                        hobby.maxNum,
                        hobby.maxNum,
                        hobby.hobbyImage,
                        hobbyHistory.approveStatus
                ))
                .from(hobby)
                .innerJoin(hobby.hobbyHistoryList, hobbyHistory)
                .innerJoin(hobby.hobbyFavoriteList, hobbyFavorite)
                .innerJoin(hobbyHistory.member, member)
                .innerJoin(hobby.category, category)
                .where(
                        member.id.eq(memberId)
                                .and(hobbyFavorite.isFavorite.eq(isFavorite))
                )
                .fetch();
        return hobbyStatusDtoList;
    }

}
