package com.demo.rental.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "TOOLS")
@NoArgsConstructor
@AllArgsConstructor
public class Tool {
    @Id
    private String toolCode;
    private String brand;
    @ManyToOne
    @JoinColumn(name = "toolType")
    private ToolCharges toolCharges;
}
