package com.mutualfunds.investment.services;

import static java.lang.Math.floor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutualfunds.investment.models.FundAllocation;
import com.mutualfunds.investment.models.InvestmentPlanResponse;
import com.mutualfunds.persistence.fund.domains.Fund;
import com.mutualfunds.strategy.services.StrategyService;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InvestmentServiceImpl implements InvestmentService {

    private final @NonNull StrategyService investmentStrategyService;

    @Override
    public InvestmentPlanResponse getPlan(String style, List<Fund> selectedFunds, Integer totalAmount) {
        val investmentPlan = splitAmountBetweenFundTypes(investmentStrategyService.getInvestmentStrategy(style), totalAmount);
        val investmentSubPlan = getInvestmentSubPlan(investmentPlan, selectedFunds, totalAmount);

        return new InvestmentPlanResponse(investmentSubPlan, getResidual(totalAmount, investmentPlan));
    }

    private List<FundAllocation> splitAmountBetweenFundTypes(List<FundAllocation> investmentStyles, Integer amount) {
        return investmentStyles
            .stream()
            .map(investment -> mapToInvestmentAmount(investment, amount))
            .collect(Collectors.toList());
    }

    private FundAllocation mapToInvestmentAmount(FundAllocation investment, Integer amount) {
        return new FundAllocation()
            .setType(investment.getType())
            .setShare(investment.getShare())
            .setAmount(getAmountToInvest(amount, investment.getShare()));
    }

    private Integer getAmountToInvest(Integer amount, Double share) {
        Double amountToInvest = floor(amount * share);

        return amountToInvest.intValue();
    }

    private List<FundAllocation> getInvestmentSubPlan(List<FundAllocation> investmentPlan, List<Fund> selectedFunds, Integer amount) {
        return investmentPlan
            .stream()
            .flatMap(investmentAmount -> splitAmountBetweenFunds(
                getFundsByType(investmentAmount.getType(), selectedFunds),
                investmentAmount.getAmount(),
                amount))
            .collect(Collectors.toList());
    }

    private List<FundAllocation> getFundsByType(String fundType, List<Fund> fundEntities) {
        return fundEntities
            .stream()
            .filter(entity -> fundType.equals(entity.getType()))
            .map(entity -> new FundAllocation().setName(entity.getName()).setType(entity.getType()))
            .collect(Collectors.toList());
    }

    private Stream<FundAllocation> splitAmountBetweenFunds(List<FundAllocation> sameTypeFundAllocations, Integer amountToInvestInFundType, Integer totalAmount) {
        Integer amountToInvest = getAmountToInvest(amountToInvestInFundType, 1.00 / sameTypeFundAllocations.size());
        Double totalShare = (amountToInvest * 1.0) / totalAmount;
        Integer residual = amountToInvestInFundType - (amountToInvest * sameTypeFundAllocations.size());

        sameTypeFundAllocations
            .forEach(fundAllocation -> fundAllocation.setAmount(amountToInvest).setShare(totalShare));

        if (residual > 0 && sameTypeFundAllocations.size() > 0) {
            FundAllocation firstFundAllocation = sameTypeFundAllocations.get(0);
            firstFundAllocation.setAmount(firstFundAllocation.getAmount() + residual);
            firstFundAllocation.setShare((firstFundAllocation.getAmount() * 1.0) / totalAmount);
        }

        return sameTypeFundAllocations.stream();
    }

    private Integer getResidual(Integer totalAmount, List<FundAllocation> amountsToInvest) {
        return totalAmount - amountsToInvest
            .stream()
            .reduce(
                0,
                (sum, investmentAmount) -> sum += investmentAmount.getAmount(),
                (sum1, sum2) -> sum1 + sum2);
    }
}
