package ml.marcosibanez.rest.service;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ml.marcosibanez.rest.security.jwt.JwtUtils;
import ml.marcosibanez.rest.domain.ERole;
import ml.marcosibanez.rest.domain.Role;
import ml.marcosibanez.rest.domain.User;
import ml.marcosibanez.rest.repository.RoleRepository;
import ml.marcosibanez.rest.repository.UserRepository;
import ml.marcosibanez.rest.service.dto.JwtDTO;
import ml.marcosibanez.rest.service.dto.UserDTO;
import ml.marcosibanez.rest.service.exception.InternalServerErrorException;
import ml.marcosibanez.rest.service.exception.MensajeException;
import ml.marcosibanez.rest.service.exception.NotFountException;

@Service
public class UserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	
	private final UserRepository userRepository;
	
	private final AuthenticationManager authenticationManager;

	private final PasswordEncoder passwordEncoder;
	
	private final RoleRepository roleRepository;

	private final JwtUtils jwtUtils;
	
	public UserService(
			AuthenticationManager authenticationManager,
			UserRepository userRepository,
			PasswordEncoder passwordEncoder,
			RoleRepository roleRepository,
			JwtUtils jwtUtils
			) {
        this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.roleRepository = roleRepository;
		this.jwtUtils = jwtUtils;
	}
	
	public JwtDTO getJwtByLogin(UserDTO userDTO) throws MensajeException {
	
		userRepository.findByUsername(
				userDTO.getUsername())
				.orElseThrow(() -> new NotFountException("SNOT-404-1", "El username no existe"));

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                		userDTO.getUsername(),
                		userDTO.getPassword()
                )
        ); 
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);	     
        
        return new JwtDTO(jwt);
    }
	
	
	public String registerUser(UserDTO userDTO) throws MensajeException {
		User user = new User();
		user.setUsername(userDTO.getUsername());
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
	
		if (userRepository.existsByUsername(userDTO.getUsername())) {
			throw new NotFountException("USER_ALREADT_EXIST", "USER_ALREADT_EXIST");
		}

		Set<Role> roles = new HashSet<>();
			Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		
			user.setRoles(roles);
			try {
				userRepository.save(user);
			} catch (final Exception e) {
				LOGGER.error("INTERNAL_SERVER_ERROR", e);
				throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
			}
			return "Succes";
	}
	
	
}
