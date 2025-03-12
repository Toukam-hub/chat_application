package com.neambo.demowebsocket.resource;

public record ChatRoomRequest(
        String sender,
        String recipient,
        boolean createNewRoomIfNoExist
) {
}
