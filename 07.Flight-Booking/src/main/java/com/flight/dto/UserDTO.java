package com.flight.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String userId;
    @NotNull(message = "password must be mandatory")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[\\W_]).{9,}$", message = "Password should be minimum 9 characters in length with upper and lowercase alphabets and special character")
    private String password;
    @NotNull(message = "name must be mandatory")
    private String name;
    @NotNull(message = "city must be mandatory")
    private String city;
    @NotNull(message = "email must be mandatory")
    private String email;
    @NotNull(message = "phone must be mandatory")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number should be 10 digits")
    private String phone;
}
