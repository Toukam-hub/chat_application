package com.neambo.demowebsocket.service.user;

import com.neambo.demowebsocket.entity.User;
import com.neambo.demowebsocket.enums.Status;
import com.neambo.demowebsocket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersConnected {

    private final UserRepository userRepository;

    public List<User> findConnectedUsers() {
        return userRepository.findAllByStatus(Status.ONLINE);
    }
}
