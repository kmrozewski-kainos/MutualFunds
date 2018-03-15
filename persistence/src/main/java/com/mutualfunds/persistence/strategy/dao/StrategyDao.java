package com.mutualfunds.persistence.strategy.dao;

import java.util.stream.Stream;

import com.mutualfunds.persistence.strategy.domains.Strategy;

public interface StrategyDao {

    Stream<Strategy> getInvestmentStylesByName(String styleType);
}
