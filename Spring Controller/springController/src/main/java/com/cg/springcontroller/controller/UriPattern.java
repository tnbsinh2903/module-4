package com.cg.springcontroller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UriPattern {

    @RequestMapping("/si?h?/d?p")
    public String sinh1(Model model){
        model.addAttribute("sinh","sinhdz");
        return "sinh1";
    }

    @RequestMapping("/s*h?/d*p")
    public String sinh2(ModelMap model){
        model.put("sinh","hahhaaa");
        return "sinh1";
    }

    @RequestMapping("/s*h?/d**")
    public ModelAndView sinh3(){
        ModelAndView model = new ModelAndView();
        model.setViewName("sinh1");
        model.addObject("sinh","xenhhhh");
        return model;

    }

}
