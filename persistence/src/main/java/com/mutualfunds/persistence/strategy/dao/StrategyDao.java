package com.mutualfunds.persistence.strategy.dao;

import java.util.List;
import java.util.stream.Stream;

import com.mutualfunds.persistence.strategy.domains.Strategy;

public interface StrategyDao {

    Stream<Strategy> getInvestmentStrategiesByName(String styleType);
    List<String> getAllStrategyNames();
}
