package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.author;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Author implements Serializable {

    private static final long serialVersionUID = -4793648600100588139L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Column(unique = true)
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(max = 400, message = "{size.description}")
    private String description;

    private LocalDateTime createdAt = LocalDateTime.now();

    @Deprecated
    public Author() {
    }

    public Author(@NotBlank String name, @Email String email, @NotBlank @Size(max = 400) String description) {
        Assert.hasText(name, "Name can't be blank.");
        Assert.hasText(email, "Email can't be blank.");
        Assert.hasText(description, "Description can't be blank.");
        Assert.isTrue(description.length() <= 400, "Description size can't be greater than 400 characters.");
        this.name = name;
        this.email = email;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
