package com.naturep.rewards.demo.request;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RewardRequest {

    @NotBlank
    private long customerId;

    private boolean total;

    private boolean totalByMonth;

}
