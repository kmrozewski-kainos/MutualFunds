package com.mutualfunds.investment;

import static java.util.Collections.singletonList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

import java.util.ArrayList;
import java.util.List;

import lombok.val;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.validation.Errors;

import com.mutualfunds.investment.models.InvestmentPlanRequest;
import com.mutualfunds.investment.models.InvestmentPlanResponse;
import com.mutualfunds.investment.services.InvestmentService;
import com.mutualfunds.persistence.fund.domains.Fund;

@RunWith(MockitoJUnitRunner.class)
public class InvestmentControllerTest {

    private static final String STRATEGY_SAFE = "SAFE";
    private static final List<Fund> EMPTY_FUND_LIST = new ArrayList<>();
    private static final List<Fund> FUND_LIST = singletonList(new Fund());

    @InjectMocks
    private InvestmentController investmentController;

    private final InvestmentService investmentService = mock(InvestmentService.class);
    private final Errors errors = mock(Errors.class);

    @Test
    public void when_ObtainedAllCorrectParameters_Then_OK() {
        when(errors.hasErrors()).thenReturn(false);
        when(investmentService.getPlan(anyString(), anyList(), anyInt())).thenReturn(new InvestmentPlanResponse());

        val response = investmentController.getPlan(new InvestmentPlanRequest(STRATEGY_SAFE, FUND_LIST, 1000), errors);

        assertThat(response.getStatusCode(), equalTo(OK));
        verify(investmentService, times(1)).getPlan(anyString(), anyList(), anyInt());
    }

    @Test
    public void when_ObtainedUncorrectParameters_Then_BadRequest() {
        when(errors.hasErrors()).thenReturn(true);

        val response = investmentController.getPlan(new InvestmentPlanRequest(STRATEGY_SAFE, EMPTY_FUND_LIST, 200), errors);

        assertThat(response.getStatusCode(), equalTo(BAD_REQUEST));
        verify(investmentService, times(0)).getPlan(anyString(), anyList(), anyInt());
    }
}
