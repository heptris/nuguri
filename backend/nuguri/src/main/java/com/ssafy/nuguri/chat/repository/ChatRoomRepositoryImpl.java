//package com.ssafy.nuguri.chat.repository;
//
//import com.querydsl.mongodb.morphia.MorphiaQuery;
//import com.ssafy.nuguri.chat.domain.ChatRoom;
//import com.ssafy.nuguri.chat.domain.QChatRoom;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoOperations;
//import org.springframework.data.mongodb.repository.support.QuerydslRepositorySupport;
//
//import javax.sql.DataSource;
//import java.util.List;
//
//public class ChatRoomRepositoryImpl extends QuerydslRepositorySupport implements ChatRoomRepositoryCustom  {
//
//    private static final QChatRoom chatRoom = QChatRoom.chatRoom;
//
//    public ChatRoomRepositoryImpl(MongoOperations operations) {
//        super(operations);
//    }
//
//
//    @Override
//    public List<ChatRoom> findAlll() {
//        List<ChatRoom> chatRooms = from(chatRoom).fetch();
//        return chatRooms;
//    }
//}
