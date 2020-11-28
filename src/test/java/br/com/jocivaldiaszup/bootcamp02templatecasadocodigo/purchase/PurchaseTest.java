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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class PurchaseTest {

    Country country = new Country("Brazil");
    CountryState countryState = new CountryState("Minas Gerais", country);
    Coupon coupon = new Coupon("code", BigDecimal.TEN, LocalDate.now().plusDays(1l));

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

    Set<PurchaseItem> purchaseItemSet = new HashSet<PurchaseItem>(Set.of(new PurchaseItem(book, 1, BigDecimal.TEN)));

    PurchaseBuilder purchaseBuilder = new PurchaseBuilder()
            .setFirstName("firstname")
            .setLastName("lastname")
            .setEmail("email@email.com.br")
            .setCpfCnpj("123456789")
            .setTelephoneNumber("123465789")
            .setAddress("Address")
            .setComplement("complement")
            .setZipCode("123456789")
            .setCity("City")
            .setCountry(country)
            .setCountryState(countryState)
            .setStatus(PurchaseStatus.OPENED)
            .setTotalValue(BigDecimal.valueOf(100))
            .setPurchaseItemSet(purchaseItemSet);

    @Test
    void givenValidCoupon_whenApplyCoupon_thenAdjustValue() {
        Purchase purchase = purchaseBuilder
                .setCoupon(coupon)
                .build();

        BigDecimal originalValue = purchase.getTotalValue();
        purchase.applyCoupon();
        BigDecimal finalValue = purchase.getTotalValue();

        // 10% of discount
        originalValue = originalValue.multiply(BigDecimal.valueOf(0.9));

        Assertions.assertEquals(originalValue, finalValue);
        Assertions.assertEquals(purchase.getStatus(), PurchaseStatus.COUPONAPPLIED);
    }

    @Test
    void name() {
    }
}
