package com.mutualfunds.persistence.fund;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.mutualfunds.persistence.fund.domain.Fund;

@Transactional
@Repository
public class FundDaoImpl implements FundDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Fund> getAllFunds() {
        return (List<Fund>) entityManager
            .createNamedQuery("getFunds")
            .getResultList();
    }
}
