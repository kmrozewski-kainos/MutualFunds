package com.mutualfunds.fund;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mutualfunds.persistence.fund.domain.Fund;

@Controller
@RequestMapping("fund")
public class FundController {

    @Autowired
    private FundService fundService;

    @GetMapping("all")
    public ResponseEntity<List<Fund>> getFunds() {
        List<Fund> list = fundService.getAllFunds();

        return new ResponseEntity<>(list, OK);
    }
}
