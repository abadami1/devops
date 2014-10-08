/**
 * 
 */
package exp.devops.sisd.web;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import exp.devops.sisd.model.Address;
import exp.devops.sisd.model.Event;
import exp.devops.sisd.model.Person;
import exp.devops.sisd.model.WebDataResponse;
import exp.devops.sisd.model.WebResponse;
import exp.devops.sisd.service.EventPersistenceService;

/**
 * @author abadami
 *
 */
@Controller
@RequestMapping("/eventController")
public class EventController extends ApplicationController {

	private static final Logger logger = 
			LoggerFactory.getLogger(EventController.class);

	private EventPersistenceService eventPersistenceService;

	public EventController() {
		logger.info("created event controller");
	}

	@Autowired
	public void setEventPersistenceService(
			EventPersistenceService eventPersistenceService) {
		this.eventPersistenceService = eventPersistenceService;
	}

	@RequestMapping(
			value="/", 
			method=RequestMethod.GET,  
			headers = ApplicationController.HTTP_ACCEPT_JSON_HEADER,
			produces = ApplicationController.HTTP_RESPONSE_JSON_HEADER)
	public @ResponseBody WebResponse getAllEvents(){
		WebResponse resp = new WebDataResponse();
		try{
			Collection<Event> list = eventPersistenceService.getAll();
			handleSuccess(resp, list);
		} catch(Exception e){
			handleException(resp, e);
		}
		return resp;
	}

	@RequestMapping(
			value="/hi", 
			method=RequestMethod.GET,  
			headers = ApplicationController.HTTP_ACCEPT_JSON_HEADER,
			produces = ApplicationController.HTTP_RESPONSE_JSON_HEADER)
	public @ResponseBody WebResponse hi(){
		WebResponse resp = new WebDataResponse();
		try{
			Event event = new Event();
			event.setName("Happy Hour");
			Address address = new Address();
			address.setCity("Springfield");
			address.setCountry("GUSA");
			address.setState("CN");
			address.setStreet1("21 Main Street");
			address.setStreet2("");
			address.setZip("54321");
			event.setAddress(address);
			event.setEndTime(new Date());
			Person jdoe = new Person();
			jdoe.setEmail("jdoe@mail.com");
			jdoe.setFirstName("John");
			jdoe.setLastName("Doe");
			event.setOrganizer(jdoe);
			event.setStartTime(new Date());

			event = eventPersistenceService.create(event);

			handleSuccess(resp, "Persisted " + event.toString());
		} catch(Exception e){
			handleException(resp, e);
		}
		return resp;
	}

	@RequestMapping(
			value="/{id}", 
			method=RequestMethod.DELETE,  
			produces = ApplicationController.HTTP_RESPONSE_JSON_HEADER)
	public @ResponseBody WebResponse deleteEvent(
			@PathVariable("id") Long id){
		WebResponse resp = new WebDataResponse();
		try{
			Event eventToDelete = eventPersistenceService.getById(id);
			eventPersistenceService.delete(eventToDelete);
			handleSuccess(resp, true);
		} catch(Exception e){
			handleException(resp, e);
		}
		return resp;
	}

	@RequestMapping(
			value="/", 
			method=RequestMethod.POST,  
			headers = ApplicationController.HTTP_ACCEPT_JSON_HEADER,
			produces = ApplicationController.HTTP_RESPONSE_JSON_HEADER)	
	public @ResponseBody WebResponse createEvent(
			@RequestBody Event systemPermission){
		WebResponse resp = new WebDataResponse();
		try{
			Event r = eventPersistenceService.create(systemPermission);
			handleSuccess(resp, r);
		} catch(Exception e){
			handleException(resp, e);
		}
		return resp;
	}

	@RequestMapping(
			value="/", 
			method=RequestMethod.PUT,  
			headers = ApplicationController.HTTP_ACCEPT_JSON_HEADER,
			produces = ApplicationController.HTTP_RESPONSE_JSON_HEADER)
	public @ResponseBody WebResponse updateEvent(
			@RequestBody Event systemPermission){
		WebResponse resp = new WebDataResponse();
		try{
			Event r = eventPersistenceService.update(systemPermission);
			handleSuccess(resp, r);
		} catch(Exception e){
			handleException(resp, e);
		}
		return resp;
	}

