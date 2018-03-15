package com.mutualfunds.strategy.mappers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;

import static com.mutualfunds.util.JSONParser.fromJSON;
import static com.mutualfunds.util.ObjectComparator.compare;

import lombok.val;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mutualfunds.persistence.strategy.domains.Strategy;
import com.mutualfunds.persistence.strategy.entities.StrategyEntity;
import com.mutualfunds.persistence.strategy.mappers.StrategyMapper;

@RunWith(MockitoJUnitRunner.class)
public class StrategyMapperTest {

    private static final String INPUT_STRATEGY_ENTITY_PATH = "fixtures/input/strategy_entity.json";
    private static final String EXPECTED_STRATEGY_DOMAIN_PATH = "fixtures/expected/strategy_domain.json";

    private final ModelMapper modelMapper = spy(ModelMapper.class);

    @InjectMocks
    private StrategyMapper strategyMapper;

    @Test
    public void when_MappedStrategyEntityToStrategyDomain_Expect_CorrectValues() {
        Mockito.doCallRealMethod().when(modelMapper).map(any(), any());

        val strategyEntity = fromJSON(INPUT_STRATEGY_ENTITY_PATH, this.getClass(), new TypeReference<StrategyEntity>() {});
        val expected = fromJSON(EXPECTED_STRATEGY_DOMAIN_PATH, this.getClass(), new TypeReference<Strategy>() {});
        val actual = strategyMapper.mapEntityToDomain(strategyEntity);

        compareStrategy(actual, expected);
    }

    private void compareStrategy(Strategy actual, Strategy expected) {
        compare(actual, expected, strategy -> strategy::getShare);
        compare(actual, expected, strategy -> strategy::getType);
    }
}
