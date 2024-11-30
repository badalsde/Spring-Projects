package com.fbs.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDTO {
	@NotBlank(message = "User ID is mandatory")
    @Size(min = 3, max = 15, message = "User ID must be between 3 and 15 characters")
    @Pattern(regexp = "^[a-zA-Z0-9._]+$", message = "User ID can only contain alphabets, digits, '.', and '_'")
	private String userId;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
//    @JsonIgnore
    private String password;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Name can only contain alphabets and spaces")
    private String name;

    @NotBlank(message = "City is mandatory")
    @Size(min = 2, max = 50, message = "City name must be between 2 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "City can only contain alphabets and spaces")
    private String city;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone number is mandatory")
    @Pattern(regexp = "[0-9]{10,13}", message = "Phone number must be a valid format (e.g. 1234567890)")
    private String phone;
    
	public UserDTO() {
		super();
	}

	public UserDTO(String userId, String password, String name, String city, String email, String phone) {
		super();
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.city = city;
		this.email = email;
		this.phone = phone;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", password=" + password + ", name=" + name + ", city=" + city + ", email="
				+ email + ", phone=" + phone + "]";
	}  
}
