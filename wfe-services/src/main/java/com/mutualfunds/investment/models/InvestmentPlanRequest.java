package com.mutualfunds.investment.models;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mutualfunds.persistence.fund.domains.Fund;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvestmentPlanRequest {

    @NotNull
    @JsonProperty
    private String style;

    @NotNull
    @Size(min = 1)
    @JsonProperty
    private List<Fund> funds;

    @NotNull
    @Min(1)
    @JsonProperty
    private Integer amount;
}
