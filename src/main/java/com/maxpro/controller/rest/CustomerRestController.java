package com.maxpro.controller.rest;

import com.maxpro.model.Address;
import com.maxpro.model.Customer;
import com.maxpro.repository.AddressRepository;
import com.maxpro.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressRepository addressRepository;


    @RequestMapping(value = "", method = RequestMethod.POST)
    public Customer create(@RequestBody Customer customer) {
        customerRepository.save(customer);
        return customer;
    }

    @RequestMapping("/{id}")
    public Customer read(@PathVariable Long id) {
        return customerRepository.findOne(id);
    }

    @RequestMapping("")
    public List<Customer> list() {
        return (List<Customer>) customerRepository.findAll();
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Customer update(@RequestBody Customer customer) {
        customerRepository.save(customer);
        return customer;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        customerRepository.delete(id);
    }

    @RequestMapping(path = "/complex/query")
    public List<Customer> getCustomerByComplexQuery() {
        List<Customer> customers = customerRepository.findByFirstNameAndLastName("Johirul", "Islam");
        return customers;
    }

    @RequestMapping(path = "/complex/query2")
    public List<Customer> getCustomerByComplexQuery2() {
        List<Customer> customers = customerRepository.findByFirstNameOrLastName("Johirul", "Islam");
        return customers;
    }

    @RequestMapping("/customer-with-address/{customerId}")
    public Customer getCustomerWithAddress(@PathVariable("customerId") Long customerId) {
        Customer customer = customerRepository.findOne(customerId);
        return customer;
    }

    @RequestMapping("/save-customer-with-address")
    public Customer saveCustomerWithAddress() {

        Address address = new Address();
        address.setStreet("street");
        address.setCity("city");
        address.setCountry("country");
        address.setPostalCode("postal_code");
        Address address1 = addressRepository.save(address);

        Customer customer = new Customer();
        customer.setFirstName("Johirul");
        customer.setLastName("Islam");
        customer.setAddress(address1);
        customer.setAddressId(address1.getId());
        customerRepository.save(customer);

        return customer;
    }

    @RequestMapping("/customer-address/{customerId}")
    public Address getCustomerAddress(@PathVariable("customerId") Long customerId) {
        return addressRepository.findOne(customerId);
    }

    @RequestMapping(path = "/customer-address-by-addressId/{addressId}")
    public Address getCustomerAddressByAddressId(@PathVariable("addressId") Long addressId) {
        return addressRepository.addressById(addressId);
    }

    @RequestMapping(path = "/customer-list")
    public List<Customer> customerList() {
        return customerRepository.findByFirstNameOrderByLastNameAsc("Johir");
    }

    @RequestMapping(path = "/distinct-customer-by-first-name/{firstName}")
    public List<Customer> customerList(@PathVariable("firstName") String firstName) {
        return customerRepository.findDistinctCustomerByFirstName("Johir");
    }

    @RequestMapping(path = "/count")
    public Long countCustomer() {
        return customerRepository.count();
    }

    @RequestMapping(path = "/is-exist/{customerId}")
    public boolean isCustomerExist(@PathVariable("customerId") Long customerId){
        return customerRepository.exists(customerId);
    }

    @RequestMapping(path = "/insert-100-address-and-customer")
    public List<Customer> insert100AddressAndCustomer() {

        Address address;
        Customer customer;

        List<Customer> customers = new ArrayList<>();

        for (int i = 0; i < 100; i++) {

            address = null;
            customer = null;

            address = new Address();
            address.setStreet("street " + (i + 1));
            address.setCity("city " + (i + 1));
            address.setCountry("country " + (i + 1));
            address.setPostalCode("postal_code " + (i + 1));
            Address address1 = addressRepository.save(address);

            customer = new Customer();
            customer.setFirstName("FirstName " + (i + 1));
            customer.setLastName("LastName " + (i + 1));
            customer.setAddress(address1);
            customer.setAddressId(address1.getId());
            customerRepository.save(customer);

            customers.add(customer);
        }

        return customers;
    }

    @RequestMapping(path = "/customer-by-pagination")
    public List<Customer> customerListByPagination(WebRequest webRequest) {
        int page = Integer.parseInt(webRequest.getParameter("page") != null && !webRequest.getParameter("page").isEmpty() ? webRequest.getParameter("page") : "1");
        int pageSize = Integer.parseInt(webRequest.getParameter("pageSize") != null && !webRequest.getParameter("pageSize").isEmpty() ? webRequest.getParameter("pageSize") : "20");

        Pageable pageable = new PageRequest(page, pageSize);

        return customerRepository.findAll(pageable);
    }

}
