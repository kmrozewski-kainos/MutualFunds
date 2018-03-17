package com.mutualfunds.strategy.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import static com.mutualfunds.util.JSONParser.fromJSON;

import java.util.List;

import lombok.val;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mutualfunds.persistence.strategy.dao.StrategyDao;
import com.mutualfunds.persistence.strategy.domains.Strategy;

@RunWith(MockitoJUnitRunner.class)
public class StrategyServiceTest {

    private static final String STRATEGY_PATH = "fixtures//input/strategies_safe.json";
    private static final String STRATEGY_SAFE = "SAFE";
    private static final Long STRATEGIES_COUNT = 3L;

    @InjectMocks
    StrategyServiceImpl strategyService;

    private final StrategyDao strategyDao = mock(StrategyDao.class);

    @Before
    public void setUp() {
        initMocks(this);
        val strategies = fromJSON(STRATEGY_PATH, this.getClass(), new TypeReference<List<Strategy>>() {}).stream();

        when(strategyDao.getInvestmentStylesByName(STRATEGY_SAFE)).thenReturn(strategies);
    }

    @Test
    public void when_CalledStrategyService_ExpectDaoToBeCalledAndReturnedDataInCorrectFormat() {
        val strategies = strategyService.getInvestmentStrategyByStyle(STRATEGY_SAFE);

        verify(strategyDao, times(1)).getInvestmentStylesByName(STRATEGY_SAFE);
        assertThat(strategies.count(), equalTo(STRATEGIES_COUNT));
    }

    @Test
    public void when_CalledStrategyService_ExpectAppropriateInstancesToBeReturned() {
        val strategies = strategyService.getInvestmentStrategyByStyle(STRATEGY_SAFE);

        strategies.forEach(strategy -> assertThat(strategy, instanceOf(Strategy.class)));
    }


}
