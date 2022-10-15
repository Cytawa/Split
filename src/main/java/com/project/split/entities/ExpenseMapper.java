package com.project.split.entities;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class ExpenseMapper {
    /*public ExpenseDTO toDTO(Expense expense) {

        String nameExpanse = expense.getNameExpanse();
        Integer sumExpense = expense.getSumExpense();
        String billName = expense.getBill().getNameBill();
        //Set<User> users = expense.getUsers();


        return new ExpenseDTO(nameExpanse, sumExpense, billName, users);
    }*/
}
