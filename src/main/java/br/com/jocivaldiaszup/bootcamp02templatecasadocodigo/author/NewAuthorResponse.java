package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.author;

import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.validation.UniqueField;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class NewAuthorResponse implements Serializable {
    private static final long serialVersionUID = 7407226838318371910L;

    @NotNull(message = "{NotNull}")
    private Long id;

    @NotBlank(message = "{NotBlank}")
    @Email(message = "{Email}")
    @UniqueField(domainClass = Author.class, fieldName = "email", message = "{unique.email}")
    private String email;

    @NotBlank(message = "{NotBlank}")
    private String name;

    @NotBlank(message = "{NotBlank}")
    @Size(max = 400, message = "{size.description}")
    private String description;

    @Deprecated
    public NewAuthorResponse() {
    }

    public NewAuthorResponse(@NotNull Long id,
                             @NotBlank @Email String email,
                             @NotBlank String name,
                             @NotBlank @Length(max = 400) String description) {
        Assert.notNull(id, "Id can't be null.");
        Assert.hasText(name, "Name can't be blank.");
        Assert.hasText(email, "Email can't be blank.");
        Assert.hasText(description, "Description can't be blank.");
        Assert.isTrue(description.length() <= 400, "Description size can't be greater than 400 characters.");
        this.id = id;
        this.email = email;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public static NewAuthorResponse fromModel(Author author){
        return new NewAuthorResponse(
                author.getId(),
                author.getName(),
                author.getEmail(),
                author.getDescription());
    }
}
