package com.flight.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {
    @NotNull(message = "username should not be null")
    private String userId;
    @NotNull(message = "password must be mandatory")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[\\W_]).{9,}$", message = "Password should be minimum 9 characters in length with upper and lowercase alphabets and special character")
    private String password;
}
