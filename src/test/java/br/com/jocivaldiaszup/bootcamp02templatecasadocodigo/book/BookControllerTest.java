package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.book;

import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.author.Author;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.category.Category;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.coupon.Coupon;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.exception.ObjectNotFoundException;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.util.TestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookControllerTest {

    Author author = new Author("Name", "email@email.com", "description");
    Category category = new Category("Example");

    EntityManager entityManager = Mockito.mock(EntityManager.class);

    BookController bookController = new BookController(entityManager);

    TypedQuery<Book> query = Mockito.mock(TypedQuery.class);

    Book book = new BookBuilder()
            .setTitle("Example")
            .setBookAbstract(TestUtil.generateString(500, "example"))
            .setSummary("Example summary")
            .setValue(BigDecimal.valueOf(20))
            .setPages(100)
            .setIsbn("123456789")
            .setPublicationDate(LocalDate.now().plusDays(1))
            .setAuthor(author)
            .setCategory(category)
            .build();

    @Test
    void whenFindAllWithElements_returnBookResponseList() {
        List<Book> bookList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            bookList.add(book);
        }

        //Mock string query
        Mockito.when(entityManager.createQuery("select b from Book b", Book.class)).thenReturn(query);
        Mockito.when(query.getResultList()).thenReturn(bookList);


        ResponseEntity<List<ListBookResponse>> all = bookController.findAll();

        Assertions.assertEquals(all.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(all.getBody().size(), 3);
    }

    @Test
    void whenFindAllWithoutElements_returnBookResponseList() {
        //Mock string query
        Mockito.when(entityManager.createQuery("select b from Book b", Book.class)).thenReturn(query);
        Mockito.when(query.getResultList()).thenReturn(new ArrayList<>());

        ResponseEntity<List<ListBookResponse>> all = bookController.findAll();

        Assertions.assertEquals(all.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(all.getBody().size(), 0);
    }

    @Test
    void whenBookDetails_thenReturnBookDetailResponse() {
        Mockito.when(entityManager.find(Book.class, 1l)).thenReturn(book);

        ResponseEntity<?> responseEntity = bookController.bookDetails(1l);

        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assertions.assertNotNull(responseEntity.getBody());
    }

    @Test
    void givenBookNotExists_whenBookDetails_thenThrowException() {
        Mockito.when(entityManager.find(Book.class, 1l)).thenReturn(null);

        Assertions.assertThrows(ObjectNotFoundException.class, () -> {
            ResponseEntity<?> responseEntity = bookController.bookDetails(1l);
        });

    }
}
