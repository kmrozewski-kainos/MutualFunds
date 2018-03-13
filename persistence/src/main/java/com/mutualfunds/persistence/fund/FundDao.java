package com.mutualfunds.persistence.fund;

import java.util.List;

import com.mutualfunds.persistence.fund.domain.Fund;

public interface FundDao {

    List<Fund> getAllFunds();
}
