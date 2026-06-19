package com.technodrome.walletmanagement.dto;

import java.math.BigDecimal;

public class TransferRequest {

	 private Long sourceWalletId;

	    private Long targetWalletId;

	    private BigDecimal amount;

		public Long getSourceWalletId() {
			return sourceWalletId;
		}

		public void setSourceWalletId(Long sourceWalletId) {
			this.sourceWalletId = sourceWalletId;
		}

		public Long getTargetWalletId() {
			return targetWalletId;
		}

		public void setTargetWalletId(Long targetWalletId) {
			this.targetWalletId = targetWalletId;
		}

		public BigDecimal getAmount() {
			return amount;
		}

		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}
	    
	    
}
