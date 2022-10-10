package com.cg.Service;

import com.cg.model.SmartPhone;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface ISmartphoneService {

    Optional<SmartPhone> findById(Long id);

    Iterable<SmartPhone> findAll( );

    SmartPhone save(SmartPhone smartPhone);

    void remove(Long id);


}
