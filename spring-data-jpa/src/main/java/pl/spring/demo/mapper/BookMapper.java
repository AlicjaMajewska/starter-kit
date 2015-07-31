package pl.spring.demo.mapper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.mockito.internal.util.reflection.LenientCopyTool;
import org.springframework.stereotype.Component;

import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookEntity;
import pl.spring.demo.to.BookTo;

@Component
public class BookMapper {

	public BookTo convertToBookTo(BookEntity bookEntity) {
		String authors = new String("");
		for (int i = 0; i < bookEntity.getAuthors().size(); ++i) {
			authors += (bookEntity.getAuthors().get(i).getFirstName() + " " + bookEntity
					.getAuthors().get(i).getLastName());
			if (i < bookEntity.getAuthors().size() - 1) {
				authors += ", ";
			}
		}
		if(bookEntity.getAuthors().size() == 0){
			authors = null;
		}
		return new BookTo(bookEntity.getId(), bookEntity.getTitle(), authors);
	}

	public BookEntity convertToBookEntity(BookTo bookTo) {
		return new BookEntity(bookTo.getId(), bookTo.getTitle(),
				getAuthorListFormString(bookTo.getAuthors()));
	}

	public List<AuthorTo> getAuthorListFormString(String authors) {
		List<AuthorTo> authorsList = new ArrayList<AuthorTo>();
		if (authors != null) {
			String[] splitAuthors = authors.split(", ");
			long id = 0;
			for (String author : splitAuthors) {
				++id;
				AuthorTo authorTo = getAuthorFromString(author);
				if (authorTo != null) {
					authorTo.setId(id);
					authorsList.add(authorTo);
				}
			}
		}
		return authorsList;

	}

	public AuthorTo getAuthorFromString(String author) {
		String[] authorNames = author.split(" ");
		if (authorNames.length != 0) {
			AuthorTo authorTo = new AuthorTo();
			authorTo.setLastName(authorNames[authorNames.length - 1]);
			String firstName = new String("");
			for (int i = 0; i < authorNames.length - 1; ++i) {
				firstName += authorNames[i];
			}
			authorTo.setFirstName(firstName);
			return authorTo;
		}
		return null;
	}

}
