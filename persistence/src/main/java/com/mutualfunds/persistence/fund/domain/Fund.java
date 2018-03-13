package com.mutualfunds.persistence.fund.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Fund {

    private String name;
    private FundType type;
}
