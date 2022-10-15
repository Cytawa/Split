package com.project.split.repository;

import com.project.split.entities.Expense;
import com.project.split.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {


    User findByUsername(String name);

    List<User> findUsersByBill_NameBill( String name);

    Optional<Expense> findAllByUsernameAndBill_NameBill(String userName, String billName);
}
