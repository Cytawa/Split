package com.project.split.web;

import com.project.split.entities.*;
import com.project.split.service.BillService;
import com.project.split.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/split/user")
public class UserController {


    private final UserService userService;
    private final BillService billService;
    private static final Role ADMIN = new Role(1L, "ADMIN");


    @GetMapping("/{name}")
    //@PreAuthorize("authentication.principal.id==#id")
    public ResponseEntity<User> getByName(@PathVariable final String name) {
        return ResponseEntity.ok(userService.findByName(name));
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody User user) {
        log.info("SIGNUP");
        return ResponseEntity.ok(userService.save(user));

    }

    @PatchMapping("/setadmin/{name}")
    public ResponseEntity<String> giveAdminRole(@PathVariable("name") String name) {
        User user = userService.findByName(name);
        user.getUserRoles().add(ADMIN);
        userService.save(user);
        String ok = "Role changed!!";
        return ResponseEntity.ok(ok);
    }

    @PatchMapping("/setbill/{nameuser}/{namebill}")
    public ResponseEntity<String> setBill(@PathVariable String nameuser, @PathVariable String namebill) {
        User user = userService.findByName(nameuser);
        user.setBill(billService.findByName(namebill));
        userService.save(user);
        String ok = "Set Bill!!";
        return ResponseEntity.ok(ok);
    }

    @DeleteMapping("/delete/{name}")
    //@PreAuthorize("authentication.principal.id==#id")
    public ResponseEntity<Boolean> delete(@PathVariable String name) {
        return ResponseEntity.ok(userService.deleteByName(name));
    }
}
