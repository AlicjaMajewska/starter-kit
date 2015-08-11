package pl.spring.demo.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.entity.LibraryEntity;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonRepositoryTest-context.xml")
public class LibraryRepositoryTest {

	@Autowired
	private LibraryRepository libraryRepository;

	@Test
	public void testShouldFindOneLibraryByNameBibliot() {
		// given
		String libraryPrefix = "Bibliot";
		String fullName = "Biblioteka Miejska";
		// when
		List<LibraryEntity> findLibraryByName = libraryRepository
				.findLibraryByName(libraryPrefix);
		// then
		assertFalse("List of found libraries is not empty",
				findLibraryByName.isEmpty());

		boolean containsBibliotekaMiejska = false;
		for (LibraryEntity libraryEntity : findLibraryByName) {
			if (fullName.equals(libraryEntity.getName())) {
				containsBibliotekaMiejska = true;
			}
		}
		assertTrue("List of found libraries contains " + fullName + " library", containsBibliotekaMiejska);
	}

	@Test
	public void testShouldFindTwoLibrariesByNameK() {
		// given
		String libraryPrefix = "K";
		String fullName1 = "Książekczki do poduszczki";
		String fullName2 = "Kolejna biblioteka";
		// when
		List<LibraryEntity> findLibraryByName = libraryRepository
				.findLibraryByName(libraryPrefix);
		// then
		assertTrue("List of found libraries has two elemetns",
				findLibraryByName.size() == 2);

		boolean contains1 = false;
		boolean contains2 = false;
		for (LibraryEntity libraryEntity : findLibraryByName) {
			if (fullName1.equals(libraryEntity.getName())) {
				contains1 = true;
			}
			if (fullName2.equals(libraryEntity.getName())) {
				contains2 = true;
			}
		}
		assertTrue("List of found libraries contains " + fullName1 +" and " + fullName2 + " library", contains1 && contains2);
	}
}

