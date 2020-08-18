package ml.marcosibanez.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ml.marcosibanez.rest.domain.About;
import ml.marcosibanez.rest.repository.AboutRepository;
import ml.marcosibanez.rest.service.exception.InternalServerErrorException;
import ml.marcosibanez.rest.service.exception.MensajeException;
import ml.marcosibanez.rest.service.exception.NotFountException;

@Service
public class AboutService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AboutService.class);

	@Autowired
	AboutRepository aboutRepository;


	@Transactional
	public String createAbout(About abouts) throws MensajeException {
		try {
			aboutRepository.save(abouts);
		} catch (final Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR");
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		return "Resgistro exitoso ";
	}


	@Transactional
	public List<About> findAllAbout() throws MensajeException {
		List<About> abouts = new ArrayList<About>();
		try {
			abouts = aboutRepository.findAll();
		} catch (final Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR");
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		return abouts;
	}

	
	public About updateAbout(About abouts) throws MensajeException {
		
		About about = aboutRepository.findById(abouts.getId())
				.orElseThrow(() -> new NotFountException("SNOT-404-1", "ABOUT_NOT_FOUND"));
		try {
			aboutRepository.save(about);

		} catch (final Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR");
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		return abouts;
	}

	@Transactional
	public String deleteAbout(Long id) throws MensajeException {
		aboutRepository.findById(id)
				.orElseThrow(() -> new NotFountException("SNOT-404-1", "ABOUT_NOT_FOUND"));
		try {
			aboutRepository.deleteById(id);
		} catch (Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR", e);
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}

		return "DELECT SUCCESS";
	}

}



