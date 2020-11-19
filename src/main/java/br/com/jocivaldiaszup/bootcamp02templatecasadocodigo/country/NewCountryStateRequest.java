package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.country;

import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.validation.UniqueField;
import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NewCountryStateRequest {

    @NotBlank
    @Column(unique = true)//Deveria ser único por país.
    @UniqueField(domainClass = CountryState.class, fieldName = "name", message = "State name must be unique")
    private String name;

    @NotNull
    private Long countryId;

    public static CountryState toModel(NewCountryStateRequest newCountryStateRequest, EntityManager entityManager) {
        Country country = entityManager.find(Country.class, newCountryStateRequest.getCountryId());

        Assert.notNull(country, "The country sent is not registered. Id: " + newCountryStateRequest.getCountryId());

        return new CountryState(newCountryStateRequest.getName(), country);
    }

    public String getName() {
        return name;
    }

    public Long getCountryId() {
        return countryId;
    }
}
