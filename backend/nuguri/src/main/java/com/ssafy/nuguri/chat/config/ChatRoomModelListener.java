package com.ssafy.nuguri.chat.config;

import com.ssafy.nuguri.chat.domain.ChatMessage;
import com.ssafy.nuguri.chat.domain.ChatRoom;
import com.ssafy.nuguri.chat.service.SequenceGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ChatRoomModelListener extends AbstractMongoEventListener<ChatRoom> {

    private final SequenceGeneratorService generatorService;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<ChatRoom> event) {
        event.getSource().setId(generatorService.generateSequence(ChatRoom.SEQUENCE_NAME));
    }
}
