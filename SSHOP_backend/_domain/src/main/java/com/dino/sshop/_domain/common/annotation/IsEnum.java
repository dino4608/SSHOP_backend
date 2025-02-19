package com.dino.sshop._domain.common.annotation;

import com.dino.sshop._domain.common.annotation.impl.IsEnumValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
        ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = IsEnumValidator.class)
public @interface IsEnum {
    Class<? extends Enum<?>> enumClass();
    String message() default "should be any of enum {enumClass}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
