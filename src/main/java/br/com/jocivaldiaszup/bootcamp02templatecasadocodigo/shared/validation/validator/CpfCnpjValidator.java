package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.validation.validator;

import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.validation.CpfCnpj;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.validation.validator.util.BR;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CpfCnpjValidator implements ConstraintValidator<CpfCnpj, String> {


    @Override
    public void initialize(CpfCnpj constraintAnnotation) {

    }

    @Override
    public boolean isValid(String cpfCnpj, ConstraintValidatorContext constraintValidatorContext) {
        if(cpfCnpj == null)
            return true;

        return BR.isValidCNPJ(cpfCnpj) || BR.isValidCPF(cpfCnpj);
    }
}
