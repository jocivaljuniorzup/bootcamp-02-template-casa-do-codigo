package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.book;

import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.author.Author;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.category.Category;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.util.TestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;

public class NewBookRequestTest {

    EntityManager entityManager = Mockito.mock(EntityManager.class);
    NewBookRequest newBookRequest = new NewBookRequest("title",
            "abstract",
            "summary",
            BigDecimal.valueOf(100),
            200,
            "123456",
            LocalDate.now().plusDays(1l),
            1l,
            1l);

    @Test
    public void givenValidAuthorAndValidCategory_whenToModel_thenSuccess(){
        Mockito.when(entityManager.find(Category.class, 1l)).thenReturn(new Category("Example Category"));
        Mockito.when(entityManager.find(Author.class, 1l)).thenReturn(new Author("Author",
                "email@email.com",
                "description"));
        Assertions.assertNotNull(NewBookRequest.toModel(newBookRequest, entityManager));
    }

    @Test
    public void givenInvalidAuthorAndValidCategory_whenToModel_thenSuccess(){
        Mockito.when(entityManager.find(Category.class, 1l)).thenReturn(new Category("Example Category"));
        Mockito.when(entityManager.find(Author.class, 1l)).thenReturn(null);
        Assertions.assertThrows(IllegalArgumentException.class, () -> NewBookRequest.toModel(newBookRequest, entityManager));
    }

    @Test
    public void givenValidAuthorAndInvalidCategory_whenToModel_thenSuccess(){
        Mockito.when(entityManager.find(Category.class, 1l)).thenReturn(null);
        Mockito.when(entityManager.find(Author.class, 1l)).thenReturn(new Author("Author",
                "email@email.com",
                "description"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> NewBookRequest.toModel(newBookRequest, entityManager));
    }


}
