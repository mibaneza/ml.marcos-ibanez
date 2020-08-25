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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ml.marcosibanez.rest.domain.Card;
import ml.marcosibanez.rest.service.CardService;
import ml.marcosibanez.rest.service.exception.MensajeException;
import ml.marcosibanez.rest.web.rest.response.MensajeResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/")
public class CardResource {
    private static final  String SUCCES = "Succes";
	private static final  String OK = "OK";
	@Autowired
	CardService cardService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "security/card", produces = MediaType.APPLICATION_JSON_VALUE)
	public MensajeResponse<String> createPostHorary(@Valid @RequestBody Card cardDto) throws MensajeException {
		return new MensajeResponse<>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				cardService.createCard(cardDto));
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "web/card", produces = MediaType.APPLICATION_JSON_VALUE)
	public MensajeResponse<List<Card>> readGetHorary() throws MensajeException {
		return new MensajeResponse<>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				cardService.findAllCard());
	}
	
	@PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "security/card", produces = MediaType.APPLICATION_JSON_VALUE)
	public MensajeResponse<Card> updatePutHorary(@Valid @RequestBody Card cardDto) throws MensajeException {
		return new MensajeResponse<>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				cardService.updateCard(cardDto));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = "security/card", produces = MediaType.APPLICATION_JSON_VALUE)
	public MensajeResponse<String> deleteCard(@RequestParam Long id) throws MensajeException {
		return new MensajeResponse<>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				cardService.deleteCard(id));
 }
}