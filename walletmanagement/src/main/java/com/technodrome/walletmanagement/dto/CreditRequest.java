package com.technodrome.walletmanagement.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;

public class CreditRequest {
	
	 @DecimalMin("0.01")
	    private BigDecimal amount;

	 public BigDecimal getAmount() {
		 return amount;
	 }

	 public void setAmount(BigDecimal amount) {
		 this.amount = amount;
	 }
	 
	 

}
