package com.neambo.demowebsocket.entity;

import com.neambo.demowebsocket.enums.Status;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class User {
    @Id
    private String nickname;
    private String fullname;
    private Status status;

}
