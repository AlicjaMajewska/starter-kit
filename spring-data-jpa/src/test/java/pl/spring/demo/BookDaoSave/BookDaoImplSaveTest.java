package pl.spring.demo.BookDaoSave;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.common.Sequence;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookEntity;

import java.util.ArrayList;
import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class BookDaoImplSaveTest {

	@Autowired
	BookDao bookDao;
	@Autowired
	Sequence sequence;

	@Test
	public void testShouldChangeBookId() {
		// given
		BookEntity book = new BookEntity(null, "title",
				new ArrayList<AuthorTo>(Arrays.asList(new AuthorTo(1L,
						"author", "author"))));
		Mockito.when(sequence.nextValue(Mockito.anyCollection()))
				.thenReturn(1L);

		// when
		BookEntity result = bookDao.save(book);
		// then
		Assert.assertNotNull(result.getId());
		Assert.assertEquals(1L, result.getId().longValue());
	}

	@Test(expected = BookNotNullIdException.class)
	public void testShouldThrowExceptionWhenBookIdNotNull() {
		// given
		BookEntity book = new BookEntity(2L, "title", new ArrayList<AuthorTo>(
				Arrays.asList(new AuthorTo(1L, "author", "author"))));
		// when
		BookEntity result = bookDao.save(book);
		// then
		fail("test should throw BookNotNullIdException");
	}

}
