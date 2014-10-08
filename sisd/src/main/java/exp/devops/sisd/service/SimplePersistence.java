/**
 * 
 */
package exp.devops.sisd.service;

import java.util.List;

/**
 * @author abadami
 *
 */
public interface SimplePersistence<E> {

	E create(E e);
	
	E getById(Long id);
	
	E getMatch(E stub);
	
	List<E> getAll();
	
	E update(E e);
	
	void delete(E e);
}
