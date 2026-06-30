package com.technodrome.walletmanagement.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.technodrome.walletmanagement.dto.TransferRequest;
import com.technodrome.walletmanagement.dto.WalletRequest;
import com.technodrome.walletmanagement.model.Customer;
import com.technodrome.walletmanagement.model.Transaction;
import com.technodrome.walletmanagement.model.Wallet;
import com.technodrome.walletmanagement.repository.CustomerRepository;
import com.technodrome.walletmanagement.repository.TransactionRepository;
import com.technodrome.walletmanagement.repository.WalletRepository;	

import jakarta.transaction.Transactional;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepo;

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private TransactionRepository txnRepo;

    @Autowired
    private AuditService auditService;

    public Wallet createWallet(String email, WalletRequest request) {

        Customer customer = customerRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Wallet wallet = new Wallet();
        wallet.setWalletName(request.getWalletName());
        wallet.setBalance(BigDecimal.ZERO);
        wallet.setStatus("ACTIVE");
        wallet.setCustomer(customer);

        walletRepo.save(wallet);

        auditService.log(
                customer.getCustomerId(),
                "WALLET_CREATED");

        return wallet;
    }

    public Wallet credit(Long walletId, BigDecimal amount) {

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Amount must be greater than zero");
        }

        Wallet wallet = walletRepo.findById(walletId)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        wallet.setBalance(wallet.getBalance().add(amount));

        walletRepo.save(wallet);

        Transaction transaction = new Transaction();
        transaction.setWallet(wallet);
        transaction.setTxnType("CREDIT");
        transaction.setAmount(amount);
        transaction.setCreatedDate(LocalDateTime.now());

        txnRepo.save(transaction);

        auditService.log(
                wallet.getCustomer().getCustomerId(),
                "CREDIT");

        return wallet;
    }

    public Wallet debit(Long walletId, BigDecimal amount) {

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Amount must be greater than zero");
        }

        Wallet wallet = walletRepo.findById(walletId)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        if (wallet.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient Balance");
        }

        wallet.setBalance(wallet.getBalance().subtract(amount));

        walletRepo.save(wallet);

        Transaction transaction = new Transaction();
        transaction.setWallet(wallet);
        transaction.setTxnType("DEBIT");
        transaction.setAmount(amount);
        transaction.setCreatedDate(LocalDateTime.now());

        txnRepo.save(transaction);

        auditService.log(
                wallet.getCustomer().getCustomerId(),
                "DEBIT");

        return wallet;
    }

    @Transactional
    public void transfer(TransferRequest request) {

        if (request.getSourceWalletId()
                .equals(request.getTargetWalletId())) {

            throw new RuntimeException(
                    "Source and Target Wallet cannot be the same");
        }

        if (request.getAmount()
                .compareTo(BigDecimal.ZERO) <= 0) {

            throw new RuntimeException(
                    "Amount must be greater than zero");
        }

        Wallet source = walletRepo.findById(
                request.getSourceWalletId())
                .orElseThrow(() ->
                        new RuntimeException("Source wallet not found"));

        Wallet target = walletRepo.findById(
                request.getTargetWalletId())
                .orElseThrow(() ->
                        new RuntimeException("Target wallet not found"));

        if (source.getBalance()
                .compareTo(request.getAmount()) < 0) {

            throw new RuntimeException(
                    "Insufficient Balance");
        }

        source.setBalance(
                source.getBalance()
                        .subtract(request.getAmount()));

        target.setBalance(
                target.getBalance()
                        .add(request.getAmount()));

        walletRepo.save(source);
        walletRepo.save(target);

        Transaction transaction = new Transaction();
        transaction.setWallet(source);
        transaction.setTxnType("TRANSFER");
        transaction.setAmount(request.getAmount());
        transaction.setCreatedDate(LocalDateTime.now());

        txnRepo.save(transaction);

        auditService.log(
                source.getCustomer().getCustomerId(),
                "TRANSFER");
    }

    public List<Wallet> getWallets(String email){

        Customer customer = customerRepo.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));

        return walletRepo.findByCustomer(customer);
    }

	
}