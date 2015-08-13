package pl.spring.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.searchcriteria.UsingQueryDSLBookRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long>, UsingQueryDSLBookRepository {

    @Query("select book from BookEntity book where upper(book.title) like concat(upper(:title), '%')")
    public List<BookEntity> findBookByTitle(@Param("title") String title);

    @Query("select book from BookEntity book JOIN book.authors author where upper(author.firstName) like concat('%', upper(:author), '%') or upper(author.lastName) like concat('%', upper(:author), '%')")
    public List<BookEntity> findBookByAuthor(@Param("author") String author);
 
    @Query("from BookEntity book where book.libraryEntity.id = :library_id")
    public List<BookEntity> findAllBooksFromLibrary(@Param("library_id") long libraryId);
}
