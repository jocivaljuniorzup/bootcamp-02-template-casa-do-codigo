package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.country;

import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.validation.UniqueField;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NewCountryStateResponse {

    private Long id;

    @NotBlank
    @Column(unique = true)//Deveria ser único por país.
    @UniqueField(domainClass = CountryState.class, fieldName = "name", message = "State name must be unique")
    private String name;

    @NotNull
    private Long countryId;

    public NewCountryStateResponse() {
    }

    public NewCountryStateResponse(Long id, @NotBlank String name, @NotNull Long countryId) {
        this.id = id;
        this.name = name;
        this.countryId = countryId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getCountryId() {
        return countryId;
    }

    public static NewCountryStateResponse fromModel(CountryState countryState) {
        return new NewCountryStateResponse(countryState.getId(), countryState.getName(), countryState.getCountry().getId());
    }
}
