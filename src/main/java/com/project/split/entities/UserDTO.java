package com.project.split.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String name;
    private String password;
    private int sumOfPay;
    private List<Role> userRoles;
    private Bill bill;


    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getSumOfPay() {
        return sumOfPay;
    }

    public List<Role> getUserRoles() {
        return userRoles;
    }

    public Bill getBill() {
        return bill;
    }
}

