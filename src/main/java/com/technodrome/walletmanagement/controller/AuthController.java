package com.technodrome.walletmanagement.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technodrome.walletmanagement.dto.AuthResponse;
import com.technodrome.walletmanagement.dto.LoginRequest;
import com.technodrome.walletmanagement.dto.RegisterRequest;
import com.technodrome.walletmanagement.model.Customer;
import com.technodrome.walletmanagement.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterRequest req) {

		authService.register(req);

		return ResponseEntity.ok(Map.of("message", "User Registered Successfully"));
	}

	@PostMapping("/login")
	public AuthResponse login(@RequestBody LoginRequest req) {

		return authService.login(req);
	}
	
	@GetMapping("/getCustomer")
    public Customer getLoggedInCustomer(Authentication authentication) {

        String email = authentication.getName();

        return authService.getCustomerByEmail(email);
    }
	
	}