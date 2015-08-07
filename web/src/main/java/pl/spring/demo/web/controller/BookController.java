
package pl.spring.demo.web.controller;

import org.eclipse.jetty.server.session.HashSessionManager;
import org.eclipse.jetty.server.session.SessionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

import java.util.List;
import java.util.Map;

@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	 HashSessionManager manager = new HashSessionManager();
     SessionHandler sessions = new SessionHandler(manager);

	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public String bookList(Map<String, Object> params) {
		final List<BookTo> allBooks = bookService.findAllBooks();
		params.put("books", allBooks);
		return "bookList";
	}
	@RequestMapping(value = "/removed", method = RequestMethod.GET)
	public String removed() {
			return "removed";
	}

	@RequestMapping(value = "/books", method = RequestMethod.POST)
    public String bookRemoved(@RequestParam(value="id") long id,  RedirectAttributes redirectAttributes) {
		BookTo bookTo = bookService.findBookById(id);
		redirectAttributes.addFlashAttribute("title", bookTo.getTitle());
    	bookService.removeBook(id);
    	return "redirect:/removed";
    }
}
