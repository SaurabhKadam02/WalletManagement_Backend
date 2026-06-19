package com.technodrome.walletmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.technodrome.walletmanagement.model.Customer;
import com.technodrome.walletmanagement.model.Transaction;

@Repository
public interface TransactionRepository
        extends JpaRepository<Transaction,Long> {

	List<Transaction> findByWalletCustomer(Customer customer);
}
