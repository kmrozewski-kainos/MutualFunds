package com.mutualfunds.fund.service;

import static java.lang.Math.floor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.mutualfunds.fund.model.FundAllocation;
import com.mutualfunds.persistence.fund.domain.Fund;
import com.mutualfunds.persistence.fund.domain.FundType;

final class InvestmentSplit {

    private InvestmentSplit() {
    }

    static List<FundAllocation> splitAmountBetweenFundTypes(List<FundAllocation> investmentStyles, Integer amount) {
        return investmentStyles
            .stream()
            .map(investment -> mapToInvestmentAmount(investment, amount))
            .collect(Collectors.toList());
    }

    private static FundAllocation mapToInvestmentAmount(FundAllocation investment, Integer amount) {
        return new FundAllocation()
            .setType(investment.getType())
            .setShare(investment.getShare())
            .setAmount(getAmountToInvest(amount, investment.getShare()));
    }

    private static Integer getAmountToInvest(Integer amount, Double share) {
        Double amountToInvest = floor(amount * share);

        return amountToInvest.intValue();
    }

    static List<FundAllocation> getInvestmentSubPlan(List<FundAllocation> investmentPlan, List<Fund> selectedFunds, Integer amount) {
        return investmentPlan
            .stream()
            .flatMap(investmentAmount -> splitAmountBetweenFunds(
                getFundsByType(investmentAmount.getType(), selectedFunds),
                investmentAmount.getAmount(),
                amount))
            .collect(Collectors.toList());
    }

    private static List<FundAllocation> getFundsByType(FundType fundType, List<Fund> fundEntities) {
        return fundEntities
            .stream()
            .filter(entity -> fundType.equals(entity.getType()))
            .map(entity -> new FundAllocation().setName(entity.getName()).setType(entity.getType()))
            .collect(Collectors.toList());
    }

    private static Stream<FundAllocation> splitAmountBetweenFunds(List<FundAllocation> sameTypeFundAllocations, Integer amountToInvestInFundType, Integer totalAmount) {
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
}
