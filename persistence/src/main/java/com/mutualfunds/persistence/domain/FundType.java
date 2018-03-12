package com.mutualfunds.persistence.domain;

public enum FundType {
    MONEY ("Pieniężny"),
    POLISH ("Polski"),
    FOREIGN ("Zagraniczny");

    private final String name;

    FundType(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
