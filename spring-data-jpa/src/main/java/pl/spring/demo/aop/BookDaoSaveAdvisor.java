package pl.spring.demo.aop;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

import pl.spring.demo.dao.impl.BookDaoImpl;
import pl.spring.demo.to.BookTo;
import pl.spring.demo.to.IdAware;
import pl.spring.demo.annotation.SaveId;

import java.lang.reflect.Method;
@Component
public class BookDaoSaveAdvisor implements MethodBeforeAdvice {

	@Override
	public void before(Method method, Object[] objects, Object o)
			throws Throwable {

		if (hasAnnotation(method, o, SaveId.class)) {
			saveId(objects, o);
		}
	}

	private void saveId(Object[] objects, Object o) {
		BookTo book = (BookTo) objects[0];

		if (book.getId() == null) {
			book.setId(((BookDaoImpl) o).getNextId());
		}
	}

	private boolean hasAnnotation(Method method, Object o, Class annotationClazz) {
		return  method.getAnnotation(annotationClazz) != null;
	}
}
