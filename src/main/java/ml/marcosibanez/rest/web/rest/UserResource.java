package ml.marcosibanez.rest.web.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ml.marcosibanez.rest.service.UserService;
import ml.marcosibanez.rest.service.dto.JwtDTO;
import ml.marcosibanez.rest.service.dto.UserDTO;
import ml.marcosibanez.rest.service.exception.MensajeException;
import ml.marcosibanez.rest.web.rest.response.MensajeResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/web/")
public class UserResource {

	private final static String succes = "Succes";
	private final static String ok = "OK";

	@Autowired
	UserService userService;

	@RequestMapping(value = "login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public MensajeResponse<JwtDTO> loginUser(@Valid @RequestBody UserDTO userDTO) throws MensajeException {
		return new MensajeResponse<JwtDTO>(succes, String.valueOf(HttpStatus.OK), ok,
				userService.getJwtByLogin(userDTO));
	}

	// @PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "create_user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public MensajeResponse<String> registerUser(@Valid @RequestBody UserDTO userDTO) throws MensajeException {
		return new MensajeResponse<>(succes, String.valueOf(HttpStatus.OK), ok, userService.registerUser(userDTO));
	}

}
