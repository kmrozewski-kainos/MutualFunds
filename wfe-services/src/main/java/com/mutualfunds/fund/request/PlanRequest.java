package com.mutualfunds.fund.request;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.util.List;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mutualfunds.fund.domain.FundAllocation;

@Data
public class PlanRequest {

    @JsonProperty
    private String fundType;

    @JsonProperty
    private Integer amountToInvest;

    @JsonProperty
    @JsonInclude(NON_NULL)
    private List<FundAllocation> selectedFunds;
}
