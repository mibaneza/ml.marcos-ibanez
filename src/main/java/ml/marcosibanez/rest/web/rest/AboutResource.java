package ml.marcosibanez.rest.web.rest;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ml.marcosibanez.rest.domain.About;
import ml.marcosibanez.rest.service.AboutService;
import ml.marcosibanez.rest.service.exception.MensajeException;
import ml.marcosibanez.rest.web.rest.response.MensajeResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/")
public class AboutResource {
    
    private static final  String SUCCES = "Succes";
	private static final  String OK = "OK";
	@Autowired
	AboutService aboutService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "security/about", produces = MediaType.APPLICATION_JSON_VALUE)
	public MensajeResponse<String> createPostHorary(@Valid @RequestBody About aboutDto) throws MensajeException {
		return new MensajeResponse<>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				aboutService.createAbout(aboutDto));
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "web/about", produces = MediaType.APPLICATION_JSON_VALUE)
	public MensajeResponse<About> readGetHorary() throws MensajeException {
		return new MensajeResponse<>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				aboutService.findOneAbout());
	}
	
	@PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "security/about", produces = MediaType.APPLICATION_JSON_VALUE)
	public MensajeResponse<About> updatePutHorary(@Valid @RequestBody About aboutDto) throws MensajeException {
		return new MensajeResponse<>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				aboutService.updateAbout(aboutDto));
	}	
	
	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = "security/about", produces = MediaType.APPLICATION_JSON_VALUE)
	public MensajeResponse<String> deleteHorary(@RequestParam Long id) throws MensajeException {
		return new MensajeResponse<>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				aboutService.deleteAbout(id));
	}

}