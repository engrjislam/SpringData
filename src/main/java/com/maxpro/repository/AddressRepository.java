package com.maxpro.repository;

import com.maxpro.model.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {

    @Query("select address from Address address where address.id = :addressId")
    public Address addressById(@Param("addressId") Long addressId);

}
