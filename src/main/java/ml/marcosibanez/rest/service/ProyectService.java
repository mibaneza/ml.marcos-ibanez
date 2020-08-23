package ml.marcosibanez.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ml.marcosibanez.rest.domain.ProyectD;
import ml.marcosibanez.rest.repository.ProyectRepository;
import ml.marcosibanez.rest.service.exception.InternalServerErrorException;
import ml.marcosibanez.rest.service.exception.MensajeException;
import ml.marcosibanez.rest.service.exception.NotFountException;

@Service
public class ProyectService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProyectService.class);

	@Autowired
	ProyectRepository proyectRepository;
	ProyectD proyectD;

	@Transactional
	public String createProyect(ProyectD proyectDs) throws MensajeException {
		 proyectD = new ProyectD(proyectDs);
		try {
			proyectRepository.save(proyectD);
		} catch (final Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR");
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		return "Resgistro exitoso ";
	}



	public List<ProyectD> findAllProyect() throws MensajeException {
		List<ProyectD> sproyectDs;
		try {
			sproyectDs = proyectRepository.findAll();
		} catch (final Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR");
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		return sproyectDs;
	}

	
	public ProyectD updateProyect(ProyectD proyectDs) throws MensajeException {
		proyectD = new ProyectD(proyectDs);
		ProyectD idP = proyectRepository.findById(proyectDs.getId())
				.orElseThrow(() -> new NotFountException("SNOT-404-1", "ABOUT_NOT_FOUND"));
		proyectD.setId(idP.getId());
		try {
			proyectRepository.save(proyectD);

		} catch (final Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR");
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		return proyectDs;
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



