package com.cg.controller.rest;


import com.cg.model.Customer;
import com.cg.model.LocationRegion;
import com.cg.model.Transfer;
import com.cg.model.dto.CustomerDTO;
import com.cg.model.dto.LocationRegionDTO;
import com.cg.model.dto.TransferDTO;
import com.cg.repository.LocationRegionRepository;
import com.cg.service.customer.ICustomerService;
import com.fasterxml.jackson.databind.util.ArrayIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/api/customers")
public class RestCustomerController {

    @Autowired
    private ICustomerService customerService;
    @Autowired
    private LocationRegionRepository locationRegionRepository;

    @GetMapping
    public ResponseEntity<?> ListCustomer() {
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        List<Customer> customers = customerService.findAllActive();

        for (Customer customer : customers) {
            LocationRegion locationRegion = customer.getLocationRegion();
            LocationRegionDTO locationRegionDTO = locationRegion.toLocationRegionDTO();
            CustomerDTO customerDTO = customer.toCustomerDTO(locationRegionDTO);
            customerDTOS.add(customerDTO);
        }

        return new ResponseEntity<>(customerDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
        Optional<Customer> optionalCustomer = customerService.findById(id);

        Customer customer = optionalCustomer.get();

        LocationRegion locationRegion = customer.getLocationRegion();
        LocationRegionDTO locationRegionDTO = locationRegion.toLocationRegionDTO();

        CustomerDTO customerDTO = customer.toCustomerDTO(locationRegionDTO);

        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> doCreate(@RequestBody CustomerDTO customerDTO) {


        LocationRegionDTO locationRegionDTO = customerDTO.getLocationRegion();
        LocationRegion locationRegion = locationRegionDTO.toLocationRegion();

        locationRegion.setId(0L);
        LocationRegion newLocationRegion = locationRegionRepository.save(locationRegion);

        customerDTO.setId(0L);
        customerDTO.setBalance(new BigDecimal(0L));

        Customer customer = customerDTO.toCustomer(newLocationRegion);

        Customer newCustomer = customerService.save(customer);
        customerDTO = newCustomer.toCustomerDTO(newLocationRegion.toLocationRegionDTO());

        return new ResponseEntity<>(customerDTO, HttpStatus.CREATED);

    }

    @PostMapping("/update")
    public ResponseEntity<?> doUpdateCreate(@RequestBody CustomerDTO customerDTO) {

        LocationRegionDTO locationRegionDTO = customerDTO.getLocationRegion();
        LocationRegion locationRegion = locationRegionDTO.toLocationRegion();

        locationRegion.setId(0L);
        LocationRegion newLocationRegion = locationRegionRepository.save(locationRegion);

        customerDTO.setId(0L);
        customerDTO.setBalance(new BigDecimal(0L));

        Customer customer = customerDTO.toCustomer(newLocationRegion);

        Customer newCustomer = customerService.save(customer);
        customerDTO = newCustomer.toCustomerDTO(newLocationRegion.toLocationRegionDTO());

        return new ResponseEntity<>(customerDTO, HttpStatus.CREATED);

    }

    //    public String doDelete( @PathVariable Long id){
////        Optional<Customer> deleteCus = customerService.findById(id);
//        customerService.remove(id);
//        return "redirect:/customers";
//    }
    @PostMapping("/delete/{id}")
    public ResponseEntity<?> doDelete(@PathVariable Long id) { System.out.println(id);
        try {
            customerService.remove(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Delete Error", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/getAllRecipientWithoutSender/{senderId}")
    public ResponseEntity<?> getAllRecipientWithSender(@PathVariable Long senderId) {

        Optional<Customer> customerOptional = customerService.findById(senderId);

        if (!customerOptional.isPresent()) {
            return new ResponseEntity<>("Khng tim thay zi'", HttpStatus.BAD_REQUEST);

        }
        List<Customer> recipients = customerService.findAllByIdNot(senderId);
        List<CustomerDTO> customerDTOS = new ArrayList<>();

//        for (Customer item : recipients) {
//            CustomerDTO customerDTO = item.toCustomerDTO();
//            customerDTOS.add(customerDTO);
//        }
        return new ResponseEntity<>(customerDTOS, HttpStatus.CREATED);

    }

    @PostMapping("/transfer")
    public ResponseEntity<?> doTransfer(@RequestBody TransferDTO transferDTO) {

        Long senderId = transferDTO.getSenderId();
        Long recipientId = transferDTO.getRecipientId();

        if (senderId.equals(recipientId)) {
            return new ResponseEntity<>("Người gửi  và người nhận không hợp lệ", HttpStatus.BAD_REQUEST);
        }

        Optional<Customer> senderOptional = customerService.findById(senderId);

        if (!senderOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<Customer> recipientOptional = customerService.findById(recipientId);

        if (!recipientOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        BigDecimal senderIdBalance = senderOptional.get().getBalance();
        BigDecimal transferAmount = transferDTO.getTransferAmount();

        int fees = 10;
        BigDecimal feesAmount = transferAmount.multiply(new BigDecimal(fees)).divide(new BigDecimal(100));
        System.out.println(feesAmount);
        BigDecimal transactionAmount = transferAmount.add(feesAmount);

        if (senderIdBalance.compareTo(transactionAmount) < 0) {
            return new ResponseEntity<>("so du koo du de thuc hien giao dich", HttpStatus.BAD_REQUEST);
        }
        transferDTO.setFees(fees);
        transferDTO.setFeesAmount(feesAmount);
        transferDTO.setTransactionAmount(transactionAmount);
        Transfer transfer = transferDTO.toTransfer(senderOptional.get(), recipientOptional.get());

        Map<String, CustomerDTO> result = new HashMap<>();
        result = customerService.doTransfer(transfer);


        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

}
