package com.mutualfunds.strategy.services;

import java.util.List;
import java.util.stream.Collectors;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutualfunds.investment.models.FundAllocation;
import com.mutualfunds.persistence.style.dao.StyleDao;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StrategyServiceImpl implements StrategyService {

    private final @NonNull StyleDao styleDao;

    @Override
    public List<FundAllocation> getInvestmentStrategy(String styleType) {
        return styleDao
            .getInvestmentStylesByName(styleType)
            .map(investmentStyle -> new FundAllocation(investmentStyle.getType(), investmentStyle.getShare()))
            .collect(Collectors.toList());
    }
}
