package com.maxpro.repositories;

import com.maxpro.models.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);
    Customer findById(long id);
    Customer findByFirstName(String firstName);
    List<Customer> findByFirstNameAndLastName(String fistName, String lastName);
    List<Customer> findByFirstNameOrLastName(String firstName, String lastName);

}
