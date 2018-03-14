package com.mutualfunds.investment.services;

import java.util.List;

import com.mutualfunds.investment.models.InvestmentPlanResponse;
import com.mutualfunds.persistence.fund.domains.Fund;

public interface InvestmentService {
    InvestmentPlanResponse getPlan(String style, List<Fund> selectedFunds, Integer amount);
}
