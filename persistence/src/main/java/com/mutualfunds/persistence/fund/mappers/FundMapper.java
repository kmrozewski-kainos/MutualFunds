package com.mutualfunds.persistence.fund.mappers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mutualfunds.persistence.fund.entities.FundEntity;
import com.mutualfunds.persistence.fund.domains.Fund;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FundMapper {

    private final @NonNull ModelMapper modelMapper;

    public Fund mapEntityToDomain(FundEntity entity) {
        return modelMapper.map(entity, Fund.class);
    }
}
