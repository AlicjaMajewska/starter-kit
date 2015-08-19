package pl.spring.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import pl.spring.demo.entity.BookEntity;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    @Query("select book from BookEntity book where book.title like :title%")
    public List<BookEntity> findBookByTitle(@Param("title") String title);

    @Query("select book from BookEntity book where book.authors like %:author%")
    public List<BookEntity> findBookByAuthor(@Param("author") String author);

   
//    @Query("UPDATE book from BookEntity book SET book.title=:newTitle WHERE book.id=:id")
    @Modifying
    @Transactional
    @Query("update BookEntity book set book.title = :newTitle where book.id = :id")
	public void editBookTitle(@Param("id")long id, @Param("newTitle") String newTitle);
}
