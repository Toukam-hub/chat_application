package com.neambo.demowebsocket.controller;

import com.neambo.demowebsocket.entity.ChatMessage;
import com.neambo.demowebsocket.resource.ChatMessageResponse;
import com.neambo.demowebsocket.service.message.ChatMessageService;
import com.neambo.demowebsocket.service.message.ListChatMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageService chatMessageService;
    private final ListChatMessage listChatMessage;

    public ChatController(
            SimpMessagingTemplate messagingTemplate, ChatMessageService chatMessageService,
            ListChatMessage listChatMessage) {
        this.messagingTemplate = messagingTemplate;
        this.chatMessageService = chatMessageService;
        this.listChatMessage = listChatMessage;
    }

    @MessageMapping
    public void processMessage(@Payload ChatMessage chatMessage) {
        var saveMessage = chatMessageService.save(chatMessage);
        messagingTemplate.convertAndSendToUser(
                chatMessage.getRecipientId(),
                "/queue/message",
                ChatMessageResponse.builder()
                        .id(saveMessage.getId())
                        .content(saveMessage.getContent())
                        .senderId(saveMessage.getSenderId())
                        .recipientId(saveMessage.getRecipientId())
                        .build()
        );
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessage>> listChatMessage(
            @PathVariable String senderId,
            @PathVariable String recipientId
    ) {
        return ResponseEntity.ok(listChatMessage.execute(senderId, recipientId));
    }

}

