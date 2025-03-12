package com.neambo.demowebsocket.repository;

import com.neambo.demowebsocket.entity.User;
import com.neambo.demowebsocket.enums.Status;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    List<User> findAllByStatus(Status status);
}
