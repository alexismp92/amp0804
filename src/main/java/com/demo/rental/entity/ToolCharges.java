package com.demo.rental.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Collection;

@Data
@Entity(name = "toolCharges")
@NoArgsConstructor
@AllArgsConstructor
public class ToolCharges {
    @Id
    private String toolType;
    private BigDecimal dailyCharge;
    private boolean weekdayCharge;
    private boolean weekendCharge;
    private boolean holidayCharge;
    @OneToMany(mappedBy = "toolCharges")
    private Collection<Tool> tools;

}
