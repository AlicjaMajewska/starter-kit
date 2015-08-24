package pl.spring.demo.to;

import java.util.Collection;
import java.util.List;

public class BookTo {
    private Long id;
    private String title;
    private Collection<AuthorTo> authorsTo;

    public BookTo() {
    }

    public BookTo(Long id, String title, Collection<AuthorTo> authorsTo) {
        this.id = id;
        this.title = title;
        this.authorsTo = authorsTo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Collection<AuthorTo> getAuthorsTo() {
        return authorsTo;
    }

    public void setAuthorsTo( Collection<AuthorTo> authorTo) {
        this.authorsTo = authorTo;
    }
}
