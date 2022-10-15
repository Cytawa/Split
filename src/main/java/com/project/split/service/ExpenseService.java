package com.project.split.service;


import com.project.split.entities.Bill;
import com.project.split.entities.Expense;
import com.project.split.entities.User;
import com.project.split.exception.NoIdException;

import com.project.split.repository.BillRepo;
import com.project.split.repository.ExpenseRepo;
import com.project.split.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepo expenseRepo;
    private final BillRepo billRepo;
    private final BillService billService;
    private final UserRepo userRepo;
    private final UserService userService;


    public Expense findById(Long id) {
        return expenseRepo.findById(id)
                .orElseThrow(() -> new NoIdException("Expense with given id not found"));
    }

    public Expense findByName(String name) {
        return expenseRepo.findByNameExpanse(name)
                .orElseThrow(() -> new NoIdException("Expense with given id not found"));
    }

    public List<Expense> findAll() {
        return expenseRepo.findAll();
    }

    public boolean deleteById(Long id) {
        expenseRepo.delete(findById(id));
        return true;
    }

    public Expense save(Expense expense) {

        //expense.setBill(billRepo.findByNameBill(expense.getBill().getNameBill()));

        int sum = userRepo.findByUsername(expense.getWhoPay()).getSumOfPay();
        expense.setSplitExpense(expense.getSumExpense() / expense.getUsers().size());
        sum -= (expense.getSumExpense());

        userRepo.findByUsername(expense.getWhoPay()).setSumOfPay(sum);
        Set<User> users = new HashSet<>();
        for (User user : expense.getUsers()) {
            users.add(userRepo.findByUsername(user.getUsername()));
        }
        expense.setUsers(users);
        for(User user:expense.getUsers()){
            user.setSumOfPay(user.getSumOfPay()+(expense.getSumExpense() / expense.getUsers().size()));

        }

        expense.setDate(LocalDate.now());
        return expenseRepo.save(expense);
    }

    public Optional<Expense> findAllByUsernameAndBill_NameBill(String userName, String billName) {
        //Optional<User> user= userRepo.findByUsername(userName);
        // Optional<Bill> bill = billRepo.findByNameBill(billName);
        return userRepo.findAllByUsernameAndBill_NameBill(userName, billName);
    }

    public List<Integer> findExpenseByBillId(Long id) {
        return expenseRepo.findExpenseByBillId(id);

    }
}
