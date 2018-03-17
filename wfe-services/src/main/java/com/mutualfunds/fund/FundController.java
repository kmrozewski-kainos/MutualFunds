package com.mutualfunds.fund;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mutualfunds.fund.services.FundService;
import com.mutualfunds.persistence.fund.domains.Fund;

@Controller
@RequestMapping("fund")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FundController {

    private final @NonNull FundService fundService;

    @GetMapping
    public ResponseEntity<List<Fund>> getInvestmentFunds() {
        return new ResponseEntity<>(fundService.getFunds(), OK);
    }
}
