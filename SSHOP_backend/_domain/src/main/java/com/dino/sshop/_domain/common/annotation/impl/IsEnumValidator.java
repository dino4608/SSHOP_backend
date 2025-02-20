package com.dino.sshop._domain.common.annotation.impl;

import com.dino.sshop._domain.common.annotation.IsEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IsEnumValidator implements ConstraintValidator<IsEnum, CharSequence> {
    private List<String> acceptedValues;

    @Override
    public void initialize(IsEnum annotation) {
        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return acceptedValues.contains(value.toString());
    }
}
