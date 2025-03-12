package com.neambo.demowebsocket.service.message;

import com.neambo.demowebsocket.entity.ChatMessage;
import com.neambo.demowebsocket.repository.ChatRoomRepository;
import com.neambo.demowebsocket.repository.MessageRepository;
import com.neambo.demowebsocket.resource.ChatRoomRequest;
import com.neambo.demowebsocket.service.chatroom.ChatRommService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListChatMessage {
    private final MessageRepository messageRepository;
    private final ChatRommService chatRommService;

    public List<ChatMessage> execute(String senderId, String recipientId) {
        var request = new ChatRoomRequest(senderId, recipientId, false);
        return chatRommService.getChatRommId(request)
                .map(messageRepository::findByChatId).orElse(new ArrayList<>());
    }
}
