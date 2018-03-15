package com.mutualfunds.investment.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@RequiredArgsConstructor
public class FundAllocation {

    private @NonNull String type;
    private @NonNull Double share;
    private String name;
    private Integer amount = 0;
}
