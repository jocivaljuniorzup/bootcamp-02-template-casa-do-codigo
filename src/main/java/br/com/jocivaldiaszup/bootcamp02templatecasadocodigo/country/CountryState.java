package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.country;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class CountryState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)//Deveria ser único por país.
    private String name;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "country_id")
    private Country country;

    @Deprecated
    public CountryState() {
    }

    public CountryState(@NotBlank String name, @NotNull Country country) {
        this.name = name;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }


}
