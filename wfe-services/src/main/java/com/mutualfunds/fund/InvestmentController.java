package com.mutualfunds.fund;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mutualfunds.fund.model.FundAllocation;
import com.mutualfunds.fund.model.InvestmentPlanRequest;
import com.mutualfunds.fund.model.InvestmentPlanResponse;
import com.mutualfunds.fund.service.InvestmentService;
import com.mutualfunds.persistence.fund.domain.Fund;

@Controller
@RequestMapping("fund")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InvestmentController {

    private final @NonNull InvestmentService investmentService;

    @RequestMapping(value = "plan", method = POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<InvestmentPlanResponse> getPlan(@RequestBody InvestmentPlanRequest investmentPlanRequest) {
        InvestmentPlanResponse plan = investmentService.getPlan(
            investmentPlanRequest.getFundType(),
            investmentPlanRequest.getSelectedFunds(),
            investmentPlanRequest.getAmountToInvest());

        return new ResponseEntity<>(plan, OK);
    }
}
