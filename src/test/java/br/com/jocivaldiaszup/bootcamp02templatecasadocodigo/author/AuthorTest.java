package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.author;

import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.util.TestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AuthorTest {


    @Test
    void givenValidAuthor_whenInstantiate_thenSuccess() {
        Author author = new Author("Example 001", "email@email.com", TestUtil.generateString(400, "b"));
        Assertions.assertNotNull(author);
    }

    @Test
    public void givenInvalidAuthorName_whenInstantiate_thenThrowException(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Author author = new Author("", "email@email.com", TestUtil.generateString(400, "b"));
        });
    }

    @Test
    public void givenInvalidAuthorEmail_whenInstantiate_thenThrowException(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Author author = new Author("Example 001", "", TestUtil.generateString(400, "b"));
        });
    }

    @Test
    public void givenInvalidAuthorDescription_whenInstantiate_thenThrowException(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Author author = new Author("Example 001", "email@email.com", "");
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Author author = new Author("Example 001", "email@email.com", TestUtil.generateString(401, "a"));
        });
    }

}
