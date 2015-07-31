package pl.spring.demo.to;

import java.util.List;

public class BookEntity implements IdAware {
	private Long id;
	private String title;
	private List<AuthorTo> authorsList;

	public BookEntity() {
	}

	public BookEntity(Long id, String title, List<AuthorTo> authorsList) {
		this.id = id;
		this.title = title;
		this.authorsList = authorsList;
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<AuthorTo> getAuthors() {
		return authorsList;
	}

	public void setAuthors(List<AuthorTo> authorsList) {
		this.authorsList = authorsList;
	}

	@Override
	public String toString() {
		return "BookEntity [id=" + id + ", title=" + title + ", authorsList="
				+ authorsList + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((authorsList == null) ? 0 : authorsList.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookEntity other = (BookEntity) obj;
		if (authorsList == null) {
			if (other.authorsList != null)
				return false;
		} else if (!authorsList.equals(other.authorsList))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
}
