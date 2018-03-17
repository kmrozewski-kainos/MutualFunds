package com.mutualfunds.investment.services;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import static com.mutualfunds.util.JSONParser.fromJSON;
import static com.mutualfunds.util.ObjectComparator.compare;
import static com.mutualfunds.util.ObjectComparator.compareList;

import java.util.List;

import lombok.val;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mutualfunds.investment.models.FundAllocation;
import com.mutualfunds.investment.models.InvestmentPlanResponse;
import com.mutualfunds.persistence.fund.domains.Fund;
import com.mutualfunds.persistence.strategy.domains.Strategy;
import com.mutualfunds.strategy.services.StrategyService;

@RunWith(MockitoJUnitRunner.class)
public class InvestmentServiceTest {

    private static final String STRATEGY_SAFE = "SAFE";
    private static final String STRATEGY_SAFE_PATH = "fixtures/input/strategies_safe.json";
    private static final String ALL_FUNDS_PATH = "fixtures/input/fund_allocations_full.json";
    private static final String PARTIAL_FUNDS_PATH = "fixtures/input/fund_allocations_partial.json";
    private static final String EXPECTED_PLAN_WITHOUT_RESIDUAL = "fixtures/expected/fund_plan_without_residual.json";
    private static final String EXPECTED_PLAN_WITH_RESIDUAL = "fixtures/expected/fund_plan_with_residual.json";
    private static final String EXPECTED_PARTIAL_PLAN_WITH_RESIDUAL = "fixtures/expected/fund_plan_without_all_funds_and_residual.json";
    private static final String EXPECTED_PARTIAL_PLAN_WITHOUT_RESIDUAL = "fixtures/expected/fund_plan_without_all_funds.json";
    private static final Integer AMOUNT_WITHOUT_RESIDUAL = 10000;
    private static final Integer AMOUNT_WITH_RESIDUAL = 10003;

    @InjectMocks
    private InvestmentServiceImpl investmentService;

    private final StrategyService strategyService = mock(StrategyService.class);

    @Before
    public void setUp() {
        initMocks(this);
        val strategies = fromJSON(STRATEGY_SAFE_PATH, this.getClass(), new TypeReference<List<Strategy>>() {}).stream();

        when(strategyService.getInvestmentStrategyByStyle(STRATEGY_SAFE)).thenReturn(strategies);
    }

    @Test
    public void when_ObtainedCorrectParams_Then_SplitAmountBetweenFundsWithoutResidualValue() {
        val funds = fromJSON(ALL_FUNDS_PATH, this.getClass(), new TypeReference<List<Fund>>() {});
        val actual = investmentService.getPlan(STRATEGY_SAFE, funds, AMOUNT_WITHOUT_RESIDUAL);
        val expected = fromJSON(EXPECTED_PLAN_WITHOUT_RESIDUAL, this.getClass(), new TypeReference<InvestmentPlanResponse>() {});

        compareInvestmentPlanResponse(actual, expected);
    }

    @Test
    public void when_ObtainedCorrectParams_Then_SplitBetweenFundsWithResidualValue() {
        val funds = fromJSON(ALL_FUNDS_PATH, this.getClass(), new TypeReference<List<Fund>>() {});
        val actual = investmentService.getPlan(STRATEGY_SAFE, funds, AMOUNT_WITH_RESIDUAL);
        val expected = fromJSON(EXPECTED_PLAN_WITH_RESIDUAL, this.getClass(), new TypeReference<InvestmentPlanResponse>() {});

        compareInvestmentPlanResponse(actual, expected);
    }

    @Test
    public void when_ObtainedCorrectParams_And_SelectedPartOfFunds_Then_SplitBetweenFundsWithResidualAndUnallocatedAmount() {
        val funds = fromJSON(PARTIAL_FUNDS_PATH, this.getClass(), new TypeReference<List<Fund>>() {});
        val actual = investmentService.getPlan(STRATEGY_SAFE, funds, AMOUNT_WITH_RESIDUAL);
        val expected = fromJSON(EXPECTED_PARTIAL_PLAN_WITH_RESIDUAL, this.getClass(), new TypeReference<InvestmentPlanResponse>() {});

        compareInvestmentPlanResponse(actual, expected);
    }

    @Test
    public void when_ObtainedCorrectParams_And_SelectedPartOfFunds_Then_SplitBetweenFundsAndReturnUnallocatedAmound() {
        val funds = fromJSON(PARTIAL_FUNDS_PATH, this.getClass(), new TypeReference<List<Fund>>() {});
        val actual = investmentService.getPlan(STRATEGY_SAFE, funds, AMOUNT_WITHOUT_RESIDUAL);
        val expected = fromJSON(EXPECTED_PARTIAL_PLAN_WITHOUT_RESIDUAL, this.getClass(), new TypeReference<InvestmentPlanResponse>() {});

        compareInvestmentPlanResponse(actual, expected);
    }

    private void compareInvestmentPlanResponse(InvestmentPlanResponse actual, InvestmentPlanResponse expected) {
        compareList(actual, expected, response -> response::getFundAllocationList, (FundAllocation fundAllocation) -> fundAllocation::getAmount);
        compareList(actual, expected, response -> response::getFundAllocationList, (FundAllocation fundAllocation) -> fundAllocation::getType);
        compareList(actual, expected, response -> response::getFundAllocationList, (FundAllocation fundAllocation) -> fundAllocation::getName);
        compareList(actual, expected, response -> response::getFundAllocationList, (FundAllocation fundAllocation) -> fundAllocation::getShare);
        compare(actual, expected, investmentPlanResponse -> investmentPlanResponse::getResidual);
    }
}
