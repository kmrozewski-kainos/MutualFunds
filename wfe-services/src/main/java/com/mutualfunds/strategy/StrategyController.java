package com.mutualfunds.strategy;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mutualfunds.strategy.services.StrategyService;

@Controller
@RequestMapping("strategy")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StrategyController {

    private final @NonNull StrategyService strategyService;

    @GetMapping
    public ResponseEntity<List<String>> getAllStrategyNames() {
        return new ResponseEntity<>(strategyService.getAllStrategyNames(), OK);
    }
}
