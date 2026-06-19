package com.technodrome.walletmanagement.dto;

import jakarta.validation.constraints.NotBlank;


public class WalletRequest {

    @NotBlank
    private String walletName;

	public String getWalletName() {
		return walletName;
	}

	public void setWalletName(String walletName) {
		this.walletName = walletName;
	}
    
    
}