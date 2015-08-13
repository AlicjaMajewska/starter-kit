package pl.spring.demo.entity;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "BOOK")
public class BookEntity  {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false, length = 50)
	private String title;

	// tu jest fk - info do jakiej biblioteki nalezy
	@ManyToOne
	private LibraryEntity libraryEntity;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "BOOK_AUTHOR", joinColumns = { @JoinColumn(name = "BOOK_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "AUTHOR_ID", nullable = false, updatable = false) })
	private Set<AuthorEntity> authors = new HashSet<>();

	// for hibernate
	protected BookEntity() {
	}

	public BookEntity(Long id, String title) {
		this.id = id;
		this.title = title;
	}

	public long getLibraryId() {
		return libraryEntity.getId();
	}
	public String  getLibraryName() {
		return libraryEntity.getName();
	}

	public LibraryEntity getLibraryEntity() {
		return libraryEntity;
	}

	public void setLibraryEntity(LibraryEntity libraryEntity) {
		this.libraryEntity = libraryEntity;
	}

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

	public Set<AuthorEntity> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<AuthorEntity> authors) {
		this.authors = authors;
	}

	@Override
	public String toString() {
		return "BookEntity [id=" + id + ", title=" + title + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
