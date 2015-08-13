package pl.spring.demo.mapper;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import pl.spring.demo.entity.AuthorEntity;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.entity.LibraryEntity;
import pl.spring.demo.to.BookTo;

public class BookMapperTest {
	private Set<AuthorEntity> authors;
	private LibraryEntity libraryEntity;

	@Before
	public void setUp() {
		authors = new HashSet<AuthorEntity>();
		libraryEntity = new LibraryEntity(2L,
				"Biblioteka im. Alicji Majewskiej");
		AuthorEntity authorEntity = new AuthorEntity("James", "Carroll");
		authors.add(authorEntity);
	}

	@Test
	public void testShoulMapCorrectlyToBookToWithNullId() {
		// given
		BookEntity bookEntity = new BookEntity(null, "Alicja w krainie czarow");
		bookEntity.setAuthors(authors);
		bookEntity.setLibraryEntity(libraryEntity);
		BookTo expectedBook = new BookTo(null, "Alicja w krainie czarow",
				"James Carroll", 2L, "Biblioteka im. Alicji Majewskiej");
		// when
		BookTo mappedBook = BookMapper.map(bookEntity);
		// then
		System.out.println("expectedBook : " + expectedBook);
		System.out.println("mappedBook : " + mappedBook);
		assertEquals(expectedBook, mappedBook);

	}

	@Test
	public void testShoulMapCorrectlyToBookToWithId2() {
		// given
		BookEntity bookEntity = new BookEntity(2L, "Alicja w krainie czarow");
		bookEntity.setAuthors(authors);
		bookEntity.setLibraryEntity(libraryEntity);
		BookTo expectedBook = new BookTo(2L, "Alicja w krainie czarow",
				"James Carroll", 2L, "Biblioteka im. Alicji Majewskiej");
		// when
		BookTo mappedBook = BookMapper.map(bookEntity);

		// then
		System.out.println("expectedBook : " + expectedBook);
		System.out.println("mappedBook : " + mappedBook);
		assertEquals(expectedBook, mappedBook);

	}

}
