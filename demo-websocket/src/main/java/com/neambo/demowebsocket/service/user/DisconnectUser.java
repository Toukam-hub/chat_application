package com.neambo.demowebsocket.service.user;

import com.neambo.demowebsocket.entity.User;
import com.neambo.demowebsocket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.neambo.demowebsocket.enums.Status.OFFLINE;

@Service
@RequiredArgsConstructor
public class DisconnectUser {

    private final UserRepository userRepository;

    public void disconnect(User user) {
        var storedUser = userRepository.findById(user.getNickname()).orElse(null);

        if (storedUser != null) {
            storedUser.setStatus(OFFLINE);
            userRepository.save(storedUser);
        }
    }
}
