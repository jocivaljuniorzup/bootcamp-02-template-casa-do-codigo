package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.coupon;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String code;

    @NotNull
    @Positive()
    private BigDecimal discountPercentage;

    @Future
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate expirationDate;

    @Deprecated
    public Coupon() {
    }

    public Coupon(@NotBlank String code,
                  @NotNull @Positive BigDecimal discountPercentage,
                  @Future LocalDate expirationDate) {

        Assert.hasText(code, "Code cant be blank");
        Assert.notNull(discountPercentage, "Discount percentage cant bet null");
        Assert.isTrue(discountPercentage.compareTo(BigDecimal.ZERO) == 1, "Discount percentage value should be greater than zero");
        Assert.isTrue(LocalDate.now().isBefore(expirationDate), "Expiration date should be a future date");

        this.code = code;
        this.discountPercentage = discountPercentage;
        this.expirationDate = expirationDate;
    }

    public boolean isValid() {
        return LocalDate.now().isBefore(expirationDate);
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

    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", discountPercentage=" + discountPercentage +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
