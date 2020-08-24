package ml.marcosibanez.rest.web.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ml.marcosibanez.rest.domain.Contact;
import ml.marcosibanez.rest.service.ContactService;
import ml.marcosibanez.rest.service.dto.ContactDTO;
import ml.marcosibanez.rest.service.exception.MensajeException;
import ml.marcosibanez.rest.web.rest.response.MensajeResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/web/")
public class ContactResource {
	@Autowired
	ContactService contactService;
	
	@PostMapping(value = "contact", produces = MediaType.APPLICATION_JSON_VALUE)
	public MensajeResponse<String> createPostHorary(@Valid @RequestBody ContactDTO contactDto) throws MensajeException {
		return new MensajeResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
				contactService.createContact(contactDto));
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "contact", produces = MediaType.APPLICATION_JSON_VALUE)
	public MensajeResponse<List<Contact>> readGetHorary() throws MensajeException {
		return new MensajeResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
				contactService.findAllContact());
	}
		
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = "contact", produces = MediaType.APPLICATION_JSON_VALUE)
	public MensajeResponse<String> deleteHorary(@RequestParam Long id) throws MensajeException {
		return new MensajeResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
				contactService.deleteContact(id));
	}

}
