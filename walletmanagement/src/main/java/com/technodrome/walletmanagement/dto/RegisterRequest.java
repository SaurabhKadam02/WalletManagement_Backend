package com.technodrome.walletmanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class RegisterRequest {
	
	@NotBlank(message = "Username is required")
    @Pattern(
        regexp = "^[a-zA-Z_]{4,20}$",
        message = "Username must be 4-20 characters and contain only letters, and underscores"
    )
	private String name;
	
	@Email(message = "Invalid email")
	@NotBlank(message = "Email is required")
	private String email;
	
	 @NotBlank(message = "Password is required")
	    @Pattern(
	        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,20}$",
	        message = "Password must contain uppercase, lowercase, number, special character, and be 8-20 characters long"
	    )
	private String password;
	 
	 
	
	public String getName() {
		return name;
	}
	 public void setName(String name) {
		 this.name = name;
	 }
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
