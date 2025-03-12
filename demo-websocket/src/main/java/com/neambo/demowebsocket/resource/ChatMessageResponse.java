package com.neambo.demowebsocket.resource;

import lombok.Builder;

@Builder
public record ChatMessageResponse(
        String id,
        String senderId,
        String recipientId,
        String content
) {
}
