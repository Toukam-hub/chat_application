package com.neambo.demowebsocket.repository;

import com.neambo.demowebsocket.entity.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {
   Optional<ChatRoom> findBySenderIdAndRecipientId(String sender, String recipient);
}
