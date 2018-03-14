package com.mutualfunds.persistence.fund;

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

import com.mutualfunds.persistence.fund.domain.Fund;
import com.mutualfunds.persistence.fund.mapper.FundMapper;

@Transactional
@Repository
@AllArgsConstructor
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FundDaoImpl implements FundDao {

    private final @NonNull FundMapper fundMapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Fund> getAllFunds() {
        return entityManager
            .createNamedQuery("getFunds", FundEntity.class)
            .getResultList()
            .stream()
            .map(fundMapper::mapEntityToDomain)
            .collect(Collectors.toList());
    }
}
