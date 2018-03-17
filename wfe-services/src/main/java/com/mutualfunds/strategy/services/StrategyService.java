package com.mutualfunds.strategy.services;

import java.util.List;
import java.util.stream.Stream;

import com.mutualfunds.persistence.strategy.domains.Strategy;

public interface StrategyService {

    Stream<Strategy> getInvestmentStrategyByStyle(String styleType);
    List<Strategy> getInvestmentStrategiesListByStyle(String styleType);
}
