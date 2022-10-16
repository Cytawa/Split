package com.project.split.service;

import com.project.split.entities.Bill;
import com.project.split.repository.BillRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class BillService {


    private final BillRepo billRepo;
    public Bill save(Bill bill) {

        return billRepo.save(bill);
    }

    public Bill findByName    (String billName) {
        return billRepo.findByNameBill(billName);
    }

    public List<Bill> findAll() {
        return billRepo.findAll();
    }

    public boolean deleteByName(String billName) {
        billRepo.delete(findByName(billName));
        return true;
    }
}
