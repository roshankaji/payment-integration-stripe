package com.stripe.paymentintegration.controller;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.CustomerCollection;
import com.stripe.paymentintegration.model.CustomerData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class StripePaymentController {

    @Value("${stripe.apikey}")
    String stripeKey;

    @RequestMapping("/home")
    public String home(){
        return "home";
    }

    @RequestMapping("/")
    public String index(Model model){
        return "index";
    }
    @RequestMapping("/createCustomer")
    public String createCustomer(CustomerData customerData) {
        return "create-customer";
    }

    @PostMapping("/addCustomer")
    public String addCustomer(CustomerData data) throws StripeException {
        Stripe.apiKey = stripeKey;

        Map<String, Object> params = new HashMap<>();
        params.put("name", data.getName());
        params.put("email", data.getEmail());

        Customer customer = Customer.create(params);

        return "success";
    }

    @RequestMapping("/index")
    public String model(Model model) throws StripeException {

        Stripe.apiKey = stripeKey;

        Map<String, Object> params = new HashMap<>();
        List<CustomerData> customerDataList = new ArrayList<CustomerData>();

        CustomerCollection customers = Customer.list(params);

        for(int i=0;i< customers.getData().size();i++){
            CustomerData customerData = new CustomerData();
            customerData.setCustomerId(customers.getData().get(i).getId());
            customerData.setName(customers.getData().get(i).getName());
            customerData.setEmail(customers.getData().get(i).getEmail());
            customerDataList.add(customerData);
        }

        model.addAttribute("customers", customerDataList);
        return "index";
    }
}
