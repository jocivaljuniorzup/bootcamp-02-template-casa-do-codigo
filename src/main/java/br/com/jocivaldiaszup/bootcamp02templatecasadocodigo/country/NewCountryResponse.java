package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.country;

import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.validation.UniqueField;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NewCountryResponse {

    @NotNull
    private Long id;

    @NotBlank
    @UniqueField(domainClass = Country.class, fieldName = "name", message = "Country name must be unique")
    private String name;

    @Deprecated
    public NewCountryResponse() {
    }

    public NewCountryResponse(@NotNull Long id, @NotBlank String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static NewCountryResponse fromModel(Country country) {
        return new NewCountryResponse(country.getId(), country.getName());
    }


}
