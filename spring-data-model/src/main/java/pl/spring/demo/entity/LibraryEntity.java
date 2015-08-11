package pl.spring.demo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "LIBRARY")
public class LibraryEntity  {

	@Id
	private long id;

	@Column(name = "name", length = 50, nullable = false)
	private String name;

	@OneToMany(mappedBy = "libraryEntity", cascade = CascadeType.REMOVE)
	private Set<BookEntity> books;

	// public void save(BookEntity bookEntity){
	// // zadbac o id?
	// books.add(bookEntity);
	// }
	// public void remove(BookEntity bookEntity){
	// if(books.contains(bookEntity)){
	// books.remove(bookEntity);
	// }
	// }
	public LibraryEntity(Long id, String name, HashSet<BookEntity> books) {
		this.id = id;
		this.name = name;
		this.books = books;
	}

	// for hibernate
	protected LibraryEntity() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	 public Set<BookEntity> getBooks() {
	 return books;
	 }
	
	 public void setBooks(Set<BookEntity> books) {
	 this.books = books;
	 }

}
