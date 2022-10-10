package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping("/create-customer")
    public ModelAndView showCreatForm(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/create");
        modelAndView.addObject("customer",new Customer());
        return modelAndView;
    }
    @PostMapping("/create-customer")
    public ModelAndView saveCustomer(@ModelAttribute("customer") Customer customer){
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("customer/create");
        modelAndView.addObject("customer", new Customer());
        modelAndView.addObject("message","New customer create Successfully !") ;
        return modelAndView;
    }

    @PostMapping("/findById")
    public Customer findById( Long id) {
        Customer customer = customerService.findById(id);
        return customer;
    }

    @GetMapping("/findById")
    public Customer findByIds(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        return customer;
    }
    @GetMapping("/create1")
    public ModelAndView CreatForm(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ajax/create1");
        modelAndView.addObject("customer",new Customer());
        return modelAndView;
    }
    @PostMapping("/create1")
    public Customer findById(@RequestBody Customer customer) {
          customerService.save(customer);
        return customer;
    }
    @GetMapping("/customers")
    public ModelAndView listCustomer(){
        List<Customer> customerList = customerService.findAll();
        ModelAndView modelAndView = new ModelAndView("ajax/list");
        modelAndView.addObject("customers", customerList);
        return modelAndView;
    }

    @GetMapping("/edit-customer/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Customer customer = customerService.findById(id);
        if(customer != null){
            ModelAndView modelAndView = new ModelAndView("customer/edit");
            modelAndView.addObject("customer",customer);
            return modelAndView;
        }
        else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("edit-customer")
    public ModelAndView updateCustomer(@ModelAttribute("customer") Customer customer){
            customerService.save(customer);
            ModelAndView modelAndView = new ModelAndView("customer/edit");
            modelAndView.addObject("customer", customer);
            modelAndView.addObject("message","Customer update Successfully !");
            return modelAndView;
    }

    @GetMapping("/delete-customer/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Customer customer = customerService.findById(id);
        if(customer != null){
            ModelAndView modelAndView = new ModelAndView("customer/delete");
            modelAndView.addObject("customer", customer);
            return modelAndView;
        }
        else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-customer")
    public String deleteCustomer(@ModelAttribute("customer") Customer customer){
        customerService.remove(customer.getId());
        return "redirect:/customers";
    }
}
