package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.purchase;

import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.book.Book;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.validation.ExistsId;

import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class NewPurchaseItemRequest {

    @NotNull
    @ManyToOne(optional = false)
    @ExistsId(fieldName = "id", domainClass = Book.class, message = "The book sent is not registered")
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
