package com.karakaya.bank.SimpleBankAPI.Controller;

import com.karakaya.bank.SimpleBankAPI.model.Customer;
import com.karakaya.bank.SimpleBankAPI.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public @ResponseBody
    ModelAndView index() {
        Map<String, Object> model = new HashMap<>();
        model.put("customerList", customerService.getAllCustomers());
        return new ModelAndView("index", model);
    }

    @RequestMapping(path = "/populateRandom", method = RequestMethod.POST)
    public @ResponseBody
    ModelAndView populateRandom() {
        customerService.populateRandomData();
        Map<String, Object> model = new HashMap<>();
        model.put("customerList", customerService.getAllCustomers());
        return new ModelAndView("index", model);
    }

    @PostMapping("/saveDetails")
    public @ResponseBody
    ModelAndView saveDetails(@RequestParam("customerID") String customerID,
                              @RequestParam("name") String name,
                              @RequestParam("surname") String surname,
                              @RequestParam("credit") String credit,
                              ModelMap modelMap) {

        Customer cust = new Customer(new Integer(customerID),name,surname);
        if(credit==null || credit.length()<1 || credit.isEmpty()) {
            customerService.createOrUpdateCustomer(cust, BigDecimal.ZERO);
        }
        else {
            customerService.createOrUpdateCustomer(cust,new BigDecimal(credit));
        }


        Map<String, Object> model = new HashMap<>();
        model.put("customerList", customerService.getAllCustomers());
        return new ModelAndView("index", model);
    }

    @RequestMapping(path = "/removeAll", method = RequestMethod.POST)
    public @ResponseBody
    ModelAndView removeAll() {
        Map<String, Object> model = new HashMap<>();
        customerService.removeAll();
        return new ModelAndView("index", model);
    }

    @RequestMapping(path = "/getCustomer", method = RequestMethod.GET)
    public @ResponseBody
    ModelAndView getCustomerInfo(@RequestParam("customerID") long customerID) {
        Map<String, Object> model = new HashMap<>();

        Customer customer = customerService.getCustomer(customerID);
        if (customer == null) {
            return new ModelAndView("index", model);
        }
        model.put("customerID", customer.getCustomerID());
        model.put("name", customer.getName());
        model.put("surname", customer.getSurname());
        model.put("accounts", customer.getAccounts());
        model.put("balance", customerService.calculateTotalBalanceOfCustomer(customerID));
        model.put("transactions", customerService.getUserTransactions(customerID));

        return new ModelAndView("user", model);
    }

    @RequestMapping(path = "/addAccount", method = RequestMethod.POST)
    public @ResponseBody
    void addAccount(@RequestParam("customerID") long customerID,
                     @RequestParam("credit") String credit) {
        Customer cust = customerService.getCustomer(customerID);
        customerService.createOrUpdateCustomer(cust, new BigDecimal(credit));
    }

}
