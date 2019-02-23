package com.azino.project.validation.annotations;

import com.azino.project.validation.MultipartFileValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MultipartFileValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmptyMultipartFile {

    String message() default "не должен быть пустым";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}