package com.luis.pcstore.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FileSizeValidator.class)
public @interface FileSize {
    String message() default "Invalid file size";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    long max(); // Máximo tamaño de archivo en bytes
}
