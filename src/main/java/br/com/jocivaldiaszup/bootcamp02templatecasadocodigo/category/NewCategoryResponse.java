package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.category;

public class NewCategoryResponse {

    private Long id;

    private String name;

    @Deprecated
    public NewCategoryResponse() {
    }

    public NewCategoryResponse(Long id,
            String name) {

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
