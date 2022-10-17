package com.project.split.service;

import com.project.split.entities.Role;
import com.project.split.entities.User;
import com.project.split.repository.BillRepo;
import com.project.split.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService  {
    private final UserRepo userRepo;
    private final BillRepo billRepo;
    private final PasswordEncoder passwordEncoder;

    private static final Role USER = new Role(2L, "USER");
    private static final Role ADMIN = new Role(1L, "ADMIN");
/**
 * if the user is the first in a given bill,
 * the roles of user and administrator are assigned,
 * if there is another, only the role of the user
 */
public User save(User user) {
    String encodedPassword = passwordEncoder.encode(user.getPassword());

    user.setPassword(encodedPassword);
    if (billRepo.findByNameBill(user.getBill().getNameBill()).getUsers().isEmpty()) {
        user.getUserRoles().add(ADMIN);
        user.getUserRoles().add(USER);
    } else {
        user.getUserRoles().add(USER);
    }
    return userRepo.save(user);
}
    //TODO show only the bill to which the logged in user belongs

    public String setBill(String nameuser, String namebill) {
        User user = userRepo.findByUsername(nameuser);
        user.setBill(billRepo.findByNameBill(namebill));
        userRepo.save(user);
        return "User added to Bill";
    }

    /**adding ADMIN role to user*/
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


