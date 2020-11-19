package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.book;

import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.validation.UniqueField;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ListBookResponse {

    @NotNull
    private Long id;

    @NotBlank
    @UniqueField(domainClass = Book.class, fieldName = "title",message = "{unique.book.title}")
    private String title;

    @Deprecated
    public ListBookResponse() {
    }

    public ListBookResponse(@NotNull Long id,
                            @NotBlank String title) {

        Assert.notNull(id, "Book id cant be null");
        Assert.hasText(title, "Title cant be blank");

        this.id = id;
        this.title = title;

    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public static ListBookResponse fromModel(Book book) {
        return new ListBookResponse(book.getId(), book.getTitle());
    }
}
