package pl.spring.demo.service;

public interface LibraryService {

	void deleteLibrary(long id);

	public long numberOfLibraries();

	public boolean libraryExist(long id);

}
