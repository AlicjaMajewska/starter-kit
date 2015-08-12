package pl.spring.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class LibraryServiceTest {

	@Autowired
	LibraryService libraryService;

	@Test
	public void testShouldRemoveLibrary2() {
		// given
		long countBeforeRemoval = libraryService.numberOfLibraries();
		// when
		libraryService.deleteLibrary(2L);
		long countAfterRemoval = libraryService.numberOfLibraries();
		// then
		assertTrue("One Library has been removed",
				countBeforeRemoval - 1 == countAfterRemoval);
		assertFalse(libraryService.libraryExist(2L));
	}

}
