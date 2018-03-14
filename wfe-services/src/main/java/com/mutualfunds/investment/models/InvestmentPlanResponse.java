package com.mutualfunds.investment.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

import com.mutualfunds.investment.models.FundAllocation;

@Data
@AllArgsConstructor
public class InvestmentPlanResponse {

    private List<FundAllocation> fundAllocationList;
    private Integer residual;
}
