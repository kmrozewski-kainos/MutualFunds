package com.mutualfunds.persistence.strategy.mappers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mutualfunds.persistence.strategy.domains.Strategy;
import com.mutualfunds.persistence.strategy.entities.StrategyEntity;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StrategyMapper {

    private final @NonNull ModelMapper modelMapper;

    public Strategy mapEntityToDomain(StrategyEntity entity) {
        return modelMapper.map(entity, Strategy.class);
    }
}
