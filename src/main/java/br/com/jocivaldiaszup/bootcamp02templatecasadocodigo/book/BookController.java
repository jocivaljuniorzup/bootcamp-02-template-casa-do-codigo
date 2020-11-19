package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.book;

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
@RequestMapping(value = "/book")
public class BookController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody @Valid NewBookRequest newBookRequest){
        Book book = NewBookRequest.toModel(newBookRequest, entityManager);
        entityManager.persist(book);
        return ResponseEntity.ok(NewBookResponse.fromModel(book));
    }

}
