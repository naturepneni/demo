package com.naturep.rewards.demo.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.naturep.rewards.demo.domain.CustomerTransaction;
import com.naturep.rewards.demo.dto.CustomerTransactionRecord;
import com.naturep.rewards.demo.repository.CustomerTransactionRepo;
import com.naturep.rewards.demo.service.impl.CustomerTransactionServiceImpl;
import com.naturep.rewards.demo.service.impl.Transformer;

@ExtendWith(MockitoExtension.class)
public class CustomerTransactionServiceTest {

    @Mock
    private CustomerTransactionRepo transactionRepo;

    @Mock
    private Transformer transformer;

    @InjectMocks
    private CustomerTransactionService transactionService = new CustomerTransactionServiceImpl();

    @Test
    @DisplayName("Test Save Transaction")
    void testLogTransaction() {

	CustomerTransactionRecord record = new CustomerTransactionRecord("10000002", 500.0D);
	CustomerTransaction transaction = new CustomerTransaction(103L, "10000002", 500.0D, LocalDate.of(2023, 8, 1));

	when(transformer.transactionRecordToDomain(record)).thenReturn(transaction);
	when(transactionRepo.save(any(CustomerTransaction.class))).thenReturn(transaction);

	transactionService.logTransaction(record);
	verify(transactionRepo, times(1)).save(any(CustomerTransaction.class));
    }

}
