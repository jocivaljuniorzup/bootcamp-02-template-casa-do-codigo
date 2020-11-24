package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.purchase;

import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.book.Book;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class NewPurchaseItemRequest {

    @NotNull
    @ManyToOne(optional = false)
    private Long bookId;

    @NotNull
    @Min(value = 1)
    private Integer quantity;

    @Deprecated
    public NewPurchaseItemRequest() {
    }

    public NewPurchaseItemRequest(@NotNull Long bookId,
                                  @NotNull @Min(value = 1) Integer quantity) {

        this.bookId = bookId;
        this.quantity = quantity;
    }

    public Long getBookId() {
        return bookId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public static PurchaseItem toModel(NewPurchaseItemRequest newPurchaseItemRequest, EntityManager entityManager) {
        Book book = entityManager.find(Book.class, newPurchaseItemRequest.getBookId());

        Assert.notNull(book, "The book sent is not registered. Id: " + newPurchaseItemRequest.getBookId());

        return new PurchaseItem(book, newPurchaseItemRequest.getQuantity(), book.getValue());
    }

    @Override
    public String toString() {
        return "NewPurchaseItemRequest{" +
                "bookId=" + bookId +
                ", quantity=" + quantity +
                '}';
    }
}
