package pl.spring.demo.service.impl;

import pl.spring.demo.dao.BookDao;
import pl.spring.demo.mapper.BookMapper;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookEntity;
import pl.spring.demo.to.BookTo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookDao bookDao;
	@Autowired
	private BookMapper bookMapper;

	@Override
	public List<BookTo> findAllBooks() {
		List<BookTo> bookToAllBooks = new ArrayList<BookTo>();
		for (BookEntity bookEntity : bookDao.findAll()) {
			bookToAllBooks.add(bookMapper.convertToBookTo(bookEntity));
		}
		return bookToAllBooks;
	}

	@Override
	public List<BookTo> findBooksByTitle(String title) {
		List<BookTo> bookToFindByTitle = new ArrayList<BookTo>();
		for (BookEntity bookEntity : bookDao.findBookByTitle(title)) {
			bookToFindByTitle.add(bookMapper.convertToBookTo(bookEntity));
		}
		return bookToFindByTitle;
	}

	@Override
	public List<BookTo> findBooksByAuthor(String author) {
		List<BookTo> bookToFindByAuthor = new ArrayList<BookTo>();
		for (BookEntity bookEntity : bookDao.findBooksByAuthor(author)) {
			bookToFindByAuthor.add(bookMapper.convertToBookTo(bookEntity));
		}
		return bookToFindByAuthor;
	}

	@Override
	public BookTo saveBook(BookTo book) {
		return bookMapper.convertToBookTo(bookDao.save(bookMapper
				.convertToBookEntity(book)));
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}
}
