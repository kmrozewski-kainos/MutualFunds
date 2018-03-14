package com.mutualfunds.fund.service;

import static com.mutualfunds.fund.service.InvestmentSplit.getInvestmentSubPlan;
import static com.mutualfunds.fund.service.InvestmentSplit.splitAmountBetweenFundTypes;

import java.util.List;
import java.util.Map;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mutualfunds.fund.model.FundAllocation;
import com.mutualfunds.fund.model.InvestmentPlanResponse;
import com.mutualfunds.persistence.fund.FundDao;
import com.mutualfunds.persistence.fund.domain.Fund;
import com.mutualfunds.persistence.fund.domain.FundType;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InvestmentServiceImpl implements InvestmentService {

    private final @NonNull FundDao fundDao;

    private final Map<String, List<FundAllocation>> investmentStyles = ImmutableMap
        .<String, List<FundAllocation>>builder()
        .put("bezpieczny", ImmutableList.of(
            new FundAllocation(FundType.POLISH, 0.2),
            new FundAllocation(FundType.FOREIGN, 0.75),
            new FundAllocation(FundType.MONEY, 0.05)))
        .put("zrównoważony", ImmutableList.of(
            new FundAllocation(FundType.POLISH, 0.3),
            new FundAllocation(FundType.FOREIGN, 0.6),
            new FundAllocation(FundType.MONEY, 0.1)))
        .put("agresywny", ImmutableList.of(
            new FundAllocation(FundType.POLISH, 0.4),
            new FundAllocation(FundType.FOREIGN, 0.2),
            new FundAllocation(FundType.MONEY, 0.4)))
        .build();

    @Override
    public InvestmentPlanResponse getPlan(FundType style, List<Fund> selectedFunds, Integer totalAmount) {
        val investmentPlan = splitAmountBetweenFundTypes(investmentStyles.get(style), totalAmount);
        val investmentSubPlan = getInvestmentSubPlan(investmentPlan, selectedFunds, totalAmount);

        return new InvestmentPlanResponse(investmentSubPlan, getResidual(totalAmount, investmentPlan));
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
