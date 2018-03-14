package com.mutualfunds.persistence.style.dao;

import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mutualfunds.persistence.style.domains.Style;
import com.mutualfunds.persistence.style.entities.StyleEntity;
import com.mutualfunds.persistence.style.mappers.StyleMapper;

@Transactional
@Repository
@AllArgsConstructor
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StyleDaoImpl implements StyleDao {

    private final @NonNull StyleMapper mapper;
    private static final String QUERY_NAME = "getInvestmentStyleByName";
    private static final String FILTERING_ARG = "styleType";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Stream<Style> getInvestmentStylesByName(String styleType) {
        return entityManager
            .createNamedQuery(QUERY_NAME, StyleEntity.class)
            .setParameter(FILTERING_ARG, styleType)
            .getResultList()
            .stream()
            .map(mapper::mapEntityToDomain);
    }
}
