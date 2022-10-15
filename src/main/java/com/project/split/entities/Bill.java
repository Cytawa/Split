package com.project.split.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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
    @JsonIgnore
    @OneToMany(mappedBy = "bill")
    private List<Expense> expenses;
    @JsonIgnore
    @OneToMany(mappedBy = "bill")
    private List<User> users;

}




