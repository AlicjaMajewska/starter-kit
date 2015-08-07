package pl.spring.demo.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;

import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import pl.spring.demo.service.BookService;
import pl.spring.demo.service.impl.BookServiceImpl;
import pl.spring.demo.to.BookTo;

public class BookControllerTest {

	@Mock
	private BookService bookService;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/templates/");
		viewResolver.setSuffix(".html");
		BookController bookController = new BookController();
		Whitebox.setInternalState(bookController, "bookService", bookService);
		mockMvc = MockMvcBuilders.standaloneSetup(bookController)
				.setViewResolvers(viewResolver).build();
	}

	@Test
	public void testShouldRemoveBookButtonOption() throws Exception {
		// given
		BookTo bookTo = new BookTo(2L, "Do usuniecia", "Zbigniew Nowak");
		Mockito.doNothing().when(bookService).removeBook(Mockito.anyLong());
		Mockito.when(bookService.findBookById(Mockito.anyLong())).thenReturn(
				bookTo);
		// when
		ResultActions response = this.mockMvc.perform(post("/books").param(
				"id", "2"));
		// then
		response.andExpect(view().name("redirect:/removed"))
				.andExpect(redirectedUrl("/removed"))
				.andExpect(flash().attributeExists("title"))
				.andExpect(flash().attributeCount(1))
				.andExpect(flash().attribute("title", "Do usuniecia"));
	}
}
