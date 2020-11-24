package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.author;

import java.io.Serializable;
import java.time.LocalDateTime;

public class NewAuthorResponse implements Serializable {
    private static final long serialVersionUID = 7407226838318371910L;

    private Long id;

    private String email;

    private String name;

    private String description;

    private LocalDateTime createdAt;

    @Deprecated
    public NewAuthorResponse() {
    }

    public NewAuthorResponse(Long id,
                             String email,
                             String name,
                             String description,
                             LocalDateTime createdAt) {

        this.id = id;
        this.email = email;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public static NewAuthorResponse fromModel(Author author){
        return new NewAuthorResponse(
                author.getId(),
                author.getName(),
                author.getEmail(),
                author.getDescription(),
                author.getCreatedAt());
    }
}
