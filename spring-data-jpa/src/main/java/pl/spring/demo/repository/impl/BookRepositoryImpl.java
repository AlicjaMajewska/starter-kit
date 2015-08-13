package pl.spring.demo.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;

import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.entity.QBookEntity;
import pl.spring.demo.searchcriteria.BookSearchCriteria;
import pl.spring.demo.searchcriteria.UsingQueryDSLBookRepository;

public class BookRepositoryImpl implements UsingQueryDSLBookRepository {

	@PersistenceContext(name = "jsql")
	private EntityManager entityManager;

	@Override
	public List<BookEntity> findBookByCriteria(
			BookSearchCriteria bookSearchCriteria) {
		JPAQuery query = new JPAQuery(entityManager);
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		QBookEntity book = QBookEntity.bookEntity;

		if (bookSearchCriteria.getTitle() != null) {
			booleanBuilder.and(book.title
					.startsWithIgnoreCase(bookSearchCriteria.getTitle()));
		}
		if (bookSearchCriteria.getLibraryName() != null) {
			booleanBuilder.and(book.libraryEntity.name
					.startsWithIgnoreCase(bookSearchCriteria.getLibraryName()));
		}
		if (bookSearchCriteria.getAuthor() != null) {
			String firstName = bookSearchCriteria.getAuthor();
			String lastName = bookSearchCriteria.getAuthor();

			if (bookSearchCriteria.getAuthor().contains(" ")) {
				String[] splitAuthorName = bookSearchCriteria.getAuthor()
						.split(" ");
				firstName = splitAuthorName[0];
				lastName = splitAuthorName[1];
			}
			booleanBuilder
					.and((book.authors.any().firstName
							.startsWithIgnoreCase(firstName)).or(book.authors
							.any().lastName.startsWithIgnoreCase(lastName)));
		}

		return query.from(book).where(booleanBuilder).list(book);
	}

}
