package com.mutualfunds.persistence.dao;

import java.util.List;

import com.mutualfunds.persistence.domain.Fund;

public interface FundDao {

    List<Fund> getFunds();
}
