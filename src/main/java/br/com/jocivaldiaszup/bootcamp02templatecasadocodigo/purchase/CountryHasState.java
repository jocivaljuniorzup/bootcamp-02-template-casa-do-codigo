package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.purchase;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = CountryHasStateValidator.class)
public @interface CountryHasState {
    String message() default "Database foreign key violation";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
