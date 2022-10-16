package com.project.split.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * an entity describing the event in which payments need to be divided between participants
 */
@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor

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

}




