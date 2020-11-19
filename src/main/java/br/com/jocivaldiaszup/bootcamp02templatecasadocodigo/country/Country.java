package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.country;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
public class Country implements Serializable {
    private static final long serialVersionUID = 2844801118166297401L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank
    private String name;

    @Deprecated
    public Country() {
    }

    public Country(@NotBlank String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
