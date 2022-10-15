package com.project.split.repository;

import com.project.split.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BillRepo extends JpaRepository<Bill, Long> {

   Bill findByNameBill(String name);
}