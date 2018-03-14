package com.mutualfunds.fund.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvestmentPlanResponse {

    private List<FundAllocation> fundAllocationList;
    private Integer residual;
}
