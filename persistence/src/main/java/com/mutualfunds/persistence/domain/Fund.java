package com.mutualfunds.persistence.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Fund {

    private String name;
    private FundType type;
}
