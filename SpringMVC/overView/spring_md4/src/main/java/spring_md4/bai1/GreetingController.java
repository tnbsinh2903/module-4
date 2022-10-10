package spring_md4.bai1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.Request;

import javax.servlet.http.HttpServlet;

@Controller 
public class GreetingController {

//    @RequestMapping(path = "greeting",method = RequestMethod.GET)
//    public String greeting(@RequestParam("name") String name, Model model){
//        model.addAttribute("name",name);
//        return "index" ;
//    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }
//    @RequestMapping("/aaa")
//    public ModelAndView mg (){
//        ModelAndView aa = new ModelAndView();
//        return new ModelAndView();
//    }


//@GetMapping("/greeting")
//public String greeting() {
//    return "index";


}
