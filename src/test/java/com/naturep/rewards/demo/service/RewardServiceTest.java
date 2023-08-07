package com.naturep.rewards.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.naturep.rewards.demo.domain.CustomerTransaction;
import com.naturep.rewards.demo.dto.RewardRecord;
import com.naturep.rewards.demo.exception.TransactionNotFoundException;
import com.naturep.rewards.demo.repository.CustomerTransactionRepo;
import com.naturep.rewards.demo.response.RewardResponse;
import com.naturep.rewards.demo.service.impl.RewardServiceImpl;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RewardServiceTest {

    @Mock
    private CustomerTransactionRepo transactionRepo;

    @InjectMocks
    private RewardService rewardService = new RewardServiceImpl();

    @BeforeAll
    public void setUp() {
		ReflectionTestUtils.setField(rewardService, "points_over_hundred", 2);
		ReflectionTestUtils.setField(rewardService, "points_over_fifty", 1);
    }

    @Test
    @DisplayName("Test Get Total Rewards")
    void testGetRewardTotal() {
		RewardRecord rewardRecord = new RewardRecord("10000002", true, false);
	
		when(transactionRepo.findByCustomerId("10000002")).thenReturn(getCustomerTransactions());
		RewardResponse rewardResponse = rewardService.getReward(rewardRecord);
	
		assertEquals(rewardResponse.getCustomerId(), "10000002");
		assertEquals(rewardResponse.getTotal(), 1120L);
    }
    
    @Test
    @DisplayName("Test Get Total Rewards - Throws TransactionNotFound Exception")
    void testGetRewardTotalNoTransactions() {
		RewardRecord rewardRecord = new RewardRecord("10000002", true, false);
	
		when(transactionRepo.findByCustomerId("10000002")).thenReturn(List.of());
		
		TransactionNotFoundException transactionNotFoundException = assertThrows(TransactionNotFoundException.class,
				() ->  rewardService.getReward(rewardRecord));

		assertEquals("Transactions with customer Id 10000002 not found", transactionNotFoundException.getMessage());
	 }

    @Test
    @DisplayName("Test Get Monthly Rewards")
    void testGetRewardMonthly() {
		RewardRecord rewardRecord = new RewardRecord("10000002", false, true);
	
		when(transactionRepo.findByCustomerId("10000002")).thenReturn(getCustomerTransactions());
		RewardResponse rewardResponse = rewardService.getReward(rewardRecord);
	
		assertEquals(rewardResponse.getCustomerId(), "10000002");
		assertEquals(rewardResponse.getMonthlyRewards().entrySet().size(), 4);
		assertEquals(rewardResponse.getMonthlyRewards().get(5), 90);
		assertEquals(rewardResponse.getMonthlyRewards().get(6), 90);
		assertEquals(rewardResponse.getMonthlyRewards().get(7), 90);
		assertEquals(rewardResponse.getMonthlyRewards().get(8), 850);
    }
    
    @Test
    @DisplayName("Test Get Monthly Rewards - Throws TransactionNotFound Exception")
    void testGetRewardMonthlyNoTransactions() {
		RewardRecord rewardRecord = new RewardRecord("10000002", false, true);
	
		when(transactionRepo.findByCustomerId("10000002")).thenReturn(List.of());
		TransactionNotFoundException transactionNotFoundException = assertThrows(TransactionNotFoundException.class,
				() ->  rewardService.getReward(rewardRecord));

		assertEquals("Transactions with customer Id 10000002 not found", transactionNotFoundException.getMessage());
    }

    private List<CustomerTransaction> getCustomerTransactions() {
		List<CustomerTransaction> transactions = new ArrayList<>();
		CustomerTransaction transaction1 = new CustomerTransaction(100L, "10000002", 120.0D, LocalDate.of(2023, 5, 1));
		CustomerTransaction transaction2 = new CustomerTransaction(101L, "10000002", 120.0D, LocalDate.of(2023, 6, 1));
		CustomerTransaction transaction3 = new CustomerTransaction(102L, "10000002", 120.0D, LocalDate.of(2023, 7, 1));
		CustomerTransaction transaction4 = new CustomerTransaction(103L, "10000002", 500.0D, LocalDate.of(2023, 8, 1));
		transactions.add(transaction1);
		transactions.add(transaction2);
		transactions.add(transaction3);
		transactions.add(transaction4);
		return transactions;
    }
}
