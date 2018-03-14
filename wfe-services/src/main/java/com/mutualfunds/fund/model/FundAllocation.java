package com.mutualfunds.fund.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import com.mutualfunds.persistence.fund.domain.FundType;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@RequiredArgsConstructor
public class FundAllocation {

    private @NonNull FundType type;
    private @NonNull Double share;
    private String name;
    private Integer amount = 0;
}
