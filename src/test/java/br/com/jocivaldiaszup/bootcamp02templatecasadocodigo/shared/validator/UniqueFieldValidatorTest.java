package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.validator;

import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.author.Author;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.validation.UniqueField;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.validation.validator.ExistsIdValidator;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.validation.validator.UniqueFieldValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.Payload;
import java.lang.annotation.Annotation;
import java.util.List;

public class UniqueFieldValidatorTest {

    private EntityManager entityManager = Mockito.mock(EntityManager.class);
    private TypedQuery<Integer> query = Mockito.mock(TypedQuery.class);

    UniqueField uniqueField = new UniqueField(){
        @Override
        public Class<? extends Annotation> annotationType() {
            return null;
        }

        @Override
        public String message() {
            return null;
        }

        @Override
        public Class<?>[] groups() {
            return new Class[0];
        }

        @Override
        public Class<? extends Payload>[] payload() {
            return new Class[0];
        }

        public String fieldName(){
                return "email";
            };

        public Class<?> domainClass(){
            return Author.class;
        }
    };

    @Test
    public void givenUniqueField_whenIsValid_thenTrue() {
        UniqueFieldValidator uniqueFieldValidator = new UniqueFieldValidator(entityManager);
        uniqueFieldValidator.initialize(uniqueField);

        List<Integer> emptyList = List.of();

        Mockito.when(entityManager.createQuery("select 1 from "+Author.class.getName()+" where email=:value")).thenReturn(query);
        Mockito.when(query.setParameter("value", "email@email.com")).thenReturn(query);
        Mockito.when(query.getResultList()).thenReturn(emptyList);

        Assertions.assertTrue(uniqueFieldValidator.isValid("email@email.com", null));
    }

    @Test
    public void givenNonUniqueField_whenIsValid_thenFalse() {
        UniqueFieldValidator uniqueFieldValidator = new UniqueFieldValidator(entityManager);
        uniqueFieldValidator.initialize(uniqueField);

        List<Integer> emptyList = List.of(1);

        Mockito.when(entityManager.createQuery("select 1 from "+Author.class.getName()+" where email=:value")).thenReturn(query);
        Mockito.when(query.setParameter("value", "email@email.com")).thenReturn(query);
        Mockito.when(query.getResultList()).thenReturn(emptyList);

        Assertions.assertFalse(uniqueFieldValidator.isValid("email@email.com", null));
    }

    @Test
    public void givenInvalidListSize_whenIsValid_thenThrowException() {
        UniqueFieldValidator uniqueFieldValidator = new UniqueFieldValidator(entityManager);
        uniqueFieldValidator.initialize(uniqueField);

        List<Integer> emptyList = List.of(1,2);

        Mockito.when(entityManager.createQuery("select 1 from "+Author.class.getName()+" where email=:value")).thenReturn(query);
        Mockito.when(query.setParameter("value", "email@email.com")).thenReturn(query);
        Mockito.when(query.getResultList()).thenReturn(emptyList);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            uniqueFieldValidator.isValid("email@email.com", null);
        });
    }

}
