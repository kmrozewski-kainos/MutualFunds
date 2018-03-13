package com.mutualfunds.fund;

import java.util.List;

import com.mutualfunds.persistence.fund.domain.Fund;

public interface FundService {

    List<Fund> getAllFunds();
}
