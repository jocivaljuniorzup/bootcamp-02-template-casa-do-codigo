package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.book;

import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.author.Author;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AuthorBookDetailResponse {

    @NotBlank
    private String name;

    @NotBlank
    @Size(max = 400, message = "{size.description}")
    private String description;

    @Deprecated
    public AuthorBookDetailResponse() {
    }

    public AuthorBookDetailResponse(@NotBlank String name,
                                    @NotBlank @Size(max = 400, message = "{size.description}") String description) {

        Assert.hasText(name, "Author name cant be blank");
        Assert.isTrue(description.length() <= 400, "Author description size can't be greater than 400 characters.");

        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public static AuthorBookDetailResponse fromModel(Author author){
        return new AuthorBookDetailResponse(author.getName(), author.getDescription());
    }

}
