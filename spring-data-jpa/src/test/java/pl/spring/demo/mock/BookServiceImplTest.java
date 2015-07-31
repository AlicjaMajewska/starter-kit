package pl.spring.demo.mock;

/**
 * @COPYRIGHT (C) 2015 Schenker AG
 *
 * All rights reserved
 */

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.mockito.internal.util.reflection.Whitebox;

import pl.spring.demo.dao.BookDao;
import pl.spring.demo.mapper.BookMapper;
import pl.spring.demo.service.impl.BookServiceImpl;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookEntity;
import pl.spring.demo.to.BookTo;
import static org.junit.Assert.assertEquals;

/**
 * TODO The class BookServiceImplTest is supposed to be documented...
 *
 * @author AOTRZONS
 */
public class BookServiceImplTest {

	@InjectMocks
	private BookServiceImpl bookService;
	@Mock
	private BookDao bookDao;
	

	private BookMapper bookMapper;

	@Before
	public void setUpt() {
		MockitoAnnotations.initMocks(this);
		bookMapper = new BookMapper();
		Whitebox.setInternalState(bookService, "bookMapper", bookMapper);
	}

	@Test
	public void testShouldSaveBook() {
		// given
		// BookEntity book = bookMapper.convertToBookEntity(new BookTo(null,
		// "title", "author author"));
		BookEntity book = new BookEntity(null, "title",
				new ArrayList<AuthorTo>(Arrays.asList(new AuthorTo(1L,
						"author", "author"))));
		Mockito.when(bookDao.save(book)).thenReturn(
				new BookEntity(1L, "title", new ArrayList<AuthorTo>(Arrays
						.asList(new AuthorTo(1L, "author", "author")))));
		// when
		BookTo result = bookService.saveBook(bookMapper.convertToBookTo(book));
		// then
		Mockito.verify(bookDao).save(book);
		assertEquals(1L, result.getId().longValue());
	}
}
