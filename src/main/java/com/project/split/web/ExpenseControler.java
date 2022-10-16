package com.project.split.web;

import com.project.split.entities.*;
import com.project.split.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/split/expense")
public class ExpenseControler {


    private final ExpenseService expenseService;


    @GetMapping("/{name}")
    String findExpenseByBillName(@PathVariable String name) {

        return "Sum of expenses in " + name + " "+expenseService.findExpenseByBillName(name);
    }

    @PostMapping
    public ResponseEntity<Expense> save(@RequestBody Expense expense) {
        return ResponseEntity.ok(expenseService.save(expense));
    }

    @PatchMapping("/setbill/{nameexpensive}/{namebill}")
    public ResponseEntity<String> setBill(@PathVariable String nameexpensive, @PathVariable String namebill) {
        return ResponseEntity.ok(expenseService.setBill(nameexpensive, namebill));
    }
    @DeleteMapping("/delete/{name}")
    public ResponseEntity<Boolean> delete(@PathVariable String name) {
        return ResponseEntity.ok(expenseService.deleteByName(name));
    }
}
