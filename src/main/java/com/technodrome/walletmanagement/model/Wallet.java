package com.technodrome.walletmanagement.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="WALLET")

public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="WALLET_ID")
    private Long walletId;

    private String walletName;

    @Column(name="BALANCE")
    private BigDecimal balance = BigDecimal.ZERO;

    @Column(name="STATUS")

    private String status = "ACTIVE";

    @ManyToOne
    @JoinColumn(name="CUSTOMER_ID")
    private Customer customer;

	public Long getWalletId() {
		return walletId;
	}

	public void setWalletId(Long walletId) {
		this.walletId = walletId;
	}

	public String getWalletName() {
		return walletName;
	}

	public void setWalletName(String walletName) {
		this.walletName = walletName;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
    
    
}