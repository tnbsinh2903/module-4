package com.cg.service.transfer;

 import com.cg.model.Transfer;
import com.cg.repository.ITransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class TransferService implements ITransferService{

    @Autowired
    private ITransferRepository transferRepository;
    @Override
    public Optional<Transfer> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Iterable<Transfer> findAll() {
        return null;
    }

    @Override
    public Transfer save(Transfer transfer) {
        return transferRepository.save(transfer);
    }

    @Override
    public void remove(Long id) {
        transferRepository.deleteById(id);
    }
}
