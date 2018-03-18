package com.mutualfunds.persistence.fund.dao;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mutualfunds.persistence.fund.domains.Fund;
import com.mutualfunds.persistence.fund.entities.FundEntity;
import com.mutualfunds.persistence.fund.mappers.FundMapper;

@Transactional
@Repository
@AllArgsConstructor
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FundDaoImpl implements FundDao {

    private static final String FUNDS_ALL_QUERY = "getFunds";

    private final @NonNull FundMapper mapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Fund> getAllFunds() {
        return entityManager
            .createNamedQuery(FUNDS_ALL_QUERY, FundEntity.class)
            .getResultList()
            .stream()
            .map(mapper::mapEntityToDomain)
            .collect(Collectors.toList());
    }
}
