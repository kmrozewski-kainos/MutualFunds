package com.mutualfunds.persistence.fund.mapper;

import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mutualfunds.persistence.fund.FundEntity;
import com.mutualfunds.persistence.fund.domain.Fund;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FundMapper {

    private final ModelMapper modelMapper;

    public Fund mapEntityToDomain(FundEntity entity) {
        return modelMapper.map(entity, Fund.class);
    }
}
