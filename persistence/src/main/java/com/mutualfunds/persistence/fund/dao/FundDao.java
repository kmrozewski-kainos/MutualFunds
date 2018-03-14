package com.mutualfunds.persistence.fund.dao;

import java.util.List;

import com.mutualfunds.persistence.fund.domains.Fund;

public interface FundDao {

    List<Fund> getAllFunds();
}
