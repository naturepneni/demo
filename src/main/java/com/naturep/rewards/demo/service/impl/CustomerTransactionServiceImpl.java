package com.naturep.rewards.demo.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naturep.rewards.demo.domain.CustomerTransaction;
import com.naturep.rewards.demo.dto.CustomerTransactionRecord;
import com.naturep.rewards.demo.repository.CustomerTransactionRepo;
import com.naturep.rewards.demo.service.CustomerTransactionService;

@Service
public class CustomerTransactionServiceImpl implements CustomerTransactionService {

    @Autowired
    CustomerTransactionRepo customerTransactionRepo;

    @Autowired
    Transformer transformer;

    @Override
    public Map<String, String> logTransaction(CustomerTransactionRecord transactionRecord) {
	CustomerTransaction customerTransaction = transformer.transactionRecordToDomain(transactionRecord);
	customerTransactionRepo.save(customerTransaction);
	Map<String, String> map = new HashMap<>();
	map.put("result", "success");
	return map;
    }

}
