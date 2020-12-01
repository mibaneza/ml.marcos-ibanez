package ml.marcosibanez.rest.web.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ml.marcosibanez.rest.domain.Contact;
import ml.marcosibanez.rest.service.ContactService;
import ml.marcosibanez.rest.service.dto.ContactDTO;
import ml.marcosibanez.rest.service.exception.MensajeException;
import ml.marcosibanez.rest.web.rest.response.MensajeResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/")
public class ContactResource {
	private static final String SUCCES = "Succes";
	private static final String OK = "OK";
	
	@Autowired
	ContactService contactService;
	
	@PostMapping(value = "web/contact", produces = MediaType.APPLICATION_JSON_VALUE)
	public MensajeResponse<String> postContact(@Valid @RequestBody ContactDTO contactDto) throws MensajeException {
		return new MensajeResponse<>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				contactService.createContact(contactDto));
	}
	
	//@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "web/contact", produces = MediaType.APPLICATION_JSON_VALUE)
	public MensajeResponse<List<Contact>> getContact() throws MensajeException {
		return new MensajeResponse<>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				contactService.findAllContact());
	}
	
	//@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(value = "web/contact/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public MensajeResponse<String> putContact(@PathVariable(value="id")Long id,
	    @Valid @RequestBody ContactDTO contactDto
			) throws MensajeException {
		return new MensajeResponse<>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				contactService.updateContact(contactDto, id));
	}	
		
	//@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = "web/contact/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public MensajeResponse<String> deleteContact(@PathVariable(value="id")Long id) throws MensajeException {
		return new MensajeResponse<>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				contactService.deleteContact(id));
	}

}
