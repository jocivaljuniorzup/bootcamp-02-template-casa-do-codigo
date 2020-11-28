package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.book;

public class ListBookResponse {

    private Long id;

    private String title;

    @Deprecated
    public ListBookResponse() {
    }

    public ListBookResponse(Long id,
                            String title) {

        this.id = id;
        this.title = title;

    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public static ListBookResponse fromModel(Book book) {
        return new ListBookResponse(book.getId(), book.getTitle());
    }
}
