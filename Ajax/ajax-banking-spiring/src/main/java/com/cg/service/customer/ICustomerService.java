package com.cg.service.customer;

import com.cg.model.Customer;
import com.cg.model.Deposit;
import com.cg.model.Transfer;
import com.cg.model.Withdraw;
import com.cg.model.dto.CustomerDTO;
import com.cg.service.IGeneralService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


public interface ICustomerService extends IGeneralService<Customer> {

 void incrementMoney ( BigDecimal transactionAmount, Long customerId);

 void reduceMoney (BigDecimal transactionAmount , Long customerId);

Customer doDeposit (Deposit deposit);

Customer doWithdraw (Withdraw withdraw);

List<Customer> findAllByIdNot(Long senderId);

 List<Customer> findAllActive( );


public Map<String , CustomerDTO> doTransfer(Transfer transfer);





}
