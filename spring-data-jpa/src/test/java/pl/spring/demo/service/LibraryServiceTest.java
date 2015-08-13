package pl.spring.demo.service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.entity.LibraryEntity;
import pl.spring.demo.mapper.BookMapper;
import pl.spring.demo.to.BookTo;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class LibraryServiceTest {

	@Autowired
	LibraryService libraryService;
	@Autowired
	BookService bookService;

	@Before
	public void setUp(){
		BookEntity bookEntity = new BookEntity(null, "title");
		LibraryEntity libraryEntity = new LibraryEntity(100L, "nameLib");
		bookEntity.setLibraryEntity(libraryEntity);
		libraryService.save(libraryEntity);
		bookService.saveBook(BookMapper.map(bookEntity));
	}
	
	@Test
	public void testShouldRemoveLibrary2() {
		// given
		final long libraryId = 100L;
		long countBeforeRemoval = libraryService.numberOfLibraries();
		
		// when
		libraryService.deleteLibrary(libraryId);
		long countAfterRemoval = libraryService.numberOfLibraries();
		// then
		assertTrue("One Library has been removed",
				countBeforeRemoval - 1 == countAfterRemoval);
		assertFalse(libraryService.libraryExist(libraryId));
		
	}

	@Test
	public void testShouldRemoveBookWhenLibrary4Removed() {
		// given
		final long libraryId = 100L;
		List<BookTo> findAllBooksFromLibrary = bookService
				.findAllBooksFromLibrary(libraryId);
		// when
		libraryService.deleteLibrary(libraryId);
		// then
		for (BookTo bookTo : findAllBooksFromLibrary) {
			assertFalse("Ksiazka zostala usunieta wraz z biblioteka",
					bookService.bookExist(bookTo.getId()));
		}

	}
}
