package com.project.split.web;

import com.project.split.entities.Bill;
import com.project.split.entities.User;
import com.project.split.service.BillService;
import com.project.split.service.ExpenseService;
import com.project.split.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/split/bill")
public class BillController {

    private final BillService billService;
    private final UserService userService;
    private final ExpenseService expenseService;

    @GetMapping("/{username}/{billname}")
    public ResponseEntity<String> findExpenseByUserName(@PathVariable String username,@PathVariable String billname) {

        String ok = "Sum of expenses of " + username +" bill id "+billname+ " is " + expenseService.sumOfExpensesByUserName(username, billname);
        return ResponseEntity.ok(ok);
    }

    @GetMapping
    public List<Bill> findAll() {
        return billService.findAll();
    }


    @PostMapping("/save")
    public ResponseEntity<Bill> save(@RequestBody Bill bill) {
        return ResponseEntity.ok(billService.save(bill));
    }


    @DeleteMapping("{name}")
    public ResponseEntity<Boolean> delete(@PathVariable String name) {
        return ResponseEntity.ok(billService.deleteByName(name));
    }

    @GetMapping("/users/{name}")
    public List<User> getByUserByBillName(@PathVariable String name) {
        return userService.findUsersByBillNameBill(name);
    }
}






