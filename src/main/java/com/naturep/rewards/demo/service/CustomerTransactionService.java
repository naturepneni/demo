package com.naturep.rewards.demo.service;

import java.util.Map;

import com.naturep.rewards.demo.dto.CustomerTransactionRecord;

public interface CustomerTransactionService {

    Map<String, String> logTransaction(CustomerTransactionRecord customerTransaction);

}
