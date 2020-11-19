package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.author;

import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.validation.UniqueField;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class NewAuthorRequest implements Serializable {
    private static final long serialVersionUID = 7407226838318371910L;

    @NotBlank
    @Email
    @UniqueField(domainClass = Author.class, fieldName = "email", message = "{unique.email}")
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    @Size(max = 400, message = "{size.description}")
    private String description;

    @Deprecated
    public NewAuthorRequest() {
    }

    // 1
    public NewAuthorRequest(@NotBlank @Email String email,
                            @NotBlank String name,
                            @NotBlank @Length(max = 400) String description) {
        Assert.hasText(name, "Name can't be blank.");
        Assert.hasText(email, "Email can't be blank.");
        Assert.hasText(description, "Description can't be blank.");
        Assert.isTrue(description.length() <= 400, "Description size can't be greater than 400 characters.");
        this.email = email;
        this.name = name;
        this.description = description;
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

    public static Author toModel(NewAuthorRequest newAuthorRequest){
        return new Author(newAuthorRequest.getName(),
                newAuthorRequest.getEmail(),
                newAuthorRequest.getDescription());
    }
}
