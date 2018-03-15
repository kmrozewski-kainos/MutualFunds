package com.mutualfunds.persistence.strategy.dao;

import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mutualfunds.persistence.strategy.domains.Strategy;
import com.mutualfunds.persistence.strategy.entities.StrategyEntity;
import com.mutualfunds.persistence.strategy.mappers.StrategyMapper;

@Transactional
@Repository
@AllArgsConstructor
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StrategyDaoImpl implements StrategyDao {

    private final @NonNull StrategyMapper mapper;
    private static final String QUERY_NAME = "getInvestmentStyleByName";
    private static final String FILTERING_ARG = "styleType";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Stream<Strategy> getInvestmentStylesByName(String styleType) {
        return entityManager
            .createNamedQuery(QUERY_NAME, StrategyEntity.class)
            .setParameter(FILTERING_ARG, styleType)
            .getResultList()
            .stream()
            .map(mapper::mapEntityToDomain);
    }
}
