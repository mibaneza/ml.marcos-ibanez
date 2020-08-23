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
    
    private final static String succes = "Succes";
	private final static String ok = "OK";
	@Autowired
	AboutService aboutService;
	
	@PostMapping(value = "about", produces = MediaType.APPLICATION_JSON_VALUE)
	public MensajeResponse<String> createPostHorary(@Valid @RequestBody About aboutDto) throws MensajeException {
		return new MensajeResponse<>(succes, String.valueOf(HttpStatus.OK), ok,
				aboutService.createAbout(aboutDto));
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "about", produces = MediaType.APPLICATION_JSON_VALUE)
	public MensajeResponse<About> readGetHorary() throws MensajeException {
		return new MensajeResponse<>(succes, String.valueOf(HttpStatus.OK), ok,
				aboutService.findOneAbout());
	}
    @PutMapping(value = "about", produces = MediaType.APPLICATION_JSON_VALUE)
	public MensajeResponse<About> updatePutHorary(@Valid @RequestBody About aboutDto) throws MensajeException {
		return new MensajeResponse<>(succes, String.valueOf(HttpStatus.OK), ok,
				aboutService.updateAbout(aboutDto));
	}	
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = "about", produces = MediaType.APPLICATION_JSON_VALUE)
	public MensajeResponse<String> deleteHorary(@RequestParam Long id) throws MensajeException {
		return new MensajeResponse<>(succes, String.valueOf(HttpStatus.OK), ok,
				aboutService.deleteAbout(id));
	}

}