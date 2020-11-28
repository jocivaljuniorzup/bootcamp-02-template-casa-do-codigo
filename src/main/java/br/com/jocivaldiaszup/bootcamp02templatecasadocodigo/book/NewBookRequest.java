package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.book;

import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.author.Author;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.category.Category;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.validation.ExistsId;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.validation.UniqueField;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class NewBookRequest {

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
    @ExistsId(domainClass = Category.class, fieldName = "id", message = "Category not exists")
    private Long categoryId;

    @NotNull
    @ExistsId(domainClass = Author.class, fieldName = "id", message = "Author not exists")
    private Long authorId;

    @Deprecated
    public NewBookRequest() {
    }

    public NewBookRequest(@NotBlank String title,
                          @NotBlank @Size(max = 500) String bookAbstract,
                          String summary,
                          @DecimalMin(value = "20.00") BigDecimal value,
                          @Min(value = 100) Integer pages,
                          @NotBlank String isbn,
                          @Future LocalDate publicationDate,
                          @NotNull Long categoryId,
                          @NotNull Long authorId) {


        this.title = title;
        this.bookAbstract = bookAbstract;
        this.summary = summary;
        this.value = value;
        this.pages = pages;
        this.isbn = isbn;
        this.publicationDate = publicationDate;
        this.categoryId = categoryId;
        this.authorId = authorId;
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

    public Long getCategoryId() {
        return categoryId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public static Book toModel(NewBookRequest newBookRequest, EntityManager entityManager) {
        Category category = entityManager.find(Category.class, newBookRequest.getCategoryId());
        Author author = entityManager.find(Author.class, newBookRequest.getAuthorId());

        return new BookBuilder()
                .setTitle(newBookRequest.getTitle())
                .setBookAbstract(newBookRequest.getBookAbstract())
                .setSummary(newBookRequest.getSummary())
                .setValue(newBookRequest.getValue())
                .setPages(newBookRequest.getPages())
                .setIsbn(newBookRequest.getIsbn())
                .setPublicationDate(newBookRequest.getPublicationDate())
                .setCategory(category)
                .setAuthor(author)
                .build();
    }

}
