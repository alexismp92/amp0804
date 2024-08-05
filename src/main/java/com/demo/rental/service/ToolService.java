package com.demo.rental.service;

import com.demo.rental.dto.CheckoutRequest;
import com.demo.rental.dto.CheckoutResponse;
import com.demo.rental.dto.ToolChargesDTO;
import com.demo.rental.dto.ToolDTO;
import com.demo.rental.entity.Tool;
import com.demo.rental.exceptions.NotFoundException;
import com.demo.rental.mapper.ToolMapper;
import com.demo.rental.repository.IToolsRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.demo.rental.utils.DateUtils.*;


@Service
@RequiredArgsConstructor
@Slf4j
public class ToolService {
    private final IToolsRepo iToolRepo;

    /**
     * get a tool based on its code
     * @param toolCode
     * @return
     * @throws NotFoundException
     */
    public ToolDTO getTool(String toolCode) throws NotFoundException{
        log.info("get tool: " + toolCode);
        Tool tool = iToolRepo.findById(toolCode.toUpperCase()).orElseThrow(()-> new NotFoundException("tool code : " + toolCode + "not found"));
        return ToolMapper.INSTANCE.toolToToolDTO(tool);
    }


    public CheckoutResponse rentTool(CheckoutRequest checkoutRequest) throws NotFoundException {
        log.info("checkout request: " + checkoutRequest);
        CheckoutResponse checkoutResponse = CheckoutResponse.builder().toolCode(checkoutRequest.getToolCode())
                .rentalDays(checkoutRequest.getRentalDays()).checkoutDate(checkoutRequest.getCheckoutDate()).discountPercentage(checkoutRequest.getDiscountPercentage()).build();

        ToolDTO toolDTO = getTool(checkoutRequest.getToolCode());
        checkoutResponse.setToolType(toolDTO.getToolCharges().getToolType());
        checkoutResponse.setBrand(toolDTO.getBrand());
        checkoutResponse.setDueDate(checkoutRequest.getCheckoutDate().plusDays(checkoutRequest.getRentalDays()));
        checkoutResponse.setDailyRentalCharge(toolDTO.getToolCharges().getDailyCharge());

        countChargeableDays(checkoutResponse, toolDTO.getToolCharges());
        calculateDiscounts(checkoutResponse);
        calculateFinalCharge(checkoutResponse);
        log.info("checkout: " + checkoutResponse);
        return checkoutResponse;
    }


    private void countChargeableDays(CheckoutResponse checkoutResponse, ToolChargesDTO toolCharges) {
        int countHolidays = 0, countWeekDays = 0, countWeekendDays = 0;
        LocalDate startDate = checkoutResponse.getCheckoutDate().plusDays(1);
        if(toolCharges.isWeekdayCharge()){
            countWeekDays = countWeekDays(startDate, checkoutResponse.getDueDate());
        }
        log.info("week days: " + countWeekDays);
        if(toolCharges.isWeekendCharge()){
            countWeekendDays = countWeekendDays(startDate, checkoutResponse.getDueDate());
        }
        log.info("weekend days: " + countWeekendDays);
        if(toolCharges.isHolidayCharge()){
            countHolidays = countHolidays(startDate, checkoutResponse.getDueDate());
        }
        log.info("holidays: " + countHolidays);

        int chargeDays = (countWeekDays + countWeekendDays + countHolidays);
        checkoutResponse.setChargeDays(chargeDays);
        log.info("charge days: " + chargeDays);

        BigDecimal preDiscountCharge = toolCharges.getDailyCharge().multiply(BigDecimal.valueOf(chargeDays));
        log.info("pre discount charge: " + preDiscountCharge);
        checkoutResponse.setPreDiscountCharge(preDiscountCharge);
    }

    private void calculateDiscounts(CheckoutResponse checkoutResponse) {
        BigDecimal discountAmount  = BigDecimal.valueOf(checkoutResponse.getDiscountPercentage()).divide(BigDecimal.valueOf(100));
        log.info("discount amount: " + discountAmount);
        checkoutResponse.setDiscountAmount(discountAmount);
    }

    private void calculateFinalCharge(CheckoutResponse checkoutResponse) {
        BigDecimal discount = checkoutResponse.getDiscountAmount().multiply(checkoutResponse.getPreDiscountCharge());
        BigDecimal finalCharge = checkoutResponse.getPreDiscountCharge().subtract(discount);
        log.info("final charge: " + finalCharge);
        checkoutResponse.setFinalCharge(finalCharge);
    }
}
