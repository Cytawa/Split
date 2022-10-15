package com.project.split.repository;
import com.project.split.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepo extends JpaRepository<Bill, Long> {

   Bill findByNameBill(String name);





}