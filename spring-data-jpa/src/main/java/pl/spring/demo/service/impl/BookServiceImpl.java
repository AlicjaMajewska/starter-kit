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
		if(book == null){
		System.out.println("Skad dostalismy tego nulla?????");
		}
		if(bookMapper == null){
			System.out.println("Dlaczego mapper jest nullem?");
		}
		if(bookDao == null){
			System.out.println("Dlaczego bookDao jest nullem?");
		}
		try {
			System.err.println("nr 1");
			System.out.println("Book jako argument: " + book);
			BookEntity convertToBookEntity = bookMapper.convertToBookEntity(book);
			System.err.println("nr 2");
			BookEntity save = bookDao.save(convertToBookEntity);
			System.err.println("nr 3");
			BookTo convertToBookTo = bookMapper.convertToBookTo(save);
			System.err.println("nr 4");
			return convertToBookTo;
//			return bookMapper.convertToBookTo(bookDao.save(bookMapper
//					.convertToBookEntity(book)));
		} catch (NullPointerException e) {
			System.out.println("Wyjatek w saveBook: " + e.getMessage());
		}
		return null;
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}
}
