package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.country;

public class NewCountryResponse {

    private Long id;

    private String name;

    @Deprecated
    public NewCountryResponse() {
    }

    public NewCountryResponse(Long id, String name) {
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
