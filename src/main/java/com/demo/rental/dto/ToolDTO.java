package com.demo.rental.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ToolDTO {
    private String toolCode;
    private String brand;
    private ToolChargesDTO toolCharges;
}
