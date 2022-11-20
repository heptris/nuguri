package com.ssafy.nuguri.domain.alarm;

import com.ssafy.nuguri.alarm.dto.AlarmDto;
import com.ssafy.nuguri.domain.BaseEntity;
import com.ssafy.nuguri.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Entity
public class Alarm extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alarm_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    private String title;
    private String content;
    private Boolean isRead;
    // 취미방 참가 신청자
    private Long participantId;

    // 취미방 참가 신청자 프로필
    private String participantImage;

    // 취미 방 Id
    private Long hobbyId;

    public AlarmDto toAlarmDto() {
        return AlarmDto.builder().alarmId(id).content(content).title(title).isRead(isRead).participantId(participantId)
                .participantImage(participantImage).hobbyId(hobbyId)
                .build();
    }

    public void changeIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

}
