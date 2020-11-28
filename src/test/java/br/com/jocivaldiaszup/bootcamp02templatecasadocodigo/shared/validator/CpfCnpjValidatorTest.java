package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.validator;

import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.validation.validator.CpfCnpjValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CpfCnpjValidatorTest {

    @ParameterizedTest
    @CsvSource({
            "85209205000134, true",
            "33475736025475, false",
            "72413634002, true",
            "14603525038, false"
    })
    public void givenTests_whenIsValid_thenMatch(String cpfCnpj, boolean result) {
        CpfCnpjValidator cpfCnpjValidator = new CpfCnpjValidator();

        Assertions.assertEquals(result, cpfCnpjValidator.isValid(cpfCnpj, null));
    }
}
