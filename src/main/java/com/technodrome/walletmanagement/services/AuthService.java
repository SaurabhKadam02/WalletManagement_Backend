package com.technodrome.walletmanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.technodrome.walletmanagement.dto.AuthResponse;
import com.technodrome.walletmanagement.dto.LoginRequest;
import com.technodrome.walletmanagement.dto.RegisterRequest;
import com.technodrome.walletmanagement.model.Customer;
import com.technodrome.walletmanagement.repository.CustomerRepository;
import com.technodrome.walletmanagement.util.JwtUtil;

@Service
public class AuthService {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public void register(RegisterRequest request) {

        if (repository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        Customer customer = new Customer();

        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setPassword(
                passwordEncoder.encode(
                        request.getPassword()));

        repository.save(customer);
    }

    public AuthResponse login(LoginRequest request) {

        Customer customer = repository.findByEmail(
                request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                customer.getPassword())) {

            throw new RuntimeException(
                    "Invalid credentials");
        }

        String token = jwtUtil.generateToken(
                customer.getEmail());

        return new AuthResponse(token);
    }

	public Customer getCustomerByEmail(String email) {
		return repository.findByEmail(email)
	            .orElseThrow(() -> new RuntimeException("Customer not found"));
	}
}