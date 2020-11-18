package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.category;

import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.validation.UniqueField;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;

public class NewCategoryResponse {

    @NotBlank(message = "{NotBlank}")
    @UniqueField(fieldName = "name", domainClass = Category.class, message = "{unique.category.name}")
    String name;

    public NewCategoryResponse() {
    }

    public NewCategoryResponse(@NotBlank String name) {
        Assert.hasText(name, "Name cant be blank");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static NewCategoryResponse fromModel(Category category) {
        return new NewCategoryResponse(category.getName());
    }
}
