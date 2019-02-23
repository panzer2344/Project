package com.azino.project.validation;

import com.azino.project.validation.annotations.NotEmptyMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MultipartFileValidator implements ConstraintValidator<NotEmptyMultipartFile, MultipartFile> {
    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        return file != null && !file.isEmpty() && 0 != file.getSize();
    }

    @Override
    public void initialize(NotEmptyMultipartFile constraintAnnotation) {
    }
}
