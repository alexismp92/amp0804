package com.demo.rental.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.StringJoiner;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
/**
 *     //Tool code - Specified at checkout
 *     //Tool type - From tool info
 *     //Tool brand - From tool info
 *     //Rental days - Specified at checkout
 *     //Check out date - Specified at checkout
 *     //Due date - Calculated from checkout date and rental days.
 *     //Daily rental charge - Amount per day, specified by the tool type.
 *     //Charge days - Count of chargeable days, from day after checkout through and including due date, excluding “no charge” days as specified by the tool type.
 *     //Pre-discount charge - Calculated as charge days X daily charge. Resulting total rounded half up to cents.
 *     //Discount percent - Specified at checkout.
 *     //Discount amount - calculated from discount % and pre-discount charge. Resulting amount rounded half up to cents.
 *     //Final charge - Calculated as pre-discount charge - discount amount.
 */
public class CheckoutResponse extends CheckoutRequest {
    private String toolType;
    private String brand;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yy")
    private LocalDate dueDate;
    private BigDecimal dailyRentalCharge;
    private int chargeDays;
    private BigDecimal preDiscountCharge;
    private BigDecimal discountAmount;
    private BigDecimal finalCharge;

    @Override
    public String toString() {
        return new StringJoiner(", ", CheckoutResponse.class.getSimpleName() + "[", "]")
                .add("toolType='" + toolType + "'")
                .add("brand='" + brand + "'")
                .add("dueDate=" + dueDate)
                .add("dailyRentalCharge=" + dailyRentalCharge)
                .add("chargeDays=" + chargeDays)
                .add("preDiscountCharge=" + preDiscountCharge)
                .add("discountAmount=" + discountAmount)
                .add("toolCode='" + toolCode + "'")
                .add("rentalDays=" + rentalDays)
                .add("checkoutDate=" + checkoutDate)
                .add("discountPercentage=" + discountPercentage)
                .add("finalCharge=$" + finalCharge)
                .toString();
    }
}
