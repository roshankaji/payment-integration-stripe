package com.stripe.paymentintegration.model;

public class CustomerData {

    public String name;

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", customerId='" + customerId + '\'' +
                '}';
    }

    public String email;
    public String customerId;


    public CustomerData(String name, String email, String customerId) {
        this.name = name;
        this.email = email;
        this.customerId = customerId;
    }

    public CustomerData() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

}
