package com.demo.rental.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@SuperBuilder
public class CheckoutRequest {
    @NotNull(message = "tooCode cannot be null")
    protected String toolCode;
    @Min(value = 1, message = "Rental days must be at least 1")
    protected int rentalDays;
    @NotNull(message = "Checkout date cannot be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yy")
    protected LocalDate checkoutDate;
    @Min(value = 0, message = "Discount percentage must be at least 0")
    @Max(value = 100, message = "Discount percentage cannot be more than 100")
    protected short discountPercentage;
}
