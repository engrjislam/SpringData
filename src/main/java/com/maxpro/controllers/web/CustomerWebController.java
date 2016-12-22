package com.maxpro.controllers.web;

import com.maxpro.models.Customer;
import com.maxpro.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/customers")
public class CustomerWebController {

    // html pages
    private final String customers = "customers";

    @Autowired
    private CustomerRepository customerRepository;

    /*@RequestMapping(value = "", method = RequestMethod.POST)
    public Customer create(@RequestBody Customer customer) {
        customerRepository.save(customer);
        return customer;
    }

    @RequestMapping("/{id}")
    public Customer read(@PathVariable Long id) {
        return customerRepository.findOne(id);
    }*/

    @RequestMapping("")
    public String getCustomers(Model model) {
        model.addAttribute("customers", (List<Customer>) customerRepository.findAll());
        return customers;
    }

    /*@RequestMapping(value = "", method = RequestMethod.PUT)
    public Customer update(@RequestBody Customer customer) {
        customerRepository.save(customer);
        return customer;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        customerRepository.delete(id);
    }*/

}
