package com.mutualfunds.fund.model;

import java.util.List;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mutualfunds.persistence.fund.domain.Fund;
import com.mutualfunds.persistence.fund.domain.FundType;

@Data
public class InvestmentPlanRequest {

    @JsonProperty
    private FundType fundType;

    @JsonProperty
    private List<Fund> selectedFunds;

    @JsonProperty
    private Integer amountToInvest;
}
