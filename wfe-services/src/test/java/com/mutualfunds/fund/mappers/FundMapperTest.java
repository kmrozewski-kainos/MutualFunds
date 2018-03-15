package com.mutualfunds.fund.mappers;

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
import com.mutualfunds.persistence.fund.domains.Fund;
import com.mutualfunds.persistence.fund.entities.FundEntity;
import com.mutualfunds.persistence.fund.mappers.FundMapper;

@RunWith(MockitoJUnitRunner.class)
public class FundMapperTest {

    private static final String INPUT_FUND_ENTITY_PATH = "fixtures/input/fund_entity.json";
    private static final String EXPECTED_FUND_DOMAIN = "fixtures/expected/fund_domain.json";

    private final ModelMapper modelMapper = spy(ModelMapper.class);

    @InjectMocks
    private FundMapper fundMapper;

    @Test
    public void when_MappedFundEntityToFundDomain_Expect_CorrectValues() {
        Mockito.doCallRealMethod().when(modelMapper).map(any(), any());

        val fundEntity = fromJSON(INPUT_FUND_ENTITY_PATH, this.getClass(), new TypeReference<FundEntity>() {});
        val expected = fromJSON(EXPECTED_FUND_DOMAIN, this.getClass(), new TypeReference<Fund>() {});
        val actual = fundMapper.mapEntityToDomain(fundEntity);

        compareFund(actual, expected);
    }

    private void compareFund(Fund actual, Fund expected) {
        compare(actual, expected, fund -> fund::getName);
        compare(actual, expected, fund -> fund::getType);
    }
}
