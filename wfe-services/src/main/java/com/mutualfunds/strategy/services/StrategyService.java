package com.mutualfunds.strategy.services;

import java.util.List;

import com.mutualfunds.investment.models.FundAllocation;

public interface StrategyService {

    List<FundAllocation> getInvestmentStrategy(String styleType);
}
