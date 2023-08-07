package com.naturep.rewards.demo.response;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RewardResponse {

    private String customerId;

    @JsonInclude(Include.NON_DEFAULT)
    private long total = 0;

    @JsonInclude(Include.NON_DEFAULT)
    private Map<Object, Long> monthlyRewards = null;

}
