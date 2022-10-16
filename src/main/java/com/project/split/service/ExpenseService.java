package com.project.split.service;


import com.project.split.entities.Expense;
import com.project.split.entities.User;
import com.project.split.exception.NoIdException;
import com.project.split.repository.BillRepo;
import com.project.split.repository.ExpenseRepo;
import com.project.split.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepo expenseRepo;
    private final BillRepo billRepo;
    private final UserRepo userRepo;

    /**
     * calculation of the sum of expenses in a given bill
     */

    public String findExpenseByBillName(String name) {
        List<Integer> expenses = expenseRepo
                .findExpenseByBillId(billRepo
                        .findByNameBill(name).getId());
        int sum = 0;
        for (Integer number : expenses) {
            sum += number;
        }
        return "Sum= " + sum;
    }

    public Expense findByName(String name) {
        return expenseRepo.findByNameExpanse(name)
                .orElseThrow(() -> new NoIdException("Expense with given id not found"));
    }

    public boolean deleteByName(String name) {
        expenseRepo.delete(findByName(name));
        return true;
    }

    public Expense save(Expense expense) {
        /**calculating the number of people to divide the expense and the sum after the division*/
        int sum = userRepo.findByUsername(expense.getWhoPay()).getSumOfPay();
        expense.setSplitExpense(expense.getSumExpense() / expense.getUsers().size());
        sum -= (expense.getSumExpense());
        userRepo.findByUsername(expense.getWhoPay()).setSumOfPay(sum);
        /**adding users to expenses*/
        Set<User> users = new HashSet<>();
        for (User user : expense.getUsers()) {
            users.add(userRepo.findByUsername(user.getUsername()));
        }
        expense.setUsers(users);
        /**adding split payment to users*/
        for (User user : expense.getUsers()) {
            user.setSumOfPay(user.getSumOfPay() + (expense.getSumExpense() / expense.getUsers().size()));
        }
        /**adding date of payment*/
        expense.setDate(LocalDate.now());
        return expenseRepo.save(expense);
    }

    /**
     * assigning the expense to a given bill
     */
    public String setBill(String nameExpense, String nameBill) {
        Optional<Expense> expense = expenseRepo.findByNameExpanse(nameExpense);
        if (expense.isPresent()) {
            expense.get().setBill(

                    billRepo.findByNameBill(nameBill));
            expenseRepo.save(expense.get());

            return "Set Bill!!";
        } else {
            return "Name doesn't exist";
        }
    }

    /**calculation of the sum of split payments minus the sum of expenses*/
    public Integer sumOfExpensesByUserName(String userName, String namebill) {
        Integer sum = 0;
        Integer sumOfPay = 0;
        Long billid = billRepo.findByNameBill(namebill).getId();


        for (Integer a : expenseRepo.findExpensesByWhoPay(userName, billid)) {
            sumOfPay += a;
        }
        for (Integer b : expenseRepo.findExpenseUserName(userName, billid)) {
            sum += b;
        }
        return sum - sumOfPay;
    }
}
