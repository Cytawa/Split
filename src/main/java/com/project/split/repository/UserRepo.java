package com.project.split.repository;


import com.project.split.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface UserRepo extends JpaRepository<User, Long> {


    User findByUsername(String name);

    List<User> findUsersByBillNameBill( String name);

}
