package com.project.split.repository;

import com.project.split.entities.Bill;
import com.project.split.entities.Expense;
import com.project.split.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ExpenseRepo extends JpaRepository<Expense, Long> {

    //Optional<Expense> findExpensesByUser
    Optional<Expense> findByNameExpanse(String name);

   @Query(nativeQuery = true, value = "select sum_expense  from expense where bill_id=?")
        List<Integer>findExpenseByBillId(Long id);
   }








