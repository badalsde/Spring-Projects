package com.fbs.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class LoginDTO {
	@NotBlank(message = "User ID is mandatory")
    @Size(min = 3, max = 15, message = "User ID must be between 3 and 15 characters")
    @Pattern(regexp = "^[a-zA-Z0-9._]+$", message = "User ID can only contain alphabets, digits, '.', and '_'")
	private String userId;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    private String password;
	
	public LoginDTO() {
		super();
	}
	public LoginDTO(String userId, String password) {
		super();
		this.userId = userId;
		this.password = password;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "LoginDTO [userId=" + userId + ", password=" + password + "]";
	}

}
