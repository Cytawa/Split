package com.project.split.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * an entity describing the event in which payments need to be divided between participants
 */
@Entity

public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String nameBill;
    /**
     * expenses assigned to this bill
     */
    @JsonIgnore
    @OneToMany(mappedBy = "bill")
    private List<Expense> expenses;
    /**
     * users assigned to this bill
     */
    @JsonIgnore
    @OneToMany(mappedBy = "bill")
    private List<User> users;

  public Bill() {}

  public Long getId() {
    return this.id;
  }

  public @NonNull String getNameBill() {
    return this.nameBill;
  }

  public List<Expense> getExpenses() {
    return this.expenses;
  }

  public List<User> getUsers() {
    return this.users;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setNameBill(@NonNull String nameBill) {
    this.nameBill = nameBill;
  }

  @JsonIgnore
  public void setExpenses(List<Expense> expenses) {
    this.expenses = expenses;
  }

  @JsonIgnore
  public void setUsers(List<User> users) {
    this.users = users;
  }

  public String toString() {
    return "Bill(id="
        + this.getId()
        + ", nameBill="
        + this.getNameBill()
        + ", expenses="
        + this.getExpenses()
        + ", users="
        + this.getUsers()
        + ")";
  }
}




