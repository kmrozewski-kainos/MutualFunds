package com.mutualfunds.strategy.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutualfunds.persistence.strategy.dao.StrategyDao;
import com.mutualfunds.persistence.strategy.domains.Strategy;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StrategyServiceImpl implements StrategyService {

    private final @NonNull StrategyDao strategyDao;

    @Override
    public Stream<Strategy> getInvestmentStrategyByStyle(String styleType) {
        return strategyDao
            .getInvestmentStrategiesByName(styleType);
    }

    @Override
    public List<Strategy> getInvestmentStrategiesListByStyle(String styleType) {
        return getInvestmentStrategyByStyle(styleType)
            .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllStrategyNames() {
        return strategyDao.getAllStrategyNames();
    }
}
