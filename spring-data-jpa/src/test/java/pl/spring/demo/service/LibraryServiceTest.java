package pl.spring.demo.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

	@Test
	public void testShouldRemoveLibrary2WithBooks() {
		// given
		final long libraryId= 2L;
		long countBeforeRemoval = libraryService.numberOfLibraries();
		List<BookTo> findAllBooksFromLibrary = bookService.findAllBooksFromLibrary(libraryId);
		// when
		libraryService.deleteLibrary(libraryId);
		long countAfterRemoval = libraryService.numberOfLibraries();
		// then
		assertTrue("One Library has been removed",
				countBeforeRemoval - 1 == countAfterRemoval);
		assertFalse(libraryService.libraryExist(libraryId));
		for (BookTo bookTo : findAllBooksFromLibrary) {
			assertFalse("Ksiazka zostala usunieta wraz z biblioteka",bookService.bookExist(bookTo.getId()));
		}
		
	}
}
