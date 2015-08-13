package pl.spring.demo.searchcriteria;

import java.util.List;
import pl.spring.demo.entity.BookEntity;


public interface UsingQueryDSLBookRepository  {

	 public List<BookEntity> findBookByCriteria(BookSearchCriteria bookSearchCriteria);
	
	
}
