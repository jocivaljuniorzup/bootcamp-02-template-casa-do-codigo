package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.country;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.persistence.EntityManager;

public class NewCountryStateRequestTest {

    EntityManager entityManager = Mockito.mock(EntityManager.class);
    NewCountryStateRequest newCountryStateRequest = new NewCountryStateRequest("Name", 1l);

    @Test
    public void givenValidCountry_whenToModel_thenSuccess(){
        Mockito.when(entityManager.find(Country.class, 1l)).thenReturn(new Country("Brasil"));

        Assertions.assertNotNull(NewCountryStateRequest.toModel(newCountryStateRequest,entityManager));
    }

    @Test
    public void givenInvalidCountry_whenToModel_thenThrowException(){
        Mockito.when(entityManager.find(Country.class, 1l)).thenReturn(null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            NewCountryStateRequest.toModel(newCountryStateRequest,entityManager);
        });
    }

}