	/**
	 * ************************************************************************
	 * 
	 * JSP Views Controllers -- Used only for internal testing.
	 * 
	 * ************************************************************************
	 */

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat(Event.TIME_FORMAT);
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	/**
	 * Handles and retrieves all events and show it in a JSP page
	 * 
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/events", method = RequestMethod.GET)
	public String getEvents(Model model) {

		logger.debug("Received request to show all events");

		// Retrieve all events by delegating the call to EventService
		List<Event> events = eventPersistenceService.getAll();

		// Attach events to the Model
		model.addAttribute("events", events);

		// This will resolve to /WEB-INF/jsp/eventspage.jsp
		return "eventspage";
	}

	/**
	 * Retrieves the add page
	 * 
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/events/add", method = RequestMethod.GET)
	public String getAdd(Model model) {
		logger.debug("Received request to show add page");

		// Create new Event and add to model
		// This is the formBackingOBject
		model.addAttribute("eventAttribute", new Event());

		// This will resolve to /WEB-INF/jsp/addpage.jsp
		return "addpage";
	}

	/**
	 * Adds a new event by delegating the processing to EventService.
	 * Displays a confirmation JSP page
	 * 
	 * @return  the name of the JSP page
	 */
	@RequestMapping(value = "/events/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("eventAttribute") Event event) {
		logger.debug("Received request to add new event");

		// The "eventAttribute" model has been passed to the controller from the JSP
		// We use the name "eventAttribute" because the JSP uses that name

		// Call EventService to do the actual adding
		eventPersistenceService.create(event);

		// This will resolve to /WEB-INF/jsp/addedpage.jsp
		return "addedpage";
	}

	/**
	 * Deletes an existing event by delegating the processing to EventService.
	 * Displays a confirmation JSP page
	 * 
	 * @return  the name of the JSP page
	 */
	@RequestMapping(value = "/events/delete", method = RequestMethod.GET)
	public String delete(@RequestParam(value="id", required=true) Long id, 
			Model model) {

		logger.debug("Received request to delete existing event");

		// Call EventService to do the actual deleting
		Event eventToDelete = eventPersistenceService.getById(id);
		eventPersistenceService.delete(eventToDelete);

		// Add id reference to Model
		model.addAttribute("id", id);

		// This will resolve to /WEB-INF/jsp/deletedpage.jsp
		return "deletedpage";
	}

	/**
	 * Retrieves the edit page
	 * 
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/events/edit", method = RequestMethod.GET)
	public String getEdit(@RequestParam(value="id", required=true) Long id,  
			Model model) {
		logger.debug("Received request to show edit page");

		// Retrieve existing Event and add to model
		// This is the formBackingOBject
		model.addAttribute("eventAttribute", eventPersistenceService.getById(id));

		// This will resolve to /WEB-INF/jsp/editpage.jsp
		return "editpage";
	}

	/**
	 * Edits an existing event by delegating the processing to EventService.
	 * Displays a confirmation JSP page
	 * 
	 * @return  the name of the JSP page
	 */
	@RequestMapping(value = "/events/edit", method = RequestMethod.POST)
	public String saveEdit(@ModelAttribute("eventAttribute") Event event, 
			@RequestParam(value="id", required=true) Long id, 
			Model model) {
		logger.debug("Received request to update event");

		// The "eventAttribute" model has been passed to the controller from the JSP
		// We use the name "eventAttribute" because the JSP uses that name

		// We manually assign the id because we disabled it in the JSP page
		// When a field is disabled it will not be included in the ModelAttribute
		event.setId(id);

		// Delegate to EventService for editing
		eventPersistenceService.update(event);

		// Add id reference to Model
		model.addAttribute("id", id);

		// This will resolve to /WEB-INF/jsp/editedpage.jsp
		return "editedpage";
	}
}
