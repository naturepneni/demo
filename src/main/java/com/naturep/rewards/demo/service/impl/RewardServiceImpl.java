package com.naturep.rewards.demo.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.naturep.rewards.demo.domain.CustomerTransaction;
import com.naturep.rewards.demo.dto.RewardRecord;
import com.naturep.rewards.demo.exception.TransactionNotFoundException;
import com.naturep.rewards.demo.repository.CustomerTransactionRepo;
import com.naturep.rewards.demo.response.RewardResponse;
import com.naturep.rewards.demo.service.RewardService;

@Service
public class RewardServiceImpl implements RewardService {

    @Autowired
    CustomerTransactionRepo customerTransactionRepo;

    @Autowired
    Transformer transformer;

    private final int hundred = 100;
    private final int fifty = 50;

    @Value("${points.over.hundred}")
    private Integer points_over_hundred;

    @Value("${points.over.fifty}")
    private Integer points_over_fifty;

    @Override
    public RewardResponse getReward(RewardRecord rewardRecord) {
	long totalReward = 0;
	Map<Object, Long> monthlyRewards = null;
	if (rewardRecord.total()) {
	    totalReward = calculateTotalRewards(rewardRecord.customerId());
	}
	if (rewardRecord.totalByMonth()) {
	    monthlyRewards = calculateMonthlyRewards(rewardRecord.customerId());
	}
	RewardResponse response = new RewardResponse();
	response.setCustomerId(rewardRecord.customerId());
	response.setTotal(totalReward);
	response.setMonthlyRewards(monthlyRewards);
	return response;
    }

    private Map<Object, Long> calculateMonthlyRewards(String customerId) {
	List<CustomerTransaction> transactions = customerTransactionRepo.findByCustomerId(customerId);
	if (transactions.isEmpty()) {
	    throw new TransactionNotFoundException(customerId);
	}
	return transactions.stream().collect(Collectors.groupingBy(transaction -> transaction.getMonth(),
		Collectors.summingLong(t -> calculateRewardPerTransaction(t))));

    }

    private long calculateTotalRewards(String customerId) {
	List<CustomerTransaction> transactions = customerTransactionRepo.findByCustomerId(customerId);
	if (transactions.isEmpty()) {
	    throw new TransactionNotFoundException(customerId);
	}
	return transactions.stream().mapToLong(transaction -> calculateRewardPerTransaction(transaction)).sum();
    }

    private long calculateRewardPerTransaction(CustomerTransaction transaction) {
	long reward = 0;
	if (transaction.getAmountSpent() > hundred) {
	    reward += (transaction.getAmountSpent() - hundred) * points_over_hundred;
	    reward += fifty * points_over_fifty;
	}
	if (transaction.getAmountSpent() > fifty && transaction.getAmountSpent() <= hundred) {
	    reward += (transaction.getAmountSpent() - fifty) * points_over_fifty;
	}
	return reward;
    }

}
