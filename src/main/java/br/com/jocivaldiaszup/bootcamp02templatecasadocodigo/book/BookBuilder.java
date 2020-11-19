package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.book;

import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.author.Author;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.category.Category;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class BookBuilder{
    @NotBlank
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
    private String isbn;

    @Future
    private LocalDate publicationDate;

    @NotNull
    private Category category;

    @NotNull
    private Author author;

    public BookBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public BookBuilder setBookAbstract(String bookAbstract) {
        this.bookAbstract = bookAbstract;
        return this;
    }

    public BookBuilder setSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public BookBuilder setValue(BigDecimal value) {
        this.value = value;
        return this;
    }

    public BookBuilder setPages(Integer pages) {
        this.pages = pages;
        return this;
    }

    public BookBuilder setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public BookBuilder setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
        return this;
    }

    public BookBuilder setCategory(Category category) {
        this.category = category;
        return this;
    }

    public BookBuilder setAuthor(Author author) {
        this.author = author;
        return this;
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

    public Category getCategory() {
        return category;
    }

    public Author getAuthor() {
        return author;
    }

    public Book build(){
        return new Book(this);
    }
}
