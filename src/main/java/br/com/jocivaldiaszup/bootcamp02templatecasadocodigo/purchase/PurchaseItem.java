package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.purchase;

import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.book.Book;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class PurchaseItem {

    @NotNull
    @ManyToOne(optional = false)
    private Book book;

    @NotNull
    @Min(value = 1)
    private Integer quantity;

    @NotNull
    @DecimalMin(value = "0.00", inclusive = false)
    private BigDecimal value;

    @Deprecated
    public PurchaseItem() {
    }

    public PurchaseItem(@NotNull Book book,
                        @NotNull @Min(value = 1) Integer quantity,
                        @NotNull @DecimalMin(value = "0.00", inclusive = false) BigDecimal value) {

        Assert.notNull(book, "Book cant be null");
        Assert.notNull(quantity, "Quantity cant be null");
        Assert.isTrue(quantity >= 1, "Quantity must be greater or equal than 1");
        Assert.isTrue(value.compareTo(BigDecimal.ZERO) == 1, "Value should be greater than 0");

        this.book = book;
        this.quantity = quantity;
        this.value = value;
    }

    public Book getBook() {
        return book;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getValue() {
        return value;
    }

    public BigDecimal getTotalValue(){
        return value.multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public String toString() {
        return "PurchaseItem{" +
                "book=" + book +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseItem that = (PurchaseItem) o;
        return book.equals(that.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book);
    }
}
