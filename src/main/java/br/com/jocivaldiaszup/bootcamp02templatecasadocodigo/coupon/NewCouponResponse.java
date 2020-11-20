package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.coupon;

import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.validation.UniqueField;

import javax.persistence.Column;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

public class NewCouponResponse {

    @NotNull
    private Long id;

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
    public NewCouponResponse() {
    }

    public NewCouponResponse(@NotNull Long id,
                             @NotBlank String code,
                             @NotNull @Positive BigDecimal discountPercentage,
                             @Future LocalDate expirationDate) {
        this.id = id;
        this.code = code;
        this.discountPercentage = discountPercentage;
        this.expirationDate = expirationDate;
    }

    public static NewCouponResponse fromModel(Coupon coupon) {
        return new NewCouponResponse(coupon.getId(),
                coupon.getCode(),
                coupon.getDiscountPercentage(),
                coupon.getExpirationDate());
    }

    public Long getId() {
        return id;
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
