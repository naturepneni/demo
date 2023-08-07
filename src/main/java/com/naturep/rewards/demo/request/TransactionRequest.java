package com.naturep.rewards.demo.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TransactionRequest {

    @NotEmpty
    private String customerId;

    @NotNull
    private Double amountSpent;

}
