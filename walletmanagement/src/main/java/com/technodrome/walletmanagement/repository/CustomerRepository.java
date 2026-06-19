package com.technodrome.walletmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.technodrome.walletmanagement.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {


	Optional<Customer> findByEmail(String email);
}
