package com.neambo.demowebsocket.service.chatroom;

import com.neambo.demowebsocket.entity.ChatRoom;
import com.neambo.demowebsocket.repository.ChatRoomRepository;
import com.neambo.demowebsocket.resource.ChatRoomRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRommService {

    private final ChatRoomRepository chatRoomRepository;

    public Optional<String> getChatRommId(ChatRoomRequest request) {
        return chatRoomRepository.findBySenderIdAndRecipientId(request.sender(), request.recipient())
                .map(ChatRoom::getChatId )
                .or(
                        () -> {
                            if (request.createNewRoomIfNoExist()) {
                                var chatId = creatChat(request.sender(), request.recipient());
                               return Optional.of(chatId);
                            }
                            return Optional.empty();
                        }
                );
    }

    private String creatChat(String sender, String recipient) {
        var chatId = String.format("%s_s%", sender, recipient);

        ChatRoom senderRecipient = ChatRoom.builder()
                .chatId(chatId)
                .senderId(sender)
                .recipientId(recipient)
                .build();

        ChatRoom recipientSender = ChatRoom.builder()
                .chatId(chatId)
                .senderId(recipient)
                .recipientId(sender)
                .build();

        chatRoomRepository.save(senderRecipient);
        chatRoomRepository.save(recipientSender);

        return chatId;
    }
}
