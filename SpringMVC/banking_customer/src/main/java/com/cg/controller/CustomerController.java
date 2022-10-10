package com.cg.controller;

import com.cg.model.Customer;
import com.cg.model.dto.DepositDTO;
import com.cg.model.dto.RecipientDTO;
import com.cg.model.dto.TransferDTO;
import com.cg.model.dto.WithDrawDTO;
import com.cg.service.customer.ICustomerService;
import com.cg.service.transfer.ITransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.math.BigDecimal;
import java.util.Optional;

@Controller
public class CustomerController {

    @Autowired
    private ICustomerService customerService;
    @Autowired
    private ITransferService transferService;

    @GetMapping("/customers")
    public ModelAndView listCustomer() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/list");
        Iterable<Customer> customers = customerService.findAllByDeletedIsFalse();
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showFormCreate() {
        ModelAndView modelAndView = new ModelAndView("customer/create");
        modelAndView.addObject("customer", new Customer());
//        modelAndView.addObject("success", null);
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createCustomer(@ModelAttribute("customer") Customer customer) {
        ModelAndView modelAndView = new ModelAndView("customer/create");
        customerService.save(customer);
        modelAndView.addObject("customer", new Customer());
//        modelAndView.addObject("success", "aaaaaa");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView formUpdate(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findById(id);
        System.out.println(customer);
        if (customer.isPresent()) {

            ModelAndView modelAndView = new ModelAndView("customer/edit");
            modelAndView.addObject("customer", customer.get());
//            modelAndView.addObject("success", null);
            return modelAndView;
        } else {
            return new ModelAndView("error.404");
        }
    }

    @PostMapping("/edit/{id}")
    public ModelAndView updateCustomer(@PathVariable Long id, @Validated @ModelAttribute("customer") Customer customer, BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView("customer/edit");
        customerService.save(customer);
        modelAndView.addObject("customer", customer);
        modelAndView.addObject("success", null);
        return modelAndView;

    }

    @GetMapping("/delete/{id}")
    public ModelAndView formDelete(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findById(id);
        System.out.println(customer);
        if (customer.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("customer/delete");
            modelAndView.addObject("customer", customer.get());
//            modelAndView.addObject("success", null);
            return modelAndView;
        } else {
            return new ModelAndView("error.404");
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.remove(customer.getId());
        return "redirect:/customers";
    }

    @GetMapping("/deposit/{id}")
    public ModelAndView formDeposit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("customer/deposit");

        Optional<DepositDTO> depositDTO = customerService.findByIdWithDepositDTO(id);
        if (depositDTO.isPresent()) {
            modelAndView.addObject("depositDTO", depositDTO.get());
            return modelAndView;
        } else {
            return new ModelAndView("error.404");
        }
    }

    @PostMapping("deposit/{id}")
    public ModelAndView doDeposit(@PathVariable Long id, @Validated @ModelAttribute("customer") DepositDTO depositDTO, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("customer/deposit");
//        new DepositDTO().validate(depositDTO, bindingResult);
        customerService.doDeposit(id, depositDTO.getTransactionAmount(), depositDTO);

        modelAndView.addObject("depositDTO", customerService.findByIdWithDepositDTO(id).get());
        return modelAndView;
    }

    @GetMapping("/withdraw/{id}")
    public ModelAndView formWithdraw(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("customer/withdraw");
        Optional<WithDrawDTO> withDrawDTO = customerService.findByIdWithWithdrawDTO(id);

        if (withDrawDTO.isPresent()) {
            modelAndView.addObject("withdraw", withDrawDTO.get());
            return modelAndView;
        } else {
            return new ModelAndView("error.404");
        }
    }

    @PostMapping("/withdraw/{id}")
    public ModelAndView withdraw(@PathVariable Long id, @Validated @ModelAttribute("withdraw") WithDrawDTO withDrawDTO, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("customer/withdraw");

//        new WithDrawDTO().validate(withDrawDTOm, bindingResult);
        customerService.doWithdraw(id, withDrawDTO.getTransactionAmount(), withDrawDTO);
        modelAndView.addObject("withdraw", customerService.findByIdWithWithdrawDTO(id).get());

        return modelAndView;

    }

    @GetMapping("/transfer/{id}")
    public ModelAndView formTransfer(@PathVariable Long id) {

        ModelAndView modelAndView = new ModelAndView("customer/transfer");

        Optional<TransferDTO> transferDTO = transferService.findAllWithTransferDTO(id);

        Iterable<RecipientDTO> recipientDTO = customerService.findAllRecipientDTOByIdWithOutSenderAndDeletedIsFalse(id);

        if (transferDTO.isPresent()) {

            modelAndView.addObject("transfer", transferDTO.get());
           modelAndView.addObject("recipientDTO", recipientDTO);
            return modelAndView;
        } else {
            return new ModelAndView("error.404");
        }

    }

    @PostMapping("/transfer/{id}")
    public ModelAndView transfer(@PathVariable Long id, @Validated @ModelAttribute("transfer") TransferDTO transferDTO, BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/customer/transfer");

//        new TransferDTO().validate(transferDTO, bindingResult);

        if (bindingResult.hasFieldErrors()){
            modelAndView.addObject("recipientDTOS", customerService.findAllRecipientDTOByIdWithOutSender(id));
            modelAndView.addObject("script", true);
        }
        else {
            Optional<Customer> customer = customerService.findById(id);
            BigDecimal sender_balance = customer.get().getBalance();
            BigDecimal transferAmount = transferDTO.getTransferAmount();
            int fees = 10;
            BigDecimal feeAmount = transferAmount.divide(BigDecimal.valueOf(fees));
            BigDecimal transactionAmount = transferAmount.add(feeAmount);

            if (sender_balance.compareTo(transactionAmount) != -1) {
                try {
                    transferDTO.setFees(fees);
                    transferDTO.setFeesAmount(feeAmount);
                    transferDTO.setTransactionAmount(transactionAmount);

                    customerService.doTransfer(transferDTO);

                    Iterable<RecipientDTO> recipientDTOS = customerService.findAllRecipientDTOByIdWithOutSender(id);

                    modelAndView.addObject("transferDTO", transferService.findAllWithTransferDTO(id).get());
                    modelAndView.addObject("recipientDTOS", recipientDTOS);
                    modelAndView.addObject("success", "Successful transfer transaction");
                } catch (Exception e) {
                    e.printStackTrace();
                    modelAndView.addObject("error", "Invalid data, please contact system administrator");
                }
            }
            else {
                Iterable<RecipientDTO> recipientDTOS = customerService.findAllRecipientDTOByIdWithOutSender(id);
                modelAndView.addObject("recipientDTOS", recipientDTOS);
                modelAndView.addObject("error", "The sender's balance is not enough to make the transfer");
            }
        }
        return modelAndView;
    }
}
