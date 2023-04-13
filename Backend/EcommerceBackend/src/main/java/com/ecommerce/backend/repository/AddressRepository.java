package com.ecommerce.backend.repository;

import com.ecommerce.backend.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findAll();
    Optional<Address> findById(Long id);
    boolean existsById(Long id);

}
