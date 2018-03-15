package com.mutualfunds.strategy;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mutualfunds.persistence.strategy.domains.Strategy;
import com.mutualfunds.strategy.services.StrategyService;

@Controller
@RequestMapping("style")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StrategyController {

    private final @NonNull StrategyService strategyService;

    @GetMapping("{styleType}")
    public ResponseEntity<List<Strategy>> getInvestmentStyles(@PathVariable(value = "styleType") String styleType) {
        return new ResponseEntity<>(strategyService.getInvestmentStrategy(styleType), OK);
    }
}
