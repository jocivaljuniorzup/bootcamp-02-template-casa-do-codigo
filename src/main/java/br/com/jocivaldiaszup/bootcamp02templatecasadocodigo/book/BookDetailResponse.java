package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.book;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BookDetailResponse {

    private Long id;

    private String title;

    private String bookAbstract;

    private String summary;

    private BigDecimal value;

    private Integer pages;

    private String isbn;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate publicationDate;

    private AuthorBookDetailResponse authorBookDetailResponse;

    @Deprecated
    public BookDetailResponse() {
    }

    public BookDetailResponse(Long id,
                              String title,
                              String bookAbstract,
                              String summary,
                              BigDecimal value,
                              Integer pages,
                              String isbn,
                              LocalDate publicationDate,
                              AuthorBookDetailResponse authorBookDetailResponse) {

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
