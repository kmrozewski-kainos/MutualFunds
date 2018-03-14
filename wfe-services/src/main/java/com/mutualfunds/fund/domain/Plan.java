package com.mutualfunds.fund.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Plan {

    private List<InvestmentAmount> amountsToInvest;
    private Integer residual;
}
