package com.naturep.rewards.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naturep.rewards.demo.dto.CustomerTransactionRecord;
import com.naturep.rewards.demo.request.TransactionRequest;
import com.naturep.rewards.demo.service.CustomerTransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("transaction")
@Validated
public class CustomerTransactionController {

    @Autowired
    CustomerTransactionService customerTransactionService;

    @PostMapping(path = "spent", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> customerTransaction(@Valid @RequestBody TransactionRequest request) {
	return customerTransactionService
		.logTransaction(new CustomerTransactionRecord(request.getCustomerId(), request.getAmountSpent()));

    }

}
