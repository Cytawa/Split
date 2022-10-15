package com.project.split.service;

import com.project.split.entities.Expense;
import com.project.split.entities.Role;
import com.project.split.entities.User;
import com.project.split.repository.ExpenseRepo;
import com.project.split.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.BeanDefinitionDsl;
import org.springframework.stereotype.Service;
import com.project.split.exception.NoIdException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private static final Role USER = new Role(2L, "USER");

    public User save(User user) {
        user.getUserRoles().add(USER);
        user.setSumOfPay(0);
        return userRepo.save(user);
    }



    public User findByName(String name) {
        return userRepo.findByUsername(name);
               // .orElseThrow(()-> new NoIdException("User with given id not found"));
    }

    public List<User> findUsersByBill_NameBill(String name) {
        return userRepo.findUsersByBill_NameBill(name);
    }

    public boolean deleteByName(String name) {
        userRepo.delete(findByName(name));
        return true;
    }
}

