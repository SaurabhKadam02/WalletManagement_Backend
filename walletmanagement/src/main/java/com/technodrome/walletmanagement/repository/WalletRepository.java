package com.technodrome.walletmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.technodrome.walletmanagement.model.Customer;
import com.technodrome.walletmanagement.model.Wallet;

@Repository
public interface WalletRepository
        extends JpaRepository<Wallet,Long> {

    List<Wallet> findByCustomerCustomerId(Long customerId);

	List<Wallet> findByCustomer(Customer customer);
}
