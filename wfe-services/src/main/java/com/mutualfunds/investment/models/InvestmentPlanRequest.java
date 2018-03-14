package com.mutualfunds.investment.models;

import java.util.List;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mutualfunds.persistence.fund.domains.Fund;

@Data
public class InvestmentPlanRequest {

    @JsonProperty
    private String style;

    @JsonProperty
    private List<Fund> funds;

    @JsonProperty
    private Integer amount;
}
