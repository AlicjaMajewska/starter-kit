package pl.spring.demo.dao.impl;

import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.annotation.SaveId;
import pl.spring.demo.common.Sequence;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.mapper.BookMapper;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookEntity;
import pl.spring.demo.to.BookTo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookDaoImpl implements BookDao {

	private static  Set<BookEntity> ALL_BOOKS = new HashSet<>();
	@Autowired
	private Sequence sequence;
	@Autowired
	private BookMapper bookMapper;

	public BookDaoImpl() {
		addTestBooks();
	}

	@Override
	public List<BookEntity> findAll() {
		return new ArrayList<>(ALL_BOOKS);
	}

	@Override
	public List<BookEntity> findBookByTitle(String title) {
		List<BookEntity> foundBooks = new ArrayList<BookEntity>();
		for (BookEntity bookEntity : ALL_BOOKS) {
			if (bookEntity.getTitle().startsWith(title)) {
				foundBooks.add(bookEntity);
			}
		}
		return foundBooks;
	}

	@Override
	public List<BookEntity> findBooksByAuthor(String author) {
		List<BookEntity> foundBooks = new ArrayList<BookEntity>();
		String[] splitAuthor = author.split(" ");
		for (BookEntity bookEntity : ALL_BOOKS) {
			for (AuthorTo authorTo : bookEntity.getAuthors()) {
				if (splitAuthor.length == 1
						&& (authorTo.getFirstName().startsWith(author) || authorTo
								.getLastName().startsWith(author))) {
					foundBooks.add(bookEntity);
				}
				if (splitAuthor.length == 2
						&& authorTo.getFirstName().startsWith(splitAuthor[0])
						&& authorTo.getLastName().startsWith(splitAuthor[1])) {
					foundBooks.add(bookEntity);
				}
			}
		}
		return foundBooks;
	}

	@Override
	@NullableId
	@SaveId
	public BookEntity save(BookEntity book) {
		ALL_BOOKS.add(book);
		return book;
	}

	public long getNextId() {
		return sequence.nextValue(ALL_BOOKS);
	}

	public void setSequence(Sequence sequence) {
		this.sequence = sequence;
	}

	private void addTestBooks() {
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
