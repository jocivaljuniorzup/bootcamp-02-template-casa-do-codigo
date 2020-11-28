package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.book;

import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.author.Author;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.category.Category;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Book implements Serializable {

    private static final long serialVersionUID = 4729295236757058367L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String title;

    @NotBlank
    @Size(max = 500, message = "{size.bookAbstract}")
    @Column(length = 500)
    private String bookAbstract;

    @Column(columnDefinition = "TEXT")
    private String summary;

    @DecimalMin(value = "20.00", message = "{min.book.value}")
    private BigDecimal value;

    @Min(value = 100, message = "{min.book.pages}")
    private Integer pages;

    @NotBlank
    @Column(unique = true)
    private String isbn;

    @Future
    private LocalDate publicationDate;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @Deprecated
    public Book() {
    }

    public Book(BookBuilder bookBuilder){
        Assert.hasText(bookBuilder.getTitle(), "Title cant be blank");
        Assert.hasText(bookBuilder.getBookAbstract(), "Book abstract cant be blank");
        Assert.isTrue(bookBuilder.getBookAbstract().length() <= 500, "Book abstract size can't be greater than 400 characters.");
        Assert.isTrue(bookBuilder.getValue().compareTo(BigDecimal.valueOf(20.00)) >= 0, "Book value can't be lower than 20.00" );
        Assert.isTrue(bookBuilder.getPages() >= 100, "Book pages can't be lower than 100 pages.");
        Assert.hasText(bookBuilder.getIsbn(), "Book isbn cant be blank");
        Assert.notNull(bookBuilder.getCategory(), "Book category cant be null");
        Assert.notNull(bookBuilder.getAuthor(), "Book author cant be null");

        this.title = bookBuilder.getTitle();
        this.bookAbstract = bookBuilder.getBookAbstract();
        this.summary = bookBuilder.getSummary();
        this.value = bookBuilder.getValue();
        this.pages = bookBuilder.getPages();
        this.isbn = bookBuilder.getIsbn();
        this.publicationDate = bookBuilder.getPublicationDate();
        this.category = bookBuilder.getCategory();
        this.author = bookBuilder.getAuthor();
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

    public Category getCategory() {
        return category;
    }

    public Author getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", bookAbstract='" + bookAbstract + '\'' +
                ", summary='" + summary + '\'' +
                ", value=" + value +
                ", pages=" + pages +
                ", isbn='" + isbn + '\'' +
                ", publicationDate=" + publicationDate +
                ", category=" + category +
                ", author=" + author +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id.equals(book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
