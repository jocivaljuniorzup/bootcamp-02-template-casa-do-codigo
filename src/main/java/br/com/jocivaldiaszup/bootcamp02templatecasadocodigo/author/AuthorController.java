package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.author;

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
@RequestMapping(value = "/author")
// 4 -> 3 classes minhas + 1 método
public class AuthorController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    // 1
    public ResponseEntity<?> create(@RequestBody @Valid NewAuthorRequest newAuthorRequest){
        // 3
        Author author = NewAuthorRequest.toModel(newAuthorRequest);
        entityManager.persist(author);
        return ResponseEntity.ok(NewAuthorResponse.fromModel(author));
    }

}
