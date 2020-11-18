package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.category;

import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.validation.UniqueField;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
public class Category implements Serializable {

    private static final long serialVersionUID = -3837327430161661390L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank(message = "{NotBlank}")
    private String name;

    @Deprecated
    public Category() {
    }

    public Category(@NotBlank String name) {
        Assert.hasText(name, "Name cant be blank");
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
