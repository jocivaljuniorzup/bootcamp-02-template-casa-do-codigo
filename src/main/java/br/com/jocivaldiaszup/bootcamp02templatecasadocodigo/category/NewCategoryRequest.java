package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.category;

import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.validation.UniqueField;

import javax.validation.constraints.NotBlank;

public class NewCategoryRequest {

    @NotBlank(message = "{NotBlank}")
    @UniqueField(fieldName = "name", domainClass = Category.class, message = "{unique.category.name}")
    String name;

    public NewCategoryRequest() {
    }

    public NewCategoryRequest(@NotBlank String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Category toModel(NewCategoryRequest newCategoryRequest) {
        return new Category(newCategoryRequest.getName());
    }
}
