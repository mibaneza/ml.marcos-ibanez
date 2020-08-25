package ml.marcosibanez.rest.web.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ml.marcosibanez.rest.service.UserService;
import ml.marcosibanez.rest.service.dto.JwtDTO;
import ml.marcosibanez.rest.service.dto.UserDTO;
import ml.marcosibanez.rest.service.exception.MensajeException;
import ml.marcosibanez.rest.web.rest.response.MensajeResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/")
public class UserResource {

	private static final String SUCCES = "Succes";
	private static final String OK = "OK";

	@Autowired
	UserService userService;

	@PostMapping(value = "web/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public MensajeResponse<JwtDTO> loginUser(@Valid @RequestBody UserDTO userDTO) throws MensajeException {
		return new MensajeResponse<>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				userService.getJwtByLogin(userDTO));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "security/create_user", produces = MediaType.APPLICATION_JSON_VALUE)
	public MensajeResponse<String> registerUser(@Valid @RequestBody UserDTO userDTO) throws MensajeException {
		return new MensajeResponse<>(SUCCES, String.valueOf(HttpStatus.OK), OK, userService.registerUser(userDTO));
	}

}
