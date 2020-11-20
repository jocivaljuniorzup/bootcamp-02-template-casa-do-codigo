package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.validation;

import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.validation.validator.CpfCnpjValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({ FIELD, METHOD })
@Retention(RUNTIME)
@Constraint(validatedBy = CpfCnpjValidator.class)
public @interface CpfCnpj {

    String message() default "Invalid CPF/CNPJ";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
