package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.country;

import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.validation.ExistsId;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.validation.UniqueField;

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
    @ExistsId(fieldName = "id", domainClass = Country.class, message = "The country sent is not registered.")
    private Long countryId;

    public NewCountryStateRequest(@NotBlank String name, @NotNull Long countryId) {
        this.name = name;
        this.countryId = countryId;
    }

    public static CountryState toModel(NewCountryStateRequest newCountryStateRequest, EntityManager entityManager) {
        Country country = entityManager.find(Country.class, newCountryStateRequest.getCountryId());

        return new CountryState(newCountryStateRequest.getName(), country);
    }

    public String getName() {
        return name;
    }

    public Long getCountryId() {
        return countryId;
    }
}
