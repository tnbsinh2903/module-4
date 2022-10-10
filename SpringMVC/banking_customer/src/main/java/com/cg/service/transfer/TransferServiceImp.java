package com.cg.service.transfer;

import com.cg.model.Transfer;
import com.cg.model.dto.ITransferDTO;
import com.cg.model.dto.SumFreeAmountDTO;
import com.cg.model.dto.TransferDTO;
import com.cg.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class TransferServiceImp implements ITransferService{

    @Autowired
    private TransferRepository transferRepository;


    @Override
    public Iterable<Transfer> findAll() {
        return null;
    }

    @Override
    public Optional<Transfer> findId(Long id) {
        return Optional.empty();
    }

    @Override
    public Transfer save(Transfer transfer) {
        return  transferRepository.save(transfer);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Iterable<ITransferDTO> findAllByITransferDTO() {
        return transferRepository.findAllByITransferDTO();
    }

    @Override
    public Optional<TransferDTO> findAllWithTransferDTO(Long id) {
        return transferRepository.findByIdWithTransferDTO(id);
    }

    @Override
    public Optional<SumFreeAmountDTO> sumFeesAmount() {
        return transferRepository.sumFeesAmount();
    }
}
