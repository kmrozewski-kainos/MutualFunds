package com.mutualfunds.strategy.services;

import java.util.List;

import com.mutualfunds.persistence.strategy.domains.Strategy;

public interface StrategyService {

    List<Strategy> getInvestmentStrategy(String styleType);
}
