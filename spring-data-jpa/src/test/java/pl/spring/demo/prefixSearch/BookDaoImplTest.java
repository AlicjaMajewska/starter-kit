package pl.spring.demo.prefixSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;

import pl.spring.demo.dao.BookDao;
import pl.spring.demo.dao.impl.BookDaoImpl;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookEntity;

public class BookDaoImplTest {

	BookDao bookDao = new BookDaoImpl();

	@Before
	public void setUpt() {
		Set<BookEntity> ALL_BOOKS = new HashSet<>();
		addTestBooks(ALL_BOOKS);
		Whitebox.setInternalState(bookDao, "ALL_BOOKS", ALL_BOOKS);

	}

	@Test
	public void shouldFindAllBooks() {
		// given
		int size = 6;
		// when
		List<BookEntity> foundBooks = bookDao.findAll();
		// then
		Assert.assertEquals(size, foundBooks.size());
	}

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
		Assert.assertTrue(foundBooks.isEmpty());
	}

	private void addTestBooks(Set<BookEntity> ALL_BOOKS) {
		ALL_BOOKS.add(new BookEntity(1L, "Romeo i Julia",
				new ArrayList<AuthorTo>(Arrays.asList(new AuthorTo(1L,
						"Wiliam", "Szekspir")))));
		ALL_BOOKS.add(new BookEntity(2L, "Opium w rosole",
				new ArrayList<AuthorTo>(Arrays.asList(new AuthorTo(1L, "Hanna",
						"Ożogowska")))));
		ALL_BOOKS.add(new BookEntity(3L, "Przygody Odyseusza",
				new ArrayList<AuthorTo>(Arrays.asList(new AuthorTo(1L, "Jan",
						"Parandowski")))));
		ALL_BOOKS.add(new BookEntity(4L, "Awantura w Niekłaju",
				new ArrayList<AuthorTo>(Arrays.asList(new AuthorTo(1L,
						"Edmund", "Niziurski")))));
		ALL_BOOKS.add(new BookEntity(5L, "Pan Samochodzik i Fantomas",
				new ArrayList<AuthorTo>(Arrays.asList(new AuthorTo(1L,
						"Zbigniew", "Nienacki")))));
		ALL_BOOKS.add(new BookEntity(6L, "Zemsta", new ArrayList<AuthorTo>(
				Arrays.asList(new AuthorTo(1L, "Aleksander", "Fredro")))));
	}

}
