package com.technodrome.walletmanagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.technodrome.walletmanagement.model.Customer;
import com.technodrome.walletmanagement.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Page<Transaction> findByWalletCustomer(Customer customer, Pageable pageable);

}