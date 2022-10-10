package com.cg.Service;

import com.cg.model.SmartPhone;
import com.cg.repository.ISmartphoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class SmartphoneService implements ISmartphoneService{

    @Autowired
    private ISmartphoneRepository smartphoneRepository;
    @Override
    public Optional<SmartPhone> findById(Long id) {
        return smartphoneRepository.findById(id);
    }

    @Override
    public Iterable<SmartPhone> findAll( ) {
        return smartphoneRepository.findAll();
    }

    @Override
    public SmartPhone save(SmartPhone smartPhone) {
        return smartphoneRepository.save(smartPhone);
    }

    @Override
    public void remove(Long id) {
       smartphoneRepository.deleteById(id);
    }
}
