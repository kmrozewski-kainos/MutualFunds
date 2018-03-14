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

import com.mutualfunds.fund.domain.FundAllocation;
import com.mutualfunds.fund.request.PlanRequest;
import com.mutualfunds.persistence.fund.FundEntity;
import com.mutualfunds.persistence.fund.domain.Fund;

@Controller
@RequestMapping("fund")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FundController {

    private final @NonNull FundService fundService;

    @GetMapping("all")
    public ResponseEntity<List<Fund>> getFunds() {
        List<Fund> list = fundService.getAllFunds();

        return new ResponseEntity<>(list, OK);
    }

    @RequestMapping(value = "plan", method = POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<FundAllocation>> getPlan(@RequestBody PlanRequest planRequest) {
        List<FundAllocation> fundAllocations = fundService.getPlan(planRequest.getFundType(), planRequest.getAmountToInvest());

        return new ResponseEntity<>(fundAllocations, OK);
    }
}
