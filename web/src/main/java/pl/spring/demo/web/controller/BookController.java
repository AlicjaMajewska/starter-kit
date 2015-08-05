package pl.spring.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

import java.util.List;
import java.util.Map;

@Controller
public class BookController {
	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public String bookList(Map<String, Object> params) {
		final List<BookTo> allBooks = bookService.findAllBooks();
		params.put("books", allBooks);
		return "bookList";
	}

	@RequestMapping(value = "/bookRemoved", method = RequestMethod.GET)
    public String bookRemoved(@RequestParam(value="id") long id, Map<String, Object> parameters) {
		BookTo bookToRemove = bookService.findBookById(id);
    	parameters.put("title", bookToRemove.getTitle());
    	bookService.removeBook(bookToRemove);
    	return "bookRemoved";
    }
	// @RequestMapping(value = "/books", method = RequestMethod.GET)
	// public String removeBook() {
	// System.out.println("Wszedlem az tutaj");
	// return "bookList";
	// }
	//
	// @RequestMapping(value = "/bookToBook", method = RequestMethod.GET)
	// public String bookListAll(Map<String, Object> params) {
	// final List<BookTo> allBooks = bookService.findAllBooks();
	// params.put("tertis", allBooks);
	// params.put("cub", "I'm a cub");
	//
	// return "bookList";
	// }
}
