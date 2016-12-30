package com.maxpro.repository;

import com.maxpro.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
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
    List<Customer> findByFirstNameOrderByLastNameAsc(String firstName);
    List<Customer> findDistinctCustomerByFirstName(String firstName);
    List<Customer> findAll(Pageable pageable);

    @Query("select customer from Customer customer")
    Page<Customer> findCustomerOrderByLastNameAsc(Pageable pageable);

}
