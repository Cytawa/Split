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
        int sum = userRepo.findByUsername(expense.getWhoPay()).getSumOfPay();
        expense.setSplitExpense(expense.getSumExpense() / expense.getUsers().size());
        sum -= (expense.getSumExpense());
        userRepo.findByUsername(expense.getWhoPay()).setSumOfPay(sum);
        Set<User> users = new HashSet<>();
        for (User user : expense.getUsers()) {
            users.add(userRepo.findByUsername(user.getUsername()));
        }
        expense.setUsers(users);
        for (User user : expense.getUsers()) {
            user.setSumOfPay(user.getSumOfPay() + (expense.getSumExpense() / expense.getUsers().size()));
        }
        expense.setDate(LocalDate.now());
        return expenseRepo.save(expense);
    }

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

    public Integer sumOfExpensesByUserName(String userName) {
        Integer sum = 0;
        Integer sumOfPay = 0;
        for (Integer a : expenseRepo.findExpensesByWhoPay(userName)) {
            sumOfPay += a;
        }
        for (Integer b : expenseRepo.findExpenseUserName(userName)) {
            sum += b;
        }
        return sum - sumOfPay;
    }
}
