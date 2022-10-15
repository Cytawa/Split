package com.project.split.web;

import com.project.split.entities.Bill;
import com.project.split.entities.User;
import com.project.split.service.BillService;
import com.project.split.service.ExpenseService;
import com.project.split.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/split/bill")
public class BillController {

    private final BillService billService;
    private final UserService userService;
    private final ExpenseService expenseService;

    @GetMapping("/{name}")
    public ResponseEntity<String> findExpenseByBillName(@PathVariable String name) {

        List<Integer> expenses = expenseService
                .findExpenseByBillId(billService
                        .findByName(name).getId());
        int sum = 0;
        for (Integer number : expenses) {
            sum += number;
        }
        String ok = "Sum of expenses in "+name+" is "+sum;
        return ResponseEntity.ok(ok);
    }

    @GetMapping
    public List<Bill> findAll() {
        return billService.findAll();
    }

   // @Secured("ROLE_ADMIN")
    @PostMapping("/save")
    public ResponseEntity<Bill> save(@RequestBody Bill bill) {
        return ResponseEntity.ok(billService.save(bill));
    }

   // @Secured("ROLE_ADMIN")
    @DeleteMapping("{name}")
    public ResponseEntity<Boolean> delete(@PathVariable String name) {
        return ResponseEntity.ok(billService.deleteById(name));
    }
    @GetMapping("/users/{name}")
    public  List<User>  getByUserByBillName(@PathVariable String name) {
        return userService.findUsersByBill_NameBill(name);
    }
}






