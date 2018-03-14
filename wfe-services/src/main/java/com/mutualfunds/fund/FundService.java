package com.mutualfunds.fund;

import java.util.List;

import com.mutualfunds.fund.domain.FundAllocation;
import com.mutualfunds.persistence.fund.domain.Fund;

public interface FundService {

    List<Fund> getAllFunds();
    List<FundAllocation> getPlan(String style, Integer amount);
}
