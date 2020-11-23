package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.purchase;

import org.springframework.util.Assert;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class NewPurchaseDetailRequest {

    @NotNull
    @DecimalMin(value = "0.00", inclusive = false)
    private BigDecimal totalValue;

    @NotNull
    @Size(min = 1)
    @Valid
    private Set<NewPurchaseItemRequest> items = new HashSet<>();

    public NewPurchaseDetailRequest(@NotNull @DecimalMin(value = "0.00", inclusive = false) BigDecimal totalValue,
                                    @NotNull @Size(min = 1) Set<NewPurchaseItemRequest> items) {

        Assert.isTrue(totalValue.compareTo(BigDecimal.ZERO) == 1, "Total value should be greater than 0");
        Assert.isTrue(items.size() >= 1, "You must add at least one item to the cart");

        this.totalValue = totalValue;
        this.items = items;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public Set<NewPurchaseItemRequest> getNewOrderItemsRequests() {
        return items;
    }

    @Override
    public String toString() {
        return "NewPurchaseDetailRequest{" +
                "totalValue=" + totalValue +
                ", newPurchaseItemRequestSet=" + items +
                '}';
    }
}
