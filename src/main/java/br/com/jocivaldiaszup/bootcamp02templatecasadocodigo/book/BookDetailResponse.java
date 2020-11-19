package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.book;

import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.validation.UniqueField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.util.Assert;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class BookDetailResponse {

    @NotNull
    private Long id;

    @NotBlank
    @UniqueField(domainClass = Book.class, fieldName = "title",message = "{unique.book.title}")
    private String title;

    @NotBlank
    @Size(max = 500, message = "{size.bookAbstract}")
    private String bookAbstract;

    private String summary;

    @DecimalMin(value = "20.00", message = "{min.book.value}")
    private BigDecimal value;

    @Min(value = 100, message = "{min.book.pages}")
    private Integer pages;

    @NotBlank
    @UniqueField(fieldName = "isbn", domainClass = Book.class, message = "{unique.book.isbn}")
    private String isbn;

    @Future
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate publicationDate;

    @NotNull
    private AuthorBookDetailResponse authorBookDetailResponse;

    @Deprecated
    public BookDetailResponse() {
    }

    public BookDetailResponse(@NotNull Long id,
                              @NotBlank String title,
                              @NotBlank @Size(max = 500, message = "{size.bookAbstract}") String bookAbstract,
                              String summary,
                              @DecimalMin(value = "20.00", message = "{min.book.value}") BigDecimal value,
                              @Min(value = 100, message = "{min.book.pages}") Integer pages,
                              @NotBlank String isbn,
                              @Future LocalDate publicationDate,
                              @NotNull @Valid AuthorBookDetailResponse authorBookDetailResponse) {

        Assert.hasText(title, "Title cant be blank");
        Assert.hasText(bookAbstract, "Book abstract cant be blank");
        Assert.isTrue(bookAbstract.length() <= 500, "Book abstract size can't be greater than 400 characters.");
        Assert.isTrue(value.compareTo(BigDecimal.valueOf(20.00)) == 1, "Book value can't be lower than 20.00" );
        Assert.isTrue(pages >= 100, "Book pages can't be lower than 100 pages.");
        Assert.hasText(isbn, "Book isbn cant be blank");

        this.id = id;
        this.title = title;
        this.bookAbstract = bookAbstract;
        this.summary = summary;
        this.value = value;
        this.pages = pages;
        this.isbn = isbn;
        this.publicationDate = publicationDate;
        this.authorBookDetailResponse = authorBookDetailResponse;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBookAbstract() {
        return bookAbstract;
    }

    public String getSummary() {
        return summary;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Integer getPages() {
        return pages;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public AuthorBookDetailResponse getAuthorBookDetailResponse() {
        return authorBookDetailResponse;
    }

    public static BookDetailResponse fromModel(Book book) {

        return new BookDetailResponse(book.getId(),
                book.getTitle(),
                book.getBookAbstract(),
                book.getSummary(),
                book.getValue(),
                book.getPages(),
                book.getIsbn(),
                book.getPublicationDate(),
                AuthorBookDetailResponse.fromModel(book.getAuthor())
                );
    }

}
