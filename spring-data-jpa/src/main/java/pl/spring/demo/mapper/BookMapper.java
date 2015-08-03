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
		String authors;
		if (noAuthors(bookEntity)) {
			authors = null;
		} else {
			StringBuilder stringBuilder = new StringBuilder();
			for (int i = 0; i < bookEntity.getAuthors().size(); ++i) {
				buildAuthor(bookEntity, stringBuilder, i);
			}
			stringBuilder.delete(stringBuilder.length() - 2,
					stringBuilder.length());

			authors = stringBuilder.toString();
		}

		return new BookTo(bookEntity.getId(), bookEntity.getTitle(), authors);
	}

	private void buildAuthor(BookEntity bookEntity,
			StringBuilder stringBuilder, int i) {
		stringBuilder.append(bookEntity.getAuthors().get(i)
				.getFirstName()
				+ " "
				+ bookEntity.getAuthors().get(i).getLastName()
				+ ", ");
	}

	private boolean noAuthors(BookEntity bookEntity) {
		return bookEntity.getAuthors() == null
				|| bookEntity.getAuthors().isEmpty();
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
			StringBuilder stringBuilder = new StringBuilder();
			for (int i = 0; i < authorNames.length - 1; ++i) {
				stringBuilder.append(authorNames[i]);
			}
			authorTo.setFirstName(stringBuilder.toString());
			return authorTo;
		}
		return null;
	}

}
