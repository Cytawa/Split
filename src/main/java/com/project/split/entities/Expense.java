package com.project.split.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String nameExpanse;
    private Integer sumExpense;
    private Integer splitExpense;
    private LocalDate date;
    private String whoPay;
    @JsonIgnore
    @ManyToOne
    Bill bill;

  //  @ManyToMany (mappedBy = "expenses",  cascade = CascadeType.ALL)
    //private Set<User> users;
  @ManyToMany
  @JoinTable(
          name = "user_expense",
          joinColumns = @JoinColumn(name = "expense_id"),
          inverseJoinColumns = @JoinColumn(name = "user_id"))
  Set<User> users;
}
