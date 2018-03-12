package com.mutualfunds.persistence.dao.hibernate.mapper;

import com.mutualfunds.persistence.dao.hibernate.entities.FundEntity;
import com.mutualfunds.persistence.domain.Fund;

public class FundMapper extends Mapper {

    public Fund mapFundEntityToFund(FundEntity entity) {
        return getModelMapper().map(entity, Fund.class);
    }

    public FundEntity mapFundToFundEntity(Fund domain) {
        return getModelMapper().map(domain, FundEntity.class);
    }
}
