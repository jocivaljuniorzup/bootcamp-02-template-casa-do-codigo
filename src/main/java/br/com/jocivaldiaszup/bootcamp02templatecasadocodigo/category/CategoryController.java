package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.category;

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
@RequestMapping(value = "/category")

//4
public class CategoryController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    //1
    public ResponseEntity<?> create(@RequestBody @Valid NewCategoryRequest newCategoryRequest){
        // 3
        Category category = NewCategoryRequest.toModel(newCategoryRequest);
        entityManager.persist(category);
        return ResponseEntity.ok(NewCategoryResponse.fromModel(category));
    }

}
