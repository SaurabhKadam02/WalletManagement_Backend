package com.technodrome.walletmanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.technodrome.walletmanagement.model.Customer;
import com.technodrome.walletmanagement.model.Transaction;
import com.technodrome.walletmanagement.repository.CustomerRepository;
import com.technodrome.walletmanagement.repository.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Page<Transaction> getTransactions(String email, int page, int size) {

        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Pageable pageable = PageRequest.of(
        	    page,
        	    size,
        	    Sort.by(Sort.Direction.DESC, "createdDate")
        	);
        return transactionRepository.findByWalletCustomer(customer, pageable);
    }
}