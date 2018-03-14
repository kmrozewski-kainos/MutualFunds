package com.mutualfunds.fund;

import static java.lang.Math.floor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mutualfunds.fund.domain.FundAllocation;
import com.mutualfunds.fund.domain.Investment;
import com.mutualfunds.fund.domain.InvestmentAmount;
import com.mutualfunds.fund.domain.Plan;
import com.mutualfunds.persistence.fund.FundDao;
import com.mutualfunds.persistence.fund.domain.Fund;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FundServiceImpl implements FundService {

    private final @NonNull FundDao fundDao;

    private final Map<String, List<Investment>> investmentStyles = ImmutableMap
        .<String, List<Investment>>builder()
        .put("bezpieczny", ImmutableList.of(
            new Investment("Polskie", 0.2),
            new Investment("Zagraniczne", 0.75),
            new Investment("Pieniężne", 0.05)))
        .put("zrównoważony", ImmutableList.of(
            new Investment("Polskie", 0.3),
            new Investment("Zagraniczne", 0.6),
            new Investment("Pieniężne", 0.1)))
        .put("agresywny", ImmutableList.of(
            new Investment("Polskie", 0.4),
            new Investment("Zagraniczne", 0.2),
            new Investment("Pieniężne", 0.4)))
        .build();

    @Override
    public List<Fund> getAllFunds() {
        return fundDao.getAllFunds();
    }

    @Override
    public List<FundAllocation> getPlan(String style, Integer amount) {
        val plan = divideOverall(style, amount);
        val allFunds = fundDao.getAllFunds();

        return getInvestmentSubPlan(plan, allFunds, amount);
    }

    private List<FundAllocation> getInvestmentSubPlan(Plan plan, List<Fund> selectedFunds, Integer amount) {
        return plan
            .getAmountsToInvest()
            .stream()
            .flatMap(investmentAmount -> divideInvestmentBetweenFunds(investmentAmount, getFundsByType(investmentAmount.getFundType(), selectedFunds), amount))
            .collect(Collectors.toList());
    }

    private List<FundAllocation> getFundsByType(String fundType, List<Fund> fundEntities) {
        return fundEntities
            .stream()
            .filter(entity -> fundType.equals(entity.getType()))
            .map(entity -> new FundAllocation(entity.getName(), entity.getType()))
            .collect(Collectors.toList());
    }

    private Plan divideOverall(String style, Integer amount) {
        val investments = investmentStyles.get(style);
        val amountsToInvest = investments
            .stream()
            .map(investment -> mapToInvestmentAmount(investment, amount))
            .collect(Collectors.toList());

        return new Plan(amountsToInvest, getResidual(amount, amountsToInvest));
    }

    private InvestmentAmount mapToInvestmentAmount(Investment investment, Integer amount) {
        Double amountToInvest = floor(amount * investment.getFraction());
        return new InvestmentAmount(investment.getFundType(), investment.getFraction(), amountToInvest.intValue());
    }

    private Integer getResidual(Integer initialAmount, List<InvestmentAmount> amountsToInvest) {
        return initialAmount - amountsToInvest
            .stream()
            .reduce(
                0,
                (sum, investmentAmount) -> sum += investmentAmount.getAmountToDivide(),
                (sum1, sum2) -> sum1 + sum2);
    }

    private Stream<FundAllocation> divideInvestmentBetweenFunds(InvestmentAmount investmentAmount, List<FundAllocation> sameTypeFundAllocations, Integer amount) {

        Integer amountToDivide = investmentAmount.getAmountToDivide();
        Double fraction = 1.00 / sameTypeFundAllocations.size();
        Double amountToInvest = floor(amountToDivide * fraction);
        Double totalFraction = (amountToInvest * 1.0) / amount;
        Integer residual = amountToDivide - (amountToInvest.intValue() * sameTypeFundAllocations.size());

        sameTypeFundAllocations
            .forEach(fundAllocation -> fundAllocation.setAmount(amountToInvest.intValue()).setFraction(totalFraction));

        if (residual > 0 && sameTypeFundAllocations.size() > 0) {
            FundAllocation firstFundAllocation = sameTypeFundAllocations.get(0);
            firstFundAllocation.setAmount(firstFundAllocation.getAmount() + residual);
            firstFundAllocation.setFraction((firstFundAllocation.getAmount() * 1.0) / amount);
        }

        return sameTypeFundAllocations.stream();
    }
}
