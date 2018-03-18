package com.mutualfunds.strategy.services;

import java.util.List;
import java.util.stream.Stream;

import com.mutualfunds.persistence.strategy.domains.Strategy;

public interface StrategyService {

    Stream<Strategy> getInvestmentStrategyStreamByStyle(String styleType);
    List<String> getAllStrategyNames();
}
