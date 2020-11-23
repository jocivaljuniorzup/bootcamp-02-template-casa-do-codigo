package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.purchase;

import java.math.BigDecimal;

public class NewPurchaseItemResponse {

    private String bookTitle;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal totalPrice;

    public NewPurchaseItemResponse(String bookTitle,
                                   BigDecimal price,
                                   Integer quantity,
                                   BigDecimal totalPrice) {
        this.bookTitle = bookTitle;
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public static NewPurchaseItemResponse toModel(PurchaseItem purchaseItem) {
        return new NewPurchaseItemResponse(
                purchaseItem.getBook().getTitle(),
                purchaseItem.getValue(),
                purchaseItem.getQuantity(),
                purchaseItem.getTotalValue()
        );
    }
}
