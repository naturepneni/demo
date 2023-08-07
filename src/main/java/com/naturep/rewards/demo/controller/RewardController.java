package com.naturep.rewards.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naturep.rewards.demo.dto.RewardRecord;
import com.naturep.rewards.demo.response.RewardResponse;
import com.naturep.rewards.demo.service.RewardService;

@RestController
@RequestMapping("reward")
public class RewardController {

    @Autowired
    RewardService rewardService;

    @GetMapping("/total/{customerId}")
    public RewardResponse totalReward(@PathVariable String customerId) {
	return rewardService.getReward(new RewardRecord(customerId, true, false));

    }

    @GetMapping("/month/{customerId}")
    public RewardResponse monthlyReward(@PathVariable String customerId) {
	return rewardService.getReward(new RewardRecord(customerId, false, true));

    }

}
