package com.mutualfunds.investment;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mutualfunds.investment.models.InvestmentPlanRequest;
import com.mutualfunds.investment.models.InvestmentPlanResponse;
import com.mutualfunds.investment.services.InvestmentService;

@Controller
@RequestMapping("fund")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InvestmentController {

    private final @NonNull InvestmentService investmentService;

    @RequestMapping(value = "plan", method = POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<InvestmentPlanResponse> getPlan(@RequestBody InvestmentPlanRequest investmentPlanRequest) {
        InvestmentPlanResponse plan = investmentService.getPlan(
            investmentPlanRequest.getStyle(),
            investmentPlanRequest.getFunds(),
            investmentPlanRequest.getAmount());

        return new ResponseEntity<>(plan, OK);
    }
}
