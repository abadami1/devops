/**
 * 
 */
package exp.devops.sisd.service;

import java.util.List;

import exp.devops.sisd.model.Event;

/**
 * @author abadami
 *
 */
public interface EventPersistenceService extends SimplePersistence<Event> {

	Event create(Event e);

	Event getById(Long id);

	Event getMatch(Event stub);

	List<Event> getAll();

	Event update(Event e);

	void delete(Event e);

}
