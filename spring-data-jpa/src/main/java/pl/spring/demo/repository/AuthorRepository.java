package pl.spring.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.spring.demo.entity.AuthorEntity;

import java.util.List;

public interface AuthorRepository  extends JpaRepository<AuthorEntity, Long> {

    @Query("select author from AuthorEntity author where author.firstName like :name%  or author.lastName like :name%")
    public List<AuthorEntity> findAuthorByName(@Param("name") String name);

   
//    @Query("UPDATE book from BookEntity book SET book.title=:newTitle WHERE book.id=:id")
//    @Modifying
//    @Transactional
//    @Query("update BookEntity book set book.title = :newTitle where book.id = :id")
//	public void editBookTitle(@Param("id")long id, @Param("newTitle") String newTitle);
}
