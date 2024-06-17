package com.flight.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Creditcard {
    @Id
    @NotEmpty(message = "Please enter the Card Number")
    private String cardNumber;
    @NotEmpty(message = "Please enter the Card Holder Name")
    private String cardHolderName;
    @NotEmpty(message = "Please enter cvv")
    private String cvv;
    @NotEmpty(message = "Please enter the pin")
    private String securePin;
    @NotEmpty(message = "Please enter the Expiry Month")
    private String expiryMonth;
    @NotEmpty(message = "Please enter the Expiry Year")
    private String expiryYear;
    private String totalBill;
}
