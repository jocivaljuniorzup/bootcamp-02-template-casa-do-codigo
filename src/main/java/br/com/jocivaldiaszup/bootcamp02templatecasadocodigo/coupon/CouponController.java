package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.coupon;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/coupon")
public class CouponController {

    @PersistenceContext
    EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody @Valid NewCouponRequest newCouponRequest){
        Coupon coupon = NewCouponRequest.toModel(newCouponRequest);
        entityManager.persist(coupon);
        return ResponseEntity.ok(NewCouponResponse.fromModel(coupon));
    }

}
