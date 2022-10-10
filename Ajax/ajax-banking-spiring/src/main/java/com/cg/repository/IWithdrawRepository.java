package com.cg.repository;

import com.cg.model.Customer;
import com.cg.model.Withdraw;
 import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IWithdrawRepository extends JpaRepository<Withdraw, Long> {
    void deleteById(Long id);
}
