package com.project.split.service;

import com.project.split.entities.Role;
import com.project.split.entities.User;
import com.project.split.entities.UserDTO;
import com.project.split.entities.UserMapper;
import com.project.split.repository.BillRepo;
import com.project.split.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final BillRepo billRepo;

    private static final Role USER = new Role(2L, "USER");
    private static final Role ADMIN = new Role(1L, "ADMIN");

    public User save(User user) {
        if (billRepo.findByNameBill(user.getBill().getNameBill()).getUsers().isEmpty()) {
            user.getUserRoles().add(ADMIN);
            user.getUserRoles().add(USER);
        } else {
            user.getUserRoles().add(USER);
        }
        return userRepo.save(user);
    }

    public String setBill(String nameuser, String namebill) {
        User user = userRepo.findByUsername(nameuser);
        user.setBill(billRepo.findByNameBill(namebill));
        userRepo.save(user);
        return "User added to Bill";
    }

    public String setAdmin(String name) {
        User user = userRepo.findByUsername(name);
        user.getUserRoles().add(ADMIN);
        userRepo.save(user);
        return "Role changed!!";
    }

    public User findByName(String name) {
        return userRepo.findByUsername(name);

    }

    public List<User> findUsersByBillNameBill(String name) {
        return userRepo.findUsersByBillNameBill(name);
    }

    public boolean deleteByName(String name) {
        userRepo.delete(findByName(name));
        return true;
    }
}

