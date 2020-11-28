package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.purchase;

import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.exception.ObjectNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(value = "/purchase")
public class PurchaseController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody @Valid NewPurchaseRequest newPurchaseRequest,
                                    UriComponentsBuilder uriComponentsBuilder){
        Purchase purchase = NewPurchaseRequest.toModel(newPurchaseRequest, entityManager);
        entityManager.persist(purchase);

        URI uri = uriComponentsBuilder.path("/purchase/{id}")
                .buildAndExpand(purchase.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") Long id){
        Optional<Purchase> purchase = Optional.ofNullable(entityManager.find(Purchase.class, id));

        NewPurchaseResponse newPurchaseResponse = NewPurchaseResponse.fromModel(purchase.orElseThrow(() -> {
            throw new ObjectNotFoundException("Purchase " + id +" not found");
        }));

        return ResponseEntity.ok(newPurchaseResponse);
    }

}
