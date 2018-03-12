package com.mutualfunds.persistence.dao.hibernate.mapper;

import static org.modelmapper.convention.MatchingStrategies.STRICT;

import org.modelmapper.ModelMapper;

public abstract class Mapper {

    private ModelMapper modelMapper;

    public Mapper() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(STRICT);
    }

    protected ModelMapper getModelMapper() {
        return modelMapper;
    }
}
