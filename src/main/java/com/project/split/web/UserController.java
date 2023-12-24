package com.project.split.web;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.jayway.jsonpath.internal.JsonContext;
import com.project.split.entities.*;

import com.project.split.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/split/user")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;



    @GetMapping("/{name}")
    public ResponseEntity<User> getByName(@PathVariable final String name) {
        return ResponseEntity.ok(userService.findByName(name));
    }

  @GetMapping("/")
  public ResponseEntity<List<User>> getAllName() {
    return ResponseEntity.ok(userService.findAllName());
  }

  @GetMapping("/bill/{bill}")
  public ResponseEntity<List<User>> getByBill(@PathVariable final String bill) {
    return ResponseEntity.ok(userService.findUsersByBillNameBill(bill));
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

  @PostMapping(value = "/setbill")
  public ResponseEntity<String> setBill(@RequestBody User user) {
    return ResponseEntity.ok(userService.setBill(user.getUsername(), user.getBill().getNameBill()));
  }

  @PostMapping(value = "/setexp/{username}/{expname}")
  public ResponseEntity<Boolean> setExp(@PathVariable String username, String expname) {
    userService.addExpensive(username, expname);
    return ResponseEntity.ok(true);
  }

  @DeleteMapping("/delete/{name}")
  public ResponseEntity<Boolean> delete(@PathVariable String name) {
    return ResponseEntity.ok(userService.deleteByName(name));
  }
}
