package com.stripe.paymentintegration.controller;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.CustomerCollection;
import com.stripe.paymentintegration.model.CustomerData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customer")
public class StripePaymentControllerAPI {

    @Value("${stripe.apikey}")
    String stripeKey;


    @GetMapping("/getAll")
    public List<CustomerData> getAllCutomers() throws StripeException {

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

        return customerDataList;
    }
}
