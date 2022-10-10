package com.cg.controller.rest;

import com.cg.model.Customer;
import com.cg.model.Deposit;
import com.cg.model.dto.CustomerDTO;
import com.cg.service.customer.ICustomerService;
import com.cg.service.deposit.IDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/deposit")
public class RestDepositController {

    @Autowired
    private IDepositService depositService;

    @Autowired
    private ICustomerService customerService;

//    @PostMapping("/create")
//    public ResponseEntity<?> doDeposit(@RequestBody Deposit deposit) {
//        Long customerId = deposit.getCustomer().getId();
//
//        Optional<Customer> customerOptional = customerService.findById(customerId);
//
//        if (!customerOptional.isPresent()){
//            return new ResponseEntity<>("CustomerId  not invalid", HttpStatus.BAD_REQUEST);
//        }
//        try {
//            Customer customer = customerService.doDeposit(deposit);
//            CustomerDTO customerDTO = customer.toCustomerDTO();
//
//            return new ResponseEntity<>(customerDTO, HttpStatus.CREATED);
//        }catch (Exception e){
//            return new ResponseEntity<>("Please contact with admin", HttpStatus.BAD_REQUEST);
//
//        }
//    }

}
