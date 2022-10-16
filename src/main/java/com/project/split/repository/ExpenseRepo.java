package com.project.split.repository;


import com.project.split.entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ExpenseRepo extends JpaRepository<Expense, Long> {

    Optional<Expense> findByNameExpanse(String name);

    /**
     * use of native query to search from the database of sums of expenses in a given bill
     */
    @Query(nativeQuery = true, value = "select sum_expense from expense where bill_id=?")
    List<Integer> findExpenseByBillId(Long id);

    /**
     * use of native query to search from the database of split expenses in a given bill and who pay
     */
    @Query(nativeQuery = true, value = "select split_expense from expense where who_pay= ?1 and bill_id= ?2")
    List<Integer> findExpenseUserName(String name, Long billid);

    /**
     * use of native query to search from the database of sums of expenses in a given bill and who pay
     */
    @Query(nativeQuery = true, value = "select sum_expense from expense where who_pay= ?1 and bill_id= ?2")
    List<Integer> findExpensesByWhoPay(String name, Long billid);
}








