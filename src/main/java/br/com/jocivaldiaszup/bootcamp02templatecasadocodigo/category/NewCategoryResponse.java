package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.category;

import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.validation.UniqueField;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NewCategoryResponse {

    @NotNull
    private Long id;

    @NotBlank
    @UniqueField(fieldName = "name", domainClass = Category.class, message = "{unique.category.name}")
    private String name;

    @Deprecated
    public NewCategoryResponse() {
    }

    public NewCategoryResponse(@NotNull Long id,
            @NotBlank String name) {

        Assert.isTrue(id != null, "Id cant be null");
        Assert.hasText(name, "Name cant be blank");

        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static NewCategoryResponse fromModel(Category category) {
        return new NewCategoryResponse(category.getId(), category.getName());
    }
}
