package com.maxpro.controllers.rest;

import com.maxpro.models.Address;
import com.maxpro.models.Customer;
import com.maxpro.repositories.AddressRepository;
import com.maxpro.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

}
