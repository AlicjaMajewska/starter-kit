package pl.spring.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "BOOK")
public class BookEntity implements Serializable {
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
}
