package com.fbs.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UpdatePasswordDTO {
	@NotBlank(message = "Email is mandatory")
	@Email(message = "Invalid email format")
	private String email;

	@NotBlank(message = "Phone number is mandatory")
	@Pattern(regexp = "[0-9]{10,13}", message = "Phone number must be a valid format (e.g. 1234567890)")
	private String phone;

	@NotBlank(message = "Password is mandatory")
	@Size(min = 6, max = 20, message = "New Password must be between 6 and 20 characters")
	private String newPassword;

	public UpdatePasswordDTO() {
		super();
	}

	public UpdatePasswordDTO(String email, String phone,String newPassword) {
		super();
		this.email = email;
		this.phone = phone;
		this.newPassword = newPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Override
	public String toString() {
		return "UpdatePasswordDTO [email=" + email + ", phone=" + phone + ", newPassword=" + newPassword + "]";
	}
}
