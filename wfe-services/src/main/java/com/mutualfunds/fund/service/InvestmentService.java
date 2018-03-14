package com.mutualfunds.fund.service;

import java.util.List;

import com.mutualfunds.fund.model.FundAllocation;
import com.mutualfunds.fund.model.InvestmentPlanResponse;
import com.mutualfunds.persistence.fund.domain.Fund;
import com.mutualfunds.persistence.fund.domain.FundType;

public interface InvestmentService {

    InvestmentPlanResponse getPlan(FundType style, List<Fund> selectedFunds, Integer amount);
}
