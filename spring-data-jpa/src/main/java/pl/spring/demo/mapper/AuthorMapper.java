package pl.spring.demo.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import pl.spring.demo.entity.AuthorEntity;
import pl.spring.demo.to.AuthorTo;

public class AuthorMapper {

	public static AuthorTo map(AuthorEntity authorEntity) {
		if (authorEntity != null) {
			return new AuthorTo(authorEntity.getFirstName(),
					authorEntity.getLastName(), authorEntity.getId());
		}
		return null;
	}
	public static AuthorEntity map(AuthorTo authorTo) {
		if (authorTo != null) {
			return new AuthorEntity(authorTo.getId(),authorTo.getFirstName(),
					authorTo.getLastName());
		}
		return null;
	}
	
	public static List<AuthorTo> mapAuthors2To(Collection<AuthorEntity> authors) {
        if (!authors.isEmpty()) {
        	List<AuthorTo> authorsTo = new ArrayList<AuthorTo>();
        	for (AuthorEntity authorEntity : authors) {
        		AuthorTo authorTo = map(authorEntity);
        		authorsTo.add(authorTo);
			}
        	return authorsTo;
        }
        return null;
    }
	public static List<AuthorEntity> mapAuthors2Entity(Collection<AuthorTo> authors) {
		if (!authors.isEmpty()) {
			List<AuthorEntity> authorsEntity = new ArrayList<AuthorEntity>();
			for (AuthorTo authorTo : authors) {
				AuthorEntity authorEntity = map(authorTo);
				authorsEntity.add(authorEntity);
			}
			return authorsEntity;
		}
		return null;
	}

}
