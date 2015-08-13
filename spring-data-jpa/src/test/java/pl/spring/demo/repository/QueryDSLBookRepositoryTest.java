package pl.spring.demo.repository;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.repository.impl.BookRepositoryImpl;
import pl.spring.demo.searchcriteria.BookSearchCriteria;
import pl.spring.demo.searchcriteria.UsingQueryDSLBookRepository;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonRepositoryTest-context.xml")
public class QueryDSLBookRepositoryTest {

	private BookSearchCriteria bookSearchCriteria;

	@Autowired
	private BookRepository bookRepository;

	@Before
	public void setUp() {
		bookSearchCriteria = new BookSearchCriteria();
		// usingQueryDSLBookRepository = new BookRepositoryImpl();
	}
	@Test
	public void testShouldFindAllBooksWhenNoSearchCriteriaGiven(){
		//given
		long numberOfAllBooks = bookRepository.count();
		//when
		List<BookEntity> findBookByCriteria = bookRepository
				.findBookByCriteria(bookSearchCriteria);
		//then
		assertFalse(findBookByCriteria.isEmpty());
		assertEquals(numberOfAllBooks, findBookByCriteria.size());
	}
	
	@Test
	public void testShouldFindBookWhenOnlyTitleGiven() {
		// given
		String title = "Pierwsza książka";
		bookSearchCriteria.setTitle("Pierw");
		// when
		List<BookEntity> findBookByCriteria = bookRepository
				.findBookByCriteria(bookSearchCriteria);
		// then
		assertFalse(findBookByCriteria.isEmpty());
		boolean bookIsFound = false;
		for (BookEntity bookEntity : findBookByCriteria) {
			if (title.equals(bookEntity.getTitle())) {
				bookIsFound = true;
			}
		}
		assertTrue("Znaleziono ksiazke o tytule " + title
				+ " dla prefixu Pierw", bookIsFound);
	}
	@Test
	public void testShouldFindBookWhenOnlyLibraryNameGiven() {
		// given
		String libraryName = "Biblioteka im. Alicji Majewskiej";
		long libraryId = 4L;
		bookSearchCriteria.setLibraryName("Biblioteka i");
		
		List<BookEntity> findAllBooksFromLibrary = bookRepository.findAllBooksFromLibrary(libraryId);
		// when
		List<BookEntity> findBookByCriteria = bookRepository
				.findBookByCriteria(bookSearchCriteria);
		// then
		assertFalse(findBookByCriteria.isEmpty());
		assertEquals(findAllBooksFromLibrary.size(), findBookByCriteria.size());
		assertTrue(findBookByCriteria.containsAll(findAllBooksFromLibrary));
	}
	@Test
	public void testShouldFindBookWhenTitleAndLibraryNameGiven() {
		// given
		bookSearchCriteria.setLibraryName("Biblioteka i");
		bookSearchCriteria.setTitle("S");
		
		// when
		List<BookEntity> findBookByCriteria = bookRepository
				.findBookByCriteria(bookSearchCriteria);
		// then
		assertFalse(findBookByCriteria.isEmpty());
		assertEquals(2, findBookByCriteria.size());
	}
	@Test
	public void testShouldFindBookWhenOnlyAuthorFirstOrLastNameGiven() {
		// given
		bookSearchCriteria.setAuthor("Alicj");
		
		// when
		List<BookEntity> findBookByCriteria = bookRepository
				.findBookByCriteria(bookSearchCriteria);
		// then
		assertFalse(findBookByCriteria.isEmpty());
		assertEquals(4, findBookByCriteria.size());
	}
	@Test
	public void testShouldFindBookWhenOnlyAuthorFirstAndLastNameGiven() {
		// given
		bookSearchCriteria.setAuthor("Alicja Maj");
		
		// when
		List<BookEntity> findBookByCriteria = bookRepository
				.findBookByCriteria(bookSearchCriteria);
		// then
		assertFalse(findBookByCriteria.isEmpty());
		assertEquals(4, findBookByCriteria.size());
	}
	@Test
	public void testShouldFindBookWhenAuthorAndTitleGiven() {
		// given
		bookSearchCriteria.setAuthor("Alicja");
		bookSearchCriteria.setTitle("S");
		
		// when
		List<BookEntity> findBookByCriteria = bookRepository
				.findBookByCriteria(bookSearchCriteria);
		// then
		assertFalse(findBookByCriteria.isEmpty());
		assertEquals(2, findBookByCriteria.size());
	}
	@Test
	public void testShouldFindBookWhenAuthorAndLibraryGiven() {
		// given
		bookSearchCriteria.setAuthor("Jan");
		bookSearchCriteria.setLibraryName("Ks");
		
		// when
		List<BookEntity> findBookByCriteria = bookRepository
				.findBookByCriteria(bookSearchCriteria);
		// then
		assertFalse(findBookByCriteria.isEmpty());
		assertEquals(2, findBookByCriteria.size());
	}
	@Test
	public void testShouldFindBookWhenAuthorAndLibraryAndTitleGiven() {
		// given
		bookSearchCriteria.setAuthor("Alicja");
		bookSearchCriteria.setTitle("S");
		bookSearchCriteria.setLibraryName("Biblioteka im");
		
		// when
		List<BookEntity> findBookByCriteria = bookRepository
				.findBookByCriteria(bookSearchCriteria);
		// then
		assertFalse(findBookByCriteria.isEmpty());
		assertEquals(2, findBookByCriteria.size());
	}

}
