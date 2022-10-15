package com.project.split.entities;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public  UserDTO toDTO(User user) {

        String userName= user.getUsername();

        String password=user.getPassword();

        int sumOfPay= user.getSumOfPay();
        List<Role> userRoles=user.getUserRoles();

        Bill bill=user.getBill();

        return new UserDTO(userName,  password, sumOfPay, userRoles, bill);
    }
}
