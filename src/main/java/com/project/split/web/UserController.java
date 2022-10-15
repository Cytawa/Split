package com.project.split.web;

import com.project.split.entities.*;

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
    private final UserMapper userMapper;

    @GetMapping("/{name}")
    //@PreAuthorize("authentication.principal.id==#id")
    public ResponseEntity<User> getByName(@PathVariable final String name) {
        return ResponseEntity.ok(userService.findByName(name));
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signup(@RequestBody User user) {
        log.info("SIGNUP");
        return ResponseEntity.ok(userMapper.toDTO(userService.save(user)));

    }

    @PatchMapping("/setadmin/{name}")
    public ResponseEntity<String> giveAdminRole(@PathVariable("name") String name) {

        return ResponseEntity.ok(userService.setAdmin(name));
    }

    @PatchMapping("/setbill/{nameuser}/{namebill}")
    public ResponseEntity<String> setBill(@PathVariable String nameuser, @PathVariable String namebill) {

        return ResponseEntity.ok(userService.setBill(nameuser, namebill));
    }

    @DeleteMapping("/delete/{name}")
    //@PreAuthorize("authentication.principal.id==#id")
    public ResponseEntity<Boolean> delete(@PathVariable String name) {
        return ResponseEntity.ok(userService.deleteByName(name));
    }
}
