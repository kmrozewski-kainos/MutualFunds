package com.mutualfunds.fund.services;

import static org.hamcrest.CoreMatchers.equalTo;
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
import com.mutualfunds.persistence.fund.dao.FundDao;
import com.mutualfunds.persistence.fund.domains.Fund;

@RunWith(MockitoJUnitRunner.class)
public class FundServiceTest {

    private static final String FUNDS_PATH = "fixtures/input/fund_allocations_full.json";
    private static final Integer FUNDS_COUNT = 8;

    @InjectMocks
    FundServiceImpl fundService;

    private final FundDao fundDao = mock(FundDao.class);

    @Before
    public void setUp() {
        initMocks(this);
        val funds = fromJSON(FUNDS_PATH, this.getClass(), new TypeReference<List<Fund>>() {});

        when(fundDao.getAllFunds()).thenReturn(funds);
    }

    @Test
    public void when_CalledFundService_ExpectDaoToBeCalledAndCheckReturnedListSize() {
        val funds = fundService.getFunds();

        verify(fundDao, times(1)).getAllFunds();
        assertThat(funds.size(), equalTo(FUNDS_COUNT));
    }
}
