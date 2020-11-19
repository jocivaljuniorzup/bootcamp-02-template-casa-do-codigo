package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.book;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/book")
// 4
public class BookController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    // 1
    public ResponseEntity<?> create(@RequestBody @Valid NewBookRequest newBookRequest){
        // 3
        Book book = NewBookRequest.toModel(newBookRequest, entityManager);
        entityManager.persist(book);
        return ResponseEntity.ok(NewBookResponse.fromModel(book));
    }

    @GetMapping
    public ResponseEntity<List<ListBookResponse>> findAll(){
        List<Book> bookList = entityManager.createQuery("select b from Book b", Book.class).getResultList();

        List<ListBookResponse> listBookResponses = bookList.stream()
                .map(ListBookResponse::fromModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(listBookResponses);
    }

}
