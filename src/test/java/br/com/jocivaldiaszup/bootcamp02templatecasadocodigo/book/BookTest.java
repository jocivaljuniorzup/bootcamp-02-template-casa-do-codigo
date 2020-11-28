package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.book;

import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.author.Author;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.category.Category;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.util.TestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BookTest {

    Author author = new Author("Name", "email@email.com", "description");
    Category category = new Category("Example");

    @Test
    public void givenValidBook_whenInstantiate_thenSuccess(){
        Book book = new BookBuilder()
                .setTitle("Example title")
                .setBookAbstract(TestUtil.generateString(500, "example"))
                .setSummary("Example summary")
                .setValue(BigDecimal.valueOf(20))
                .setPages(100)
                .setIsbn("123456789")
                .setPublicationDate(LocalDate.now().plusDays(1))
                .setAuthor(author)
                .setCategory(category)
                .build();

        Assertions.assertNotNull(book);
    }

    @Test
    public void givenInvalidBookTitle_whenInstantiate_thenThrowException(){
        BookBuilder bookBuilder = new BookBuilder()
                .setTitle("")
                .setBookAbstract(TestUtil.generateString(500, "example"))
                .setSummary("Example summary")
                .setValue(BigDecimal.valueOf
                        (20))
                .setPages(100)
                .setIsbn("123456789")
                .setPublicationDate(LocalDate.now().plusDays(1))
                .setAuthor(author)
                .setCategory(category)
                .setPages(100);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            bookBuilder.build();
        });
    }

    @Test
    public void givenInvalidBookAbstract_whenInstantiate_thenThrowException(){
        BookBuilder bookBuilder = new BookBuilder()
                .setTitle("Example title")
                .setBookAbstract(TestUtil.generateString(500, "example"))
                .setSummary("Example summary")
                .setValue(BigDecimal.valueOf(20))
                .setPages(100)
                .setIsbn("123456789")
                .setPublicationDate(LocalDate.now().plusDays(1))
                .setAuthor(author)
                .setCategory(category);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            bookBuilder.setBookAbstract("").build();
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            bookBuilder.setBookAbstract(TestUtil.generateString(501, "example")).build();
        });
    }

    @Test
    public void givenInvalidBookValue_whenInstantiate_thenThrowException(){
        BookBuilder bookBuilder = new BookBuilder()
                .setTitle("Example title")
                .setBookAbstract(TestUtil.generateString(500, "example"))
                .setSummary("Example summary")
                .setValue(BigDecimal.valueOf(20))
                .setPages(100)
                .setIsbn("123456789")
                .setPublicationDate(LocalDate.now().plusDays(1))
                .setAuthor(author)
                .setCategory(category);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            bookBuilder.setValue(BigDecimal.valueOf(19)).build();
        });
    }

    @Test
    public void givenInvalidBookPages_whenInstantiate_thenThrowException(){
        BookBuilder bookBuilder = new BookBuilder()
                .setTitle("Example title")
                .setBookAbstract(TestUtil.generateString(500, "example"))
                .setSummary("Example summary")
                .setValue(BigDecimal.valueOf
                        (20))
                .setPages(100)
                .setIsbn("123456789")
                .setPublicationDate(LocalDate.now().plusDays(1))
                .setAuthor(author)
                .setCategory(category);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            bookBuilder.setPages(99).build();
        });
    }

    @Test
    public void givenInvalidBookIsbn_whenInstantiate_thenThrowException(){
        BookBuilder bookBuilder = new BookBuilder()
                .setTitle("Example title")
                .setBookAbstract(TestUtil.generateString(500, "example"))
                .setSummary("Example summary")
                .setValue(BigDecimal.valueOf
                        (20))
                .setPages(100)
                .setIsbn("")
                .setPublicationDate(LocalDate.now().plusDays(1))
                .setAuthor(author)
                .setCategory(category)
                .setPages(100);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            bookBuilder.build();
        });
    }

    @Test
    public void givenInvalidBookCategory_whenInstantiate_thenThrowException(){
        BookBuilder bookBuilder = new BookBuilder()
                .setTitle("Example title")
                .setBookAbstract(TestUtil.generateString(500, "example"))
                .setSummary("Example summary")
                .setValue(BigDecimal.valueOf
                        (20))
                .setPages(100)
                .setIsbn("123456")
                .setPublicationDate(LocalDate.now().plusDays(1))
                .setAuthor(author)
                .setCategory(null)
                .setPages(100);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            bookBuilder.build();
        });
    }

    @Test
    public void givenInvalidBookAuthor_whenInstantiate_thenThrowException(){
        BookBuilder bookBuilder = new BookBuilder()
                .setTitle("Example title")
                .setBookAbstract(TestUtil.generateString(500, "example"))
                .setSummary("Example summary")
                .setValue(BigDecimal.valueOf
                        (20))
                .setPages(100)
                .setIsbn("123456")
                .setPublicationDate(LocalDate.now().plusDays(1))
                .setAuthor(null)
                .setCategory(category)
                .setPages(100);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            bookBuilder.build();
        });
    }




}
