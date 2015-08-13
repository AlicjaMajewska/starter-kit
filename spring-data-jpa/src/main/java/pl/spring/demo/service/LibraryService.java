package pl.spring.demo.service;

import pl.spring.demo.entity.LibraryEntity;

public interface LibraryService {

	void deleteLibrary(long id);

	public long numberOfLibraries();

	public boolean libraryExist(long id);

	void save(LibraryEntity libraryEntity);

}
