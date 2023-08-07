package com.naturep.rewards.demo.service;

import com.naturep.rewards.demo.dto.RewardRecord;
import com.naturep.rewards.demo.response.RewardResponse;

public interface RewardService {

    public RewardResponse getReward(RewardRecord rewardRecord);

}
