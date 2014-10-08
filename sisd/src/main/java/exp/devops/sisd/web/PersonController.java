package exp.devops.sisd.web;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import exp.devops.sisd.model.Person;
import exp.devops.sisd.model.WebDataResponse;
import exp.devops.sisd.model.WebResponse;
import exp.devops.sisd.service.PersonService;


/**
 * Handles and retrieves person request
 */
@Controller
@RequestMapping("/personController")
public class PersonController extends ApplicationController {

	private static Logger logger = Logger.getLogger(PersonController.class);
	
	@Resource(name="personService")
	private PersonService personService;
	
	@RequestMapping(
			value="/list", 
			method=RequestMethod.GET,  
			headers = ApplicationController.HTTP_ACCEPT_JSON_HEADER,
			produces = ApplicationController.HTTP_RESPONSE_JSON_HEADER)
	public @ResponseBody WebResponse getAllEvents(){
		WebResponse resp = new WebDataResponse();
		try{
			logger.debug("Received request to show all persons");
	    	
	    	// Retrieve all persons by delegating the call to PersonService
	    	List<Person> persons = personService.getAll();
	    	
			handleSuccess(resp, persons);
		} catch(Exception e){
			handleException(resp, e);
		}
		return resp;
	}
}
