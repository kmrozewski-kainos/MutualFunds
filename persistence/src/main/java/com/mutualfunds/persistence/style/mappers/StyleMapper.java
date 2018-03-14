package com.mutualfunds.persistence.style.mappers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mutualfunds.persistence.style.domains.Style;
import com.mutualfunds.persistence.style.entities.StyleEntity;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StyleMapper {

    private final @NonNull ModelMapper modelMapper;

    public Style mapEntityToDomain(StyleEntity entity) {
        return modelMapper.map(entity, Style.class);
    }
}
