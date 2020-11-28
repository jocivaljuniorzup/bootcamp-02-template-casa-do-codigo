package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.purchase;

import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.author.Author;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.book.Book;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.book.BookBuilder;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.category.Category;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.country.Country;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.country.CountryState;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.coupon.Coupon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;

public class NewPurchaseRequestTest {

    EntityManager entityManager = Mockito.mock(EntityManager.class);

    NewPurchaseItemRequest newPurchaseItemRequest = new NewPurchaseItemRequest(1l, 1);

    NewPurchaseDetailRequest newPurchaseDetailRequest =
            new NewPurchaseDetailRequest(BigDecimal.valueOf(500), new HashSet<>());

    NewPurchaseRequest newPurchaseRequest = new NewPurchaseRequest(
            "email@email.com",
            "firstname",
            "lastname",
            "00000000",
            "address",
            "complement",
            "city",
            1l,
            "123456798",
            "123456789",
            newPurchaseDetailRequest
    );

    Country country = new Country("Brazil");
    CountryState countryState = new CountryState("Minas Gerais", country);
    Coupon coupon = new Coupon("1234", BigDecimal.TEN, LocalDate.now().plusDays(1l));
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

    TypedQuery<Coupon> query = Mockito.mock(TypedQuery.class);


    @Test
    void givenValidNewPurchaseRequestWithoutCoupon_whenToModel_thenSuccess() {
        Mockito.when(entityManager.find(Country.class, 1l)).thenReturn(country);
        Mockito.when(entityManager.find(CountryState.class, 1l)).thenReturn(countryState);
        Mockito.when(entityManager.find(Book.class, 1l)).thenReturn(book);

        newPurchaseDetailRequest.getNewOrderItemsRequests().add(newPurchaseItemRequest);
        newPurchaseRequest.setCountryStateId(1l);
        newPurchaseRequest.setCouponCode(null);

        Assertions.assertNotNull(NewPurchaseRequest.toModel(newPurchaseRequest, entityManager));
    }

    @Test
    void givenValidNewPurchaseRequestWithCoupon_whenToModel_thenSuccess() {
        Mockito.when(entityManager.find(Country.class, 1l)).thenReturn(country);
        Mockito.when(entityManager.find(CountryState.class, 1l)).thenReturn(countryState);
        Mockito.when(entityManager.find(Book.class, 1l)).thenReturn(book);

        newPurchaseDetailRequest.getNewOrderItemsRequests().add(newPurchaseItemRequest);
        newPurchaseRequest.setCountryStateId(1l);
        newPurchaseRequest.setCouponCode("1234");

        //Mock string query
        Mockito.when(entityManager.createQuery("select t from Coupon t where t.code = :value", Coupon.class)).thenReturn(query);
        Mockito.when(query.setParameter("value", "1234")).thenReturn(query);
        Mockito.when(query.getSingleResult()).thenReturn(coupon);

        Assertions.assertNotNull(NewPurchaseRequest.toModel(newPurchaseRequest, entityManager));
    }

    @Test
    void givenInvalidCountryNewPurchaseRequest_whenToModel_thenThrowException() {
        Mockito.when(entityManager.find(Country.class, 1l)).thenReturn(null);
        Mockito.when(entityManager.find(CountryState.class, 1l)).thenReturn(countryState);
        Mockito.when(entityManager.find(Book.class, 1l)).thenReturn(book);

        newPurchaseDetailRequest.getNewOrderItemsRequests().add(newPurchaseItemRequest);
        newPurchaseRequest.setCountryStateId(1l);
        newPurchaseRequest.setCouponCode(null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            NewPurchaseRequest.toModel(newPurchaseRequest, entityManager);
        });
    }

}
