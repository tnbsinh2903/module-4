package com.cg.controller;

import com.cg.Service.ISmartphoneService;
import com.cg.model.SmartPhone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@RequestMapping("/smartphones")
public class SmartPhoneController {

    @Autowired
    private ISmartphoneService smartphoneService;

    @GetMapping("/list")
    public ModelAndView getAllSmartPage(){
        ModelAndView modelAndView = new ModelAndView("phone/list");
        modelAndView.addObject("smartphones", smartphoneService.findAll());
        return modelAndView;
    }

    @PostMapping
    public ResponseEntity<SmartPhone> createSmartphone(@RequestBody SmartPhone smartPhone){
        return new ResponseEntity<>(smartphoneService.save(smartPhone), HttpStatus.CREATED);
    }

    @GetMapping ResponseEntity<Iterable<SmartPhone>> allPhone(){
        return new ResponseEntity<>(smartphoneService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<SmartPhone> deleteSmartphone(@PathVariable Long id){
        Optional<SmartPhone> smartPhoneOptional = smartphoneService.findById(id);
        if(!smartPhoneOptional.isPresent()){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        smartphoneService.remove(id);
        return new ResponseEntity<>(smartPhoneOptional.get(),HttpStatus.NO_CONTENT);
    }
}

