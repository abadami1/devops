/**
 * 
 */
package exp.devops.sisd.service;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import exp.devops.sisd.model.Event;

/**
 * @author abadami
 *
 */
@Service("eventPersistenceService")
public class EventPersistenceImpl implements EventPersistenceService {

	private static Logger logger = Logger
			.getLogger(EventPersistenceImpl.class);

	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Event create(Event e) {
		logger.debug("Adding new event");

		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();

		// Save
		session.save(e);

		// Return
		return e;
	}

	@Transactional(readOnly=true)
	public Event getById(Long id) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();

		// Retrieve existing event first
		Event event = (Event) session.get(Event.class, id);

		return event;
	}

	@Transactional(readOnly=true)
	public Event getMatch(Event stub) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly=true)
	public List<Event> getAll() {
		logger.debug("Retrieving all events");

		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();

		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM  Event");

		// Retrieve all
		return  query.list();
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public Event update(Event e) {
		logger.debug("Editing existing event");

		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();

		// Retrieve existing event via id
		Event existingEvent = (Event) session.get(Event.class, e.getId());

		// Assign updated values to this event
		existingEvent.setAddress(e.getAddress());
		existingEvent.setEndTime(e.getEndTime());
		existingEvent.setName(e.getName());
		existingEvent.setOrganizer(e.getOrganizer());
		existingEvent.setStartTime(e.getStartTime());

		// Save updates
		session.save(existingEvent);
		
		// Return
		return existingEvent;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(Event e) {
		logger.debug("Deleting existing event");

		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();

		// Retrieve existing event first
		Event event = (Event) session.get(Event.class, e.getId());

		// Delete 
		session.delete(event);
	}




}
