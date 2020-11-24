package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.book;

import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.author.Author;

public class AuthorBookDetailResponse {

    private String name;

    private String description;

    @Deprecated
    public AuthorBookDetailResponse() {
    }

    public AuthorBookDetailResponse(String name,
                                    String description) {

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
