package com.project.split.repository;


import com.project.split.entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepo extends JpaRepository<Expense, Long> {

    Optional<Expense> findByNameExpanse(String name);

    @Query(nativeQuery = true, value = "select sum_expense  from expense where bill_id=?")
    List<Integer> findExpenseByBillId(Long id);


    @Query(nativeQuery = true, value = "select expense.split_expense from expense INNER JOIN user where username=?")
    List<Integer> findExpenseUserName(String name);

    @Query(nativeQuery = true, value = "select sum_expense from expense where who_pay=?")
    List<Integer> findExpensesByWhoPay(String name);
}








