package pl.spring.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.spring.demo.entity.BookEntity;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    @Query("select book from BookEntity book where upper(book.title) like concat(upper(:title), '%')")
    public List<BookEntity> findBookByTitle(@Param("title") String title);

    @Query("select book from BookEntity book where upper(book.authors) like concat('%', upper(:author), '%')")
    public List<BookEntity> findBookByAuthor(@Param("author") String author);

    @Modifying
    @Query("delete from BookEntity b where b = :book")
	public void delete(@Param("book")BookEntity book);
 
    @Modifying
    @Query("delete from BookEntity b where b.id = :id")
    public void deleteById(@Param("id")long id);
   
    @Query("select book from BookEntity book where book.id =:id")
	public List<BookEntity> findBookById(long id);
    
}
