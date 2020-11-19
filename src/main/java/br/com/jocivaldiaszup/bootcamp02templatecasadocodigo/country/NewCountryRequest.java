package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.country;

import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.validation.UniqueField;

import javax.validation.constraints.NotBlank;

public class NewCountryRequest {

    @NotBlank
    @UniqueField(domainClass = Country.class, fieldName = "name", message = "Country name must be unique")
    private String name;

    @Deprecated
    public NewCountryRequest() {
    }

    public NewCountryRequest(@NotBlank String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Country toModel(NewCountryRequest newCountryRequest) {
        return new Country(newCountryRequest.getName());
    }
    
}
