package com.neambo.demowebsocket.service.user;

import com.neambo.demowebsocket.entity.User;
import com.neambo.demowebsocket.enums.Status;
import com.neambo.demowebsocket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.neambo.demowebsocket.enums.Status.ONLINE;

@Service
@RequiredArgsConstructor
@Slf4j
public class SaveUser {

    private final UserRepository userRepository;

    public User saveUser(User user) {
        log.info("Inside Save Method {}", user);
        user.setStatus(ONLINE);

       return userRepository.save(user);
    }
}
