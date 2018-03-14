package com.mutualfunds.fund.domain;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@RequiredArgsConstructor
public class FundAllocation {

    private @NonNull String name;
    private @NonNull String type;
    private Integer amount = 0;
    private Double fraction = 0.0;
}
