package com.project.split.entities;

import lombok.RequiredArgsConstructor;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public  UserDTO toDTO(User user) {

        String userName= user.getUsername();

        String password=user.getPassword();

        return new UserDTO(userName,  password);
    }
}
