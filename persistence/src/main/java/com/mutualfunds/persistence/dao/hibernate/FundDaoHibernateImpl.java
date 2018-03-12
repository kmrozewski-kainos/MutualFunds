package com.mutualfunds.persistence.dao.hibernate;

import java.util.List;
import java.util.stream.Collectors;

import io.dropwizard.hibernate.AbstractDAO;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mutualfunds.persistence.dao.FundDao;
import com.mutualfunds.persistence.dao.hibernate.entities.FundEntity;
import com.mutualfunds.persistence.dao.hibernate.mapper.FundMapper;
import com.mutualfunds.persistence.domain.Fund;

@Repository
public class FundDaoHibernateImpl extends AbstractDAO<FundEntity> implements FundDao {

    private static final String SELECT_ALL_NAMED_QUERY = "getFunds";

    @Autowired
    private FundMapper mapper;

    @Autowired
    public FundDaoHibernateImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Fund> getFunds() {
        Query query = currentSession().getNamedQuery(SELECT_ALL_NAMED_QUERY);

        return list(query)
            .stream()
            .map(mapper::mapFundEntityToFund)
            .collect(Collectors.toList());
    }
}
