package com.mutualfunds.fund.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class InvestmentAmount extends Investment {

    private Integer amountToDivide;

    @Builder
    public InvestmentAmount(String fundType, Double fraction, Integer amountToDivide) {
        super(fundType, fraction);
        this.amountToDivide = amountToDivide;
    }
}
