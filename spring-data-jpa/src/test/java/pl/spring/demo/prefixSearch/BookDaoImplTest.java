package pl.spring.demo.prefixSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.dao.BookDao;
import pl.spring.demo.dao.impl.BookDaoImpl;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class BookDaoImplTest {

	@Autowired
	BookDao bookDao = new BookDaoImpl();

	@Test
	public void shouldFindBookByFullTitle() {
		// given
		String title = "Zemsta";
		BookEntity bookToFind = new BookEntity(6L, "Zemsta",
				new ArrayList<AuthorTo>(Arrays.asList(new AuthorTo(1L,
						"Aleksander", "Fredro"))));
		// when
		List<BookEntity> foundBooks = bookDao.findBookByTitle(title);
		// then
		Assert.assertTrue(foundBooks.contains(bookToFind));
	}

	@Test
	public void shouldFindBookByFirstLetterOfTitle() {
		// given
		String title = "Z";
		BookEntity bookToFind = new BookEntity(6L, "Zemsta",
				new ArrayList<AuthorTo>(Arrays.asList(new AuthorTo(1L,
						"Aleksander", "Fredro"))));
		// when
		List<BookEntity> foundBooks = bookDao.findBookByTitle(title);
		// then
		Assert.assertTrue(foundBooks.contains(bookToFind));
	}

	@Test
	public void shouldFindTwoBooksByFirstLetterOfTitle() {
		// given
		String title = "P";
		BookEntity bookToFind1 = new BookEntity(3L, "Przygody Odyseusza",
				new ArrayList<AuthorTo>(Arrays.asList(new AuthorTo(1L, "Jan",
						"Parandowski"))));
		BookEntity bookToFind2 = new BookEntity(5L,
				"Pan Samochodzik i Fantomas",
				new ArrayList<AuthorTo>(Arrays.asList(new AuthorTo(1L,
						"Zbigniew", "Nienacki"))));

		// when
		List<BookEntity> foundBooks = bookDao.findBookByTitle(title);
		// then
		Assert.assertTrue(foundBooks.contains(bookToFind1)
				&& foundBooks.contains(bookToFind2));
	}

	@Test
	public void shouldFindBookByAuthorFirstName() {
		// given
		String firstName = "Aleksander";
		BookEntity bookToFind = new BookEntity(6L, "Zemsta",
				new ArrayList<AuthorTo>(Arrays.asList(new AuthorTo(1L,
						"Aleksander", "Fredro"))));
		// when
		List<BookEntity> foundBooks = bookDao.findBooksByAuthor(firstName);
		// then
		Assert.assertTrue(foundBooks.contains(bookToFind));

	}

	@Test
	public void shouldFindBookByAuthorLastName() {
		// given
		String lastName = "Parand";
		BookEntity bookToFind = new BookEntity(3L, "Przygody Odyseusza",
				new ArrayList<AuthorTo>(Arrays.asList(new AuthorTo(1L, "Jan",
						"Parandowski"))));
		// when
		List<BookEntity> foundBooks = bookDao.findBooksByAuthor(lastName);
		// then
		Assert.assertTrue(foundBooks.contains(bookToFind));

	}

	@Test
	public void shouldFindTwoBooksByAuthorLastName() {
		// given
		String lastName = "Ni";
		BookEntity bookToFind1 = new BookEntity(5L,
				"Pan Samochodzik i Fantomas",
				new ArrayList<AuthorTo>(Arrays.asList(new AuthorTo(1L,
						"Zbigniew", "Nienacki"))));
		BookEntity bookToFind2 = new BookEntity(5L,
				"Pan Samochodzik i Fantomas",
				new ArrayList<AuthorTo>(Arrays.asList(new AuthorTo(1L,
						"Zbigniew", "Nienacki"))));
		// when
		List<BookEntity> foundBooks = bookDao.findBooksByAuthor(lastName);
		// then
		Assert.assertTrue(foundBooks.contains(bookToFind1)
				&& foundBooks.contains(bookToFind2));

	}

	@Test
	public void shouldFindBookByAuthorFirstAndLastName() {
		// given
		String name = "Wili Sze";
		BookEntity bookToFind = new BookEntity(1L, "Romeo i Julia",
				new ArrayList<AuthorTo>(Arrays.asList(new AuthorTo(1L,
						"Wiliam", "Szekspir"))));

		// when
		List<BookEntity> foundBooks = bookDao.findBooksByAuthor(name);
		// then
		Assert.assertTrue(foundBooks.contains(bookToFind));

	}

	@Test
	public void shouldNotFindBookByAuthorFirstAndLastName() {
		// given
		String fullName = "Hann Fred";
		// when
		List<BookEntity> foundBooks = bookDao.findBooksByAuthor(fullName);
		// then
		Assert.assertTrue(foundBooks.size() == 0);

	}

}
