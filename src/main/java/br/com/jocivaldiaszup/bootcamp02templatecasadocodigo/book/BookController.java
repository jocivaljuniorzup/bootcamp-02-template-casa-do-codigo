package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.book;

import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.exception.ObjectNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/book")

public class BookController {


    private final EntityManager entityManager;

    public BookController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

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
    //1
    public ResponseEntity<List<ListBookResponse>> findAll(){
        //2
        List<Book> bookList = entityManager.createQuery("select b from Book b", Book.class).getResultList();

        List<ListBookResponse> listBookResponses = bookList.stream()
                .map(ListBookResponse::fromModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(listBookResponses);
    }

    @GetMapping(value = "/{id}")
    //1
    public ResponseEntity<?> bookDetails(@PathVariable(name = "id") Long bookId){
        Optional<Book> book = Optional.ofNullable(entityManager.find(Book.class, bookId));
        // 2
        BookDetailResponse bookDetailResponse = BookDetailResponse.fromModel(book.orElseThrow( () -> {
            throw new ObjectNotFoundException("Book " + bookId + " not found in database.");
        }));

        return ResponseEntity.ok(bookDetailResponse);
    }

}
