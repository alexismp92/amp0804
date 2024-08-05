package com.demo.rental.controller;

import com.demo.rental.dto.CheckoutRequest;
import com.demo.rental.dto.CheckoutResponse;
import com.demo.rental.dto.ToolDTO;
import com.demo.rental.exceptions.NotFoundException;
import com.demo.rental.service.ToolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rent/")
@RequiredArgsConstructor
@Validated
@Slf4j
public class RentController {

    private final ToolService toolsService;

    @PostMapping
    public ResponseEntity<CheckoutResponse> rentTool(@Valid @RequestBody CheckoutRequest checkoutRequest) throws NotFoundException {
        ResponseEntity responseEntity = null;

        CheckoutResponse checkoutResponse = toolsService.rentTool(checkoutRequest);
        responseEntity = new ResponseEntity<>(checkoutResponse, HttpStatus.OK);


        return responseEntity;
    }

}
