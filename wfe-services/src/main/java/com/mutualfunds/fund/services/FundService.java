package com.mutualfunds.fund.services;

import java.util.List;

import com.mutualfunds.persistence.fund.domains.Fund;

public interface FundService {

    List<Fund> getFunds();
}
