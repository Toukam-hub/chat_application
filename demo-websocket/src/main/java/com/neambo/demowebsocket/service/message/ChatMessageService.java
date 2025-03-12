package com.neambo.demowebsocket.service.message;

import com.neambo.demowebsocket.entity.ChatMessage;
import com.neambo.demowebsocket.exception.ResourceNotFoundException;
import com.neambo.demowebsocket.repository.MessageRepository;
import com.neambo.demowebsocket.resource.ChatRoomRequest;
import com.neambo.demowebsocket.service.chatroom.ChatRommService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final MessageRepository messageRepository;
    private final ChatRommService chatRommService;

    public ChatMessage save(ChatMessage chatMessage) {
        var request = new ChatRoomRequest(chatMessage.getSenderId(), chatMessage.getRecipientId(), true);
        var chatId = chatRommService.getChatRommId(request).orElseThrow(
                () -> new ResourceNotFoundException("Chat not fount")
        );

        chatMessage.setChatId(chatId);
        return messageRepository.save(chatMessage);
    }
}
