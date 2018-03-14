package com.mutualfunds.persistence.fund.domain;

import lombok.Data;

@Data
public class Fund {

    private String name;
    private FundType type;
}
