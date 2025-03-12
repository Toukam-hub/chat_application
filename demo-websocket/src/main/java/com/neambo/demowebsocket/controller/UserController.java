package com.neambo.demowebsocket.controller;

import com.neambo.demowebsocket.entity.User;
import com.neambo.demowebsocket.service.user.DisconnectUser;
import com.neambo.demowebsocket.service.user.SaveUser;
import com.neambo.demowebsocket.service.user.UsersConnected;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {
    private final DisconnectUser disconnectUser;
    private final UsersConnected usersConnected;
    private final SaveUser saveUser;

    public UserController(DisconnectUser disconnectUser, UsersConnected usersConnected, SaveUser saveUser) {
        this.disconnectUser = disconnectUser;
        this.usersConnected = usersConnected;
        this.saveUser = saveUser;
    }

    @MessageMapping("/user.addUser")
    @SendTo("/user/topic")
    public User addUser(@Payload User user) {
        return saveUser.saveUser(user);
    }

    @MessageMapping("/user.disconnectUser")
    @SendTo("/user/topic")
    public User disconnect(@Payload User user) {
        disconnectUser.disconnect(user);
        return user;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findConnectedUsers() {
        return ResponseEntity.ok(usersConnected.findConnectedUsers());
    }
}
