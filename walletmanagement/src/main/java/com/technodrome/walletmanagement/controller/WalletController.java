package com.technodrome.walletmanagement.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technodrome.walletmanagement.dto.AmountRequest;
import com.technodrome.walletmanagement.dto.TransferRequest;
import com.technodrome.walletmanagement.dto.WalletRequest;
import com.technodrome.walletmanagement.model.Wallet;
import com.technodrome.walletmanagement.services.WalletService;

@RestController
@RequestMapping("/wallets")
public class WalletController {

	@Autowired
    private WalletService service;

	@PostMapping
	public Wallet createWallet(
	        Authentication authentication,
	        @RequestBody WalletRequest request) {

	    String email = authentication.getName();

	    return service.createWallet(email, request);
	}

    @PostMapping("/{id}/credit")
    public Wallet credit(
        @PathVariable Long id,
        @RequestBody AmountRequest req){

        return service.credit(
           id,
           req.getAmount());
    }

    @PostMapping("/{id}/debit")
    public Wallet debit(
        @PathVariable Long id,
        @RequestBody AmountRequest req){

        return service.debit(
            id,
            req.getAmount());
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(
      @RequestBody TransferRequest req){

        service.transfer(req);

        return ResponseEntity.ok(
            Map.of(
             "message",
             "Transfer Successful"));
    }
    @GetMapping
    public List<Wallet> getWallets(Authentication authentication) {

        String email = authentication.getName();

        return service.getWallets(email);
    }
}
