package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.coupon;

import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.validation.UniqueField;

import javax.persistence.Column;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

public class NewCouponRequest {

    @NotBlank
    @UniqueField(fieldName = "code", domainClass = Coupon.class)
    @Column(unique = true)
    private String code;

    @NotNull
    @Positive
    private BigDecimal discountPercentage;

    @Future
    private LocalDate expirationDate;

    @Deprecated
    public NewCouponRequest() {
    }

    public NewCouponRequest(@NotBlank String code,
                            @NotNull @Positive BigDecimal discountPercentage,
                            @Future LocalDate expirationDate) {

        this.code = code;
        this.discountPercentage = discountPercentage;
        this.expirationDate = expirationDate;
    }

    public static Coupon toModel(NewCouponRequest newCouponRequest) {
        return new Coupon(newCouponRequest.getCode(),
                newCouponRequest.getDiscountPercentage(),
                newCouponRequest.getExpirationDate());
    }

    public String getCode() {
        return code;
    }

    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }
}
