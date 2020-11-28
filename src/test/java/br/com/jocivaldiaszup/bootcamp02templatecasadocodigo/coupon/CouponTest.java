package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.coupon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CouponTest {

    @Test
    public void givenValidCoupon_whenInstantiate_thenSuccess(){
        Coupon coupon = new Coupon("code", BigDecimal.valueOf(20.5), LocalDate.now().plusDays(1l));
        Assertions.assertNotNull(coupon);
    }

    @Test
    public void givenInvalidCouponCode_whenInstantiate_thenThrowException(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Coupon coupon = new Coupon("", BigDecimal.valueOf(20.5), LocalDate.now().plusDays(1l));
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Coupon coupon = new Coupon(null, BigDecimal.valueOf(20.5), LocalDate.now().plusDays(1l));
        });
    }

    @Test
    public void givenInvalidCouponDiscount_whenInstantiate_thenThrowException(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Coupon coupon = new Coupon("code", BigDecimal.valueOf(0), LocalDate.now().plusDays(1l));
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Coupon coupon = new Coupon("code", BigDecimal.valueOf(-0.01), LocalDate.now().plusDays(1l));
        });
    }

    @Test
    public void givenInvalidCouponDate_whenInstantiate_thenThrowException(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Coupon coupon = new Coupon("code", BigDecimal.valueOf(20.5), LocalDate.now().plusDays(-1l));
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Coupon coupon = new Coupon("code", BigDecimal.valueOf(20.5), LocalDate.now().plusDays(0l));
        });
    }




}
