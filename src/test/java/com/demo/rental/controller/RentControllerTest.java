package com.demo.rental.controller;

import com.demo.rental.dto.CheckoutRequest;
import com.demo.rental.exceptions.NotFoundException;
import com.demo.rental.repository.IToolsRepo;
import com.demo.rental.service.ToolService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
public class RentControllerTest {

    private RentController rentController;
    private ToolService toolService;
    @Autowired
    private IToolsRepo iToolsRepo;

    @BeforeEach
    public void setUp() {
        toolService = new ToolService(iToolsRepo);
        rentController = new RentController(toolService);
    }

    @Test
    public void testCheckout_LADW_2() throws NotFoundException {

        CheckoutRequest request = new CheckoutRequest();
        request.setToolCode("LADW");
        request.setCheckoutDate(LocalDate.of(2020, 7, 20));
        request.setRentalDays(3);
        request.setDiscountPercentage((short) 10);

        var response = rentController.rentTool(request);
        assertTrue(BigDecimal.valueOf(5.373).compareTo(response.getBody().getFinalCharge()) == 0);
    }

    @Test
    public void testCheckout_CHNS_3() throws NotFoundException {

        CheckoutRequest request = new CheckoutRequest();
        request.setToolCode("CHNS");
        request.setCheckoutDate(LocalDate.of(2015, 7, 2));
        request.setRentalDays(5);
        request.setDiscountPercentage((short) 25);

        var response = rentController.rentTool(request);
        assertTrue(BigDecimal.valueOf(4.47).compareTo(response.getBody().getFinalCharge()) == 0);

    }

    @Test
    public void testCheckout_JAKD_4() throws NotFoundException {

        CheckoutRequest request = new CheckoutRequest();
        request.setToolCode("JAKD");
        request.setCheckoutDate(LocalDate.of(2015, 9, 3));
        request.setRentalDays(6);
        request.setDiscountPercentage((short) 0);

        var response = rentController.rentTool(request);
        assertTrue(BigDecimal.valueOf(11.96).compareTo(response.getBody().getFinalCharge()) == 0);
    }

    @Test
    public void testCheckout_JAKR_5() throws NotFoundException {

        CheckoutRequest request = new CheckoutRequest();
        request.setToolCode("JAKR");
        request.setCheckoutDate(LocalDate.of(2020, 7, 2));
        request.setRentalDays(4);
        request.setDiscountPercentage((short) 50);

        var response = rentController.rentTool(request);
        assertTrue(BigDecimal.valueOf(2.99).compareTo(response.getBody().getFinalCharge()) == 0);

    }

    @Test
    public void testCheckout_JAKR_6() throws NotFoundException {

        CheckoutRequest request = new CheckoutRequest();
        request.setToolCode("JAKR");
        request.setCheckoutDate(LocalDate.of(2015, 7, 2));
        request.setRentalDays(9);
        request.setDiscountPercentage((short) 0);

        var response = rentController.rentTool(request);
        assertTrue(BigDecimal.valueOf(17.94).compareTo(response.getBody().getFinalCharge()) == 0);
    }


}