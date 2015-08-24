package pl.spring.demo.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import pl.spring.demo.service.AuthorService;
import pl.spring.demo.to.AuthorTo;

import java.util.List;

@RestController
@RequestMapping(value = "/authors")
public class AuthorRestService {

	@Autowired
	private AuthorService authorService;

	@RequestMapping(value = "/authors-by-name", method = RequestMethod.GET)
	public List<AuthorTo> findAllAuthors() {
		return authorService.findAllAuthors();
	}

}
