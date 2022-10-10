package com.cg.controller.rest;

import com.cg.model.Customer;
import com.cg.model.Withdraw;
import com.cg.model.dto.CustomerDTO;
import com.cg.model.dto.WithDrawDTO;
import com.cg.repository.IWithdrawRepository;
import com.cg.service.customer.ICustomerService;
import com.cg.service.withdraw.IWithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/withdraw")
public class RestWithdrawController {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IWithdrawService withdrawService;

//    @PostMapping("/create")
//    public ResponseEntity<?> doWithdraw(@RequestBody Withdraw withdraw){
//
//        Long customerId = withdraw.getCustomer().getId();
//        Optional<Customer> customerOptional =  customerService.findById(customerId);
//
//        if(!customerOptional.isPresent()){
//            return new ResponseEntity<>("CustomerId  not invalid", HttpStatus.BAD_REQUEST);
//        }
//        try {
//            Customer customer = customerService.doWithdraw(withdraw);
//            CustomerDTO customerDTO = customer.toCustomerDTO();
//            return new ResponseEntity<>(customerDTO, HttpStatus.CREATED);
//        }catch (Exception e){
//            return new ResponseEntity<>("Please contact with admin", HttpStatus.BAD_REQUEST);
//        }
//    }

}
