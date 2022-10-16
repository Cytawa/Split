package com.project.split.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
public class Expense {
    /**
     * entity that stores information about the expense (name, sum, who participates, who pays)
     */
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Column(unique = true)
    private String nameExpanse;
    private Integer sumExpense;
    private Integer splitExpense;
    //TODO adding the ability to change the currency
    private LocalDate date;
    private String whoPay;
    /**
     * assigning the expense to the accounts
     */
    @JsonIgnore
    @ManyToOne
    Bill bill;

    /**
     * assigning the expense to the users
     */
    @ManyToMany
    @JoinTable(
            name = "user_expense",
            joinColumns = @JoinColumn(name = "expense_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    Set<User> users;

}
