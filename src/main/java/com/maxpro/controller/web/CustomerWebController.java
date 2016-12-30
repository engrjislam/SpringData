package com.maxpro.controller.web;

import com.maxpro.model.Customer;
import com.maxpro.repository.CustomerRepository;
import com.maxpro.utility.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;


@Controller
@RequestMapping("/customers")
public class CustomerWebController {

    // html pages
    private final String customers = "customers";
    private final String customersByPagination = "customers-by-pagination";

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

    @RequestMapping(path = "/customer-by-pagination")
    public String customersByPagination(WebRequest webRequest, Model model) {
        int page = Integer.parseInt(webRequest.getParameter("page") != null && !webRequest.getParameter("page").isEmpty() ? webRequest.getParameter("page") : "0");
        int pageSize = Integer.parseInt(webRequest.getParameter("pageSize") != null && !webRequest.getParameter("pageSize").isEmpty() ? webRequest.getParameter("pageSize") : "20");

        Pageable pageable = new PageRequest(page <= 0 ? page : page - 1, pageSize);

        Page<Customer> customers = customerRepository.findCustomerOrderByLastNameAsc(pageable);
        Pagination<Customer> pagination = new Pagination<>(customers, "customer-by-pagination", pageSize);

        model.addAttribute("customers", customers);
        model.addAttribute("page", pagination);

        return customersByPagination;
    }

}
