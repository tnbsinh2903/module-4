package com.convert.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConvertUSD {

//@GetMapping("convert")
//    public String convert(@RequestParam double usd, Model model){
//            double vnd = usd * 23000;
//            model.addAttribute("usd",usd);
//            model.addAttribute("vnd",vnd);
//            return "result";
//    }

    @GetMapping("/convert")
    public ModelAndView convert(){
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

    @PostMapping("convert")
    public String convert(@RequestParam double usd, Model model){
            double vnd = usd * 23000;
            model.addAttribute("usd",usd);
            model.addAttribute("vnd",vnd);
            return "result";
    }
}
