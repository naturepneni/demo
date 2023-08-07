package com.naturep.rewards.demo.controller;

import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.naturep.rewards.demo.dto.RewardRecord;
import com.naturep.rewards.demo.response.RewardResponse;
import com.naturep.rewards.demo.service.RewardService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RewardController.class)
public class RewardControllerTest {

    @MockBean
    RewardService rewardService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGetRewardTotal() throws Exception {

	RewardRecord record = new RewardRecord("10001", true, false);

	RewardResponse response = new RewardResponse();
	response.setCustomerId("10001");
	response.setTotal(320);

	when(rewardService.getReward(record)).thenReturn(response);

	mockMvc.perform(MockMvcRequestBuilders.get("/reward/total/{customerId}", "10001"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.aMapWithSize(2)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.customerId", Matchers.is("10001")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.total", Matchers.is(320)));
    }

    @Test
    public void testGetRewardMonthly() throws Exception {

	RewardRecord record = new RewardRecord("10001", false, true);

	RewardResponse response = new RewardResponse();
	response.setCustomerId("10001");
	Map<Object, Long> map = new HashMap<>();
	Object ob = (Integer) 8;
	map.put(ob, 320L);
	response.setMonthlyRewards(map);

	when(rewardService.getReward(record)).thenReturn(response);

	mockMvc.perform(MockMvcRequestBuilders.get("/reward/month/{customerId}", "10001"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.aMapWithSize(2)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.customerId", Matchers.is("10001")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.monthlyRewards", Matchers.aMapWithSize(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.monthlyRewards.8", Matchers.is(320)));
    }

}
