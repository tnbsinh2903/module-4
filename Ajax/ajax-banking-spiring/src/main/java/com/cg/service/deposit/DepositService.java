package com.cg.service.deposit;

 import com.cg.model.Deposit;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class DepositService implements IDepositService{


    @Override
    public Optional<Deposit> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Iterable<Deposit> findAll() {
        return null;
    }

    @Override
    public Deposit save(Deposit deposit) {
        return null;
    }

    @Override
    public void remove(Long id) {
    }
}
