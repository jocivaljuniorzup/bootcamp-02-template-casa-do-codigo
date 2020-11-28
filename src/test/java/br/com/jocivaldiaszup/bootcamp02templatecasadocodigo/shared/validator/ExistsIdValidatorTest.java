package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.validator;

import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.author.Author;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.validation.ExistsId;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.validation.UniqueField;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.validation.validator.ExistsIdValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.Payload;
import java.lang.annotation.Annotation;
import java.util.List;

public class ExistsIdValidatorTest {

    private EntityManager entityManager = Mockito.mock(EntityManager.class);
    private TypedQuery<Integer> query = Mockito.mock(TypedQuery.class);

    ExistsId existsId = new ExistsId(){
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
            return "id";
        };

        public Class<?> domainClass(){
            return Author.class;
        }
    };

    @Test
    public void givenValidId_whenIsValid_thenTrue() {
        ExistsIdValidator existsIdValidator = new ExistsIdValidator(entityManager);
        existsIdValidator.initialize(existsId);

        List<Integer> emptyList = List.of(1);

        Mockito.when(entityManager.createQuery("select 1 from "+Author.class.getName()+" where id=:value")).thenReturn(query);
        Mockito.when(query.setParameter("value", 1l)).thenReturn(query);
        Mockito.when(query.getResultList()).thenReturn(emptyList);

        Assertions.assertTrue(existsIdValidator.isValid(1l, null));
    }

    @Test
    public void givenInvalidId_whenIsValid_thenFalse() {
        ExistsIdValidator existsIdValidator = new ExistsIdValidator(entityManager);
        existsIdValidator.initialize(existsId);

        List<Integer> emptyList = List.of();

        Mockito.when(entityManager.createQuery("select 1 from "+Author.class.getName()+" where id=:value")).thenReturn(query);
        Mockito.when(query.setParameter("value", 1l)).thenReturn(query);
        Mockito.when(query.getResultList()).thenReturn(emptyList);

        Assertions.assertFalse(existsIdValidator.isValid(1l, null));
    }

    @Test
    public void givenInvalidListSize_whenIsValid_thenThrowException() {
        ExistsIdValidator existsIdValidator = new ExistsIdValidator(entityManager);
        existsIdValidator.initialize(existsId);

        List<Integer> emptyList = List.of(1,2);

        Mockito.when(entityManager.createQuery("select 1 from "+Author.class.getName()+" where id=:value")).thenReturn(query);
        Mockito.when(query.setParameter("value", 1l)).thenReturn(query);
        Mockito.when(query.getResultList()).thenReturn(emptyList);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            existsIdValidator.isValid(1l, null);
        });
    }

}
