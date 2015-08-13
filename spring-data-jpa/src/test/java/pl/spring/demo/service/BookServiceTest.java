package pl.spring.demo.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.repository.LibraryRepository;
import pl.spring.demo.to.BookTo;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class BookServiceTest {
	@Autowired
	BookService bookService;
	@Autowired
	private LibraryRepository libraryRepository;

	@Test
	public void testShoudSetLibraryIntoSavedBook() {
		// given
		BookTo bookToSave = new BookTo(null, "Alicja w krainie czarow",
				"James Carroll", 2L, null);
		// when
		BookTo savedBook = bookService.saveBook(bookToSave);
		// then
		assertNotNull(savedBook.getLibraryName());
		assertNotNull(libraryRepository.getOne(savedBook.getId()));

	}

}
