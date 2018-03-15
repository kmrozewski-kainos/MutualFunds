package com.mutualfunds.investment.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.mutualfunds.investment.models.FundAllocation;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvestmentPlanResponse {

    private List<FundAllocation> fundAllocationList;
    private Integer residual;
}
