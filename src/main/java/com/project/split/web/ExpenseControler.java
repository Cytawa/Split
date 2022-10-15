package com.project.split.web;

import com.project.split.entities.*;
import com.project.split.service.BillService;
import com.project.split.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/split/expense")
public class ExpenseControler {


    private final ExpenseService expenseService;
    private final BillService billService;


    @GetMapping("/{name}")
    Integer findExpenseByBillName(@PathVariable String name) {
        List<Integer> expenses = expenseService
                .findExpenseByBillId(billService
                        .findByName(name).getId());
        int sum = 0;
        for (Integer number : expenses) {
            sum += number;
        }
        return sum;
    }

    @PostMapping
    public ResponseEntity<Expense> saveAndFlush(@RequestBody Expense expense) {
        return ResponseEntity.ok(expenseService.save(expense));
    }


}
