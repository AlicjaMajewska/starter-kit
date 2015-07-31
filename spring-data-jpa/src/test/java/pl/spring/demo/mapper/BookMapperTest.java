package pl.spring.demo.mapper;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookEntity;
import pl.spring.demo.to.BookTo;

public class BookMapperTest {

	private BookMapper bookMapper = new BookMapper();

	@Test
	public void shouldConvertToBookToSingleAuthor() {
		// given
		BookEntity bookEntity = new BookEntity(5L,
				"Pan Samochodzik i Fantomas",
				new ArrayList<AuthorTo>(Arrays.asList(new AuthorTo(1L,
						"Zbigniew", "Nienacki"))));
		BookTo bookTo = new BookTo(5L, "Pan Samochodzik i Fantomas",
				"Zbigniew Nienacki");
		// when
		BookTo convertToBookTo = bookMapper.convertToBookTo(bookEntity);
		Assert.assertTrue(bookTo.equals(convertToBookTo));
	}

	@Test
	public void shouldConvertToBookToMultipleAuthors() {
		// given
		BookEntity bookEntity = new BookEntity(5L,
				"Pan Samochodzik i Fantomas", new ArrayList<AuthorTo>(
						Arrays.asList(new AuthorTo(1L, "Zbigniew", "Nienacki"),
								new AuthorTo(2L, "Alicja", "Majewska"),
								new AuthorTo(3L, "Krzysztof", "Ibisz"))));
		BookTo bookTo = new BookTo(5L, "Pan Samochodzik i Fantomas",
				"Zbigniew Nienacki, Alicja Majewska, Krzysztof Ibisz");
		// when
		BookTo convertToBookTo = bookMapper.convertToBookTo(bookEntity);
		Assert.assertTrue(bookTo.equals(convertToBookTo));
	}

	@Test
	public void shouldConvertToBookToNullArguments() {
		// given
		BookEntity bookEntity = new BookEntity(null, null,
				new ArrayList<AuthorTo>());
		BookTo bookTo = new BookTo(null, null, null);
		// when
		BookTo convertToBookTo = bookMapper.convertToBookTo(bookEntity);
		Assert.assertEquals(bookTo,convertToBookTo);
	}

	@Test
	public void shouldConvertToBookEntitySingleAuthor() {
		// given
		BookEntity bookEntity = new BookEntity(5L,
				"Pan Samochodzik i Fantomas",
				new ArrayList<AuthorTo>(Arrays.asList(new AuthorTo(1L,
						"Zbigniew", "Nienacki"))));
		BookTo bookTo = new BookTo(5L, "Pan Samochodzik i Fantomas",
				"Zbigniew Nienacki");
		// when
		BookEntity convertToBookEntity = bookMapper.convertToBookEntity(bookTo);
		Assert.assertTrue(bookEntity.equals(convertToBookEntity));
	}

	@Test
	public void shouldConvertToBookEntityNullArguments() {
		// given
		BookEntity bookEntity = new BookEntity(null, null,
				new ArrayList<AuthorTo>(Arrays.asList()));
		BookTo bookTo = new BookTo(null, null, null);
		// when
		BookEntity convertToBookEntity = bookMapper.convertToBookEntity(bookTo);
		Assert.assertEquals(bookEntity, convertToBookEntity);
	}

	@Test
	public void shouldConvertToBookEntityMultipleAuthor() {
		// given
		BookEntity bookEntity = new BookEntity(5L,
				"Pan Samochodzik i Fantomas", new ArrayList<AuthorTo>(
						Arrays.asList(new AuthorTo(1L, "Zbigniew", "Nienacki"),
								new AuthorTo(2L, "Alicja", "Majewska"),
								new AuthorTo(3L, "Krzysztof", "Ibisz"))));
		BookTo bookTo = new BookTo(5L, "Pan Samochodzik i Fantomas",
				"Zbigniew Nienacki, Alicja Majewska, Krzysztof Ibisz");
		// when
		BookEntity convertToBookEntity = bookMapper.convertToBookEntity(bookTo);
		Assert.assertTrue(bookEntity.equals(convertToBookEntity));
	}

}
