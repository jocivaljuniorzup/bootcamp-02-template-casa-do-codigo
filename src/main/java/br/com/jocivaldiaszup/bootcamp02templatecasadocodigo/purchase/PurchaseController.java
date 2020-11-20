package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.purchase;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/order")
public class PurchaseController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody @Valid NewPurchaseRequest newPurchaseRequest,
                                    UriComponentsBuilder uriComponentsBuilder){
        Purchase purchase = NewPurchaseRequest.toModel(newPurchaseRequest, entityManager);
        entityManager.persist(purchase);
        purchase.validateTotal();
        purchase.applyCoupon();

        URI uri = uriComponentsBuilder.path("/order/{id}")
                .buildAndExpand(purchase.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

}
