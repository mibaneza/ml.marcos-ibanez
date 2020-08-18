package ml.marcosibanez.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ml.marcosibanez.rest.domain.Proyect;
import ml.marcosibanez.rest.repository.ProyectRepository;
import ml.marcosibanez.rest.service.exception.InternalServerErrorException;
import ml.marcosibanez.rest.service.exception.MensajeException;
import ml.marcosibanez.rest.service.exception.NotFountException;

@Service
public class ProyectService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProyectService.class);

	@Autowired
	ProyectRepository proyectRepository;


	@Transactional
	public String createProyect(Proyect proyects) throws MensajeException {
		try {
			proyectRepository.save(proyects);
		} catch (final Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR");
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		return "Resgistro exitoso ";
	}


	@Transactional
	public List<Proyect> findAllProyect() throws MensajeException {
		List<Proyect> proyects = new ArrayList<Proyect>();
		try {
			proyects = proyectRepository.findAll();
		} catch (final Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR");
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		return proyects;
	}

	
	public Proyect updateProyect(Proyect proyects) throws MensajeException {
		
		Proyect proyect = proyectRepository.findById(proyects.getId())
				.orElseThrow(() -> new NotFountException("SNOT-404-1", "ABOUT_NOT_FOUND"));
		try {
			proyectRepository.save(proyect);

		} catch (final Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR");
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		return proyects;
	}
	@Transactional
	public String deleteAbout(Long id) throws MensajeException {
		proyectRepository.findById(id)
				.orElseThrow(() -> new NotFountException("SNOT-404-1", "ABOUT_NOT_FOUND"));
		try {
			proyectRepository.deleteById(id);
		} catch (Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR", e);
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}

		return "DELECT SUCCESS";
	}
}



