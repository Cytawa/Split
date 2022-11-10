package com.project.split.entities;

import java.util.List;


public class UserDTO {

    private String name;
    private String password;
    private int sumOfPay;
    private List<Role> userRoles;
    private Bill bill;

  public UserDTO(String name, String password, int sumOfPay, List<Role> userRoles, Bill bill) {
    this.name = name;
    this.password = password;
    this.sumOfPay = sumOfPay;
    this.userRoles = userRoles;
    this.bill = bill;
  }

  public UserDTO() {}

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

