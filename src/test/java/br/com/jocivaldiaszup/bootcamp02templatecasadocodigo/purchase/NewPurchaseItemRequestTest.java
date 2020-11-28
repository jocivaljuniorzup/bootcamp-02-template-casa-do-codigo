package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.purchase;

import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.author.Author;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.book.Book;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.book.BookBuilder;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.category.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;

public class NewPurchaseItemRequestTest {

    EntityManager entityManager = Mockito.mock(EntityManager.class);

    NewPurchaseItemRequest newPurchaseItemRequest = new NewPurchaseItemRequest(1l, 10);

    Book book = new BookBuilder().setBookAbstract("abstract")
            .setAuthor(new Author("name", "email@email.com", "descripton"))
            .setCategory(new Category("name"))
            .setIsbn("123")
            .setPages(1000)
            .setPublicationDate(LocalDate.now().plusDays(1l))
            .setSummary("summary")
            .setTitle("title")
            .setValue(BigDecimal.valueOf(500))
            .build();

    @Test
    public void givenValidBook_whenToModel_thenSuccess(){
        Mockito.when(entityManager.find(Book.class, 1l)).thenReturn(book);

        PurchaseItem purchaseItem = NewPurchaseItemRequest.toModel(newPurchaseItemRequest, entityManager);

        Assertions.assertNotNull(purchaseItem);
    }


}
