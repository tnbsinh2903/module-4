package spring_md4.grandemonstration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import spring_md4.grandemonstration.model.Customer;
import spring_md4.grandemonstration.service.CustomerService;
import spring_md4.grandemonstration.service.CustomerServiceFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CustomerController {
//    @GetMapping("/customers")
//    public String showList(){
//        return "customers/list.jsp";
//    }

//    private CustomerService customerService = CustomerServiceFactory.getInstance();
//
//    @GetMapping("/customers")
//    public String showList(HttpServletRequest request) {
//        List<Customer> customers = customerService.findAll();
//        request.setAttribute("customers", customers);
//        return "customers/list.jsp";
//    }

    @Autowired
    private CustomerService customerService ;

@GetMapping("/customers")
    public String showList(Model model) {
        List<Customer> customers = customerService.findAll();

        model.addAttribute("customers", customers);
        return "customers/list.jsp";
    }

}
