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

import ml.marcosibanez.rest.domain.Proyect;
import ml.marcosibanez.rest.service.ProyectService;
import ml.marcosibanez.rest.service.exception.MensajeException;
import ml.marcosibanez.rest.web.rest.response.MensajeResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/")
public class ProyectResource {
    
    private final static String succes = "Succes";
	private final static String ok = "OK";
	@Autowired
	ProyectService proyectService;
	
	@PostMapping(value = "proyect", produces = MediaType.APPLICATION_JSON_VALUE)
	public MensajeResponse<String> createPostHorary(@Valid @RequestBody Proyect proyectDto) throws MensajeException {
		return new MensajeResponse<>(succes, String.valueOf(HttpStatus.OK), ok,
				proyectService.createProyect(proyectDto));
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "proyect", produces = MediaType.APPLICATION_JSON_VALUE)
	public MensajeResponse<List<Proyect>> readGetHorary() throws MensajeException {
		return new MensajeResponse<>(succes, String.valueOf(HttpStatus.OK), ok,
				proyectService.findAllProyect());
	}
    @PutMapping(value = "proyect", produces = MediaType.APPLICATION_JSON_VALUE)
	public MensajeResponse<Proyect> updatePutHorary(@Valid @RequestBody Proyect proyectDto) throws MensajeException {
		return new MensajeResponse<>(succes, String.valueOf(HttpStatus.OK), ok,
				proyectService.updateProyect(proyectDto));
	}	
/*	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = "proyect", produces = MediaType.APPLICATION_JSON_VALUE)
	public MensajeResponse<String> deleteHorary(@RequestParam Long id) throws MensajeException {
		return new MensajeResponse<>(succes, String.valueOf(HttpStatus.OK), ok,
				proyectService.deleteProyect(id));
	}
*/
}