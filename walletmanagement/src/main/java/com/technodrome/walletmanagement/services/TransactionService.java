package com.technodrome.walletmanagement.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.technodrome.walletmanagement.model.Customer;
import com.technodrome.walletmanagement.model.Transaction;
import com.technodrome.walletmanagement.repository.CustomerRepository;
import com.technodrome.walletmanagement.repository.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private TransactionRepository transactionRepo;

    public List<Transaction> getTransactions(String email){

        Customer customer =
                customerRepo.findByEmail(email)
                        .orElseThrow();

        return transactionRepo
                .findByWalletCustomer(customer);
    }
}
