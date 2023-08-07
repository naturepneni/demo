package com.naturep.rewards.demo.service.impl;

import org.springframework.stereotype.Component;

import com.naturep.rewards.demo.domain.CustomerTransaction;
import com.naturep.rewards.demo.dto.CustomerTransactionRecord;

@Component
public class Transformer {

    public CustomerTransaction transactionRecordToDomain(CustomerTransactionRecord record) {

	CustomerTransaction domainTransaction = new CustomerTransaction();
	domainTransaction.setCustomerId(record.customerId());
	domainTransaction.setAmountSpent(record.amountSpent());
	return domainTransaction;
    }

}
