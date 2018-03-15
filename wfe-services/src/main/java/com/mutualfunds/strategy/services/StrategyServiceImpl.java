package com.mutualfunds.strategy.services;

import java.util.List;
import java.util.stream.Collectors;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutualfunds.investment.models.FundAllocation;
import com.mutualfunds.persistence.strategy.dao.StrategyDao;
import com.mutualfunds.persistence.strategy.domains.Strategy;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StrategyServiceImpl implements StrategyService {

    private final @NonNull StrategyDao strategyDao;

    @Override
    public List<Strategy> getInvestmentStrategy(String styleType) {
        return strategyDao
            .getInvestmentStylesByName(styleType)
            .collect(Collectors.toList());
    }
}
