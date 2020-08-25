package ml.marcosibanez.rest.web.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ml.marcosibanez.rest.domain.ProyectD;
import ml.marcosibanez.rest.service.ProyectService;
import ml.marcosibanez.rest.service.exception.MensajeException;
import ml.marcosibanez.rest.web.rest.response.MensajeResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/")
public class ProyectResource {
    
    private static final String SUCCES = "Succes";
	private static final String OK = "OK";
	@Autowired
	ProyectService proyectService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "security/proyect", produces = MediaType.APPLICATION_JSON_VALUE)
	public MensajeResponse<String> createPostHorary(@Valid @RequestBody ProyectD proyectDto) throws MensajeException {
		return new MensajeResponse<>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				proyectService.createProyect(proyectDto));
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "web/proyect", produces = MediaType.APPLICATION_JSON_VALUE)
	public MensajeResponse<List<ProyectD>> readGetHorary() throws MensajeException {
		return new MensajeResponse<>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				proyectService.findAllProyect());
	}
	
	@PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "security/proyect", produces = MediaType.APPLICATION_JSON_VALUE)
	public MensajeResponse<ProyectD> updatePutHorary(@Valid @RequestBody ProyectD proyectDto) throws MensajeException {
		return new MensajeResponse<>(SUCCES, String.valueOf(HttpStatus.OK), OK,
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