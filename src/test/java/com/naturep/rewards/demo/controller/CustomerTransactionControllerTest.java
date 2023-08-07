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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.naturep.rewards.demo.dto.CustomerTransactionRecord;
import com.naturep.rewards.demo.request.TransactionRequest;
import com.naturep.rewards.demo.service.CustomerTransactionService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerTransactionController.class)
public class CustomerTransactionControllerTest {

    @MockBean
    CustomerTransactionService transactionService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testLogTransaction() throws Exception {

	CustomerTransactionRecord transaction = new CustomerTransactionRecord("10001", 300.0);

	Map<String, String> map = new HashMap<>();
	map.put("result", "success");
	when(transactionService.logTransaction(transaction)).thenReturn(map);

	mockMvc.perform(MockMvcRequestBuilders.post("/transaction/spent").contentType(MediaType.APPLICATION_JSON)
		.content(asJsonString(new TransactionRequest("10001", 300.0))).accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.aMapWithSize(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.result", Matchers.is("success")));
    }

    static String asJsonString(final Object obj) {
	try {
	    return new ObjectMapper().writeValueAsString(obj);
	} catch (Exception e) {
	    throw new RuntimeException(e);
	}
    }

}
