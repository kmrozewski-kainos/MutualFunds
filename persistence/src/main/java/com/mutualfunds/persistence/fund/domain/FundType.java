package com.mutualfunds.persistence.fund.domain;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public enum FundType {
    FOREIGN("Zagraniczne"),
    MONEY("Pieniężne"),
    POLISH("Polskie");

    private final String name;
}
