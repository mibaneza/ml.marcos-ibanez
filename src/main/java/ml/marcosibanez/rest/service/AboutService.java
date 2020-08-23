package ml.marcosibanez.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ml.marcosibanez.rest.domain.About;
import ml.marcosibanez.rest.domain.Parrafo;
import ml.marcosibanez.rest.domain.Ptextarea;
import ml.marcosibanez.rest.repository.AboutRepository;
import ml.marcosibanez.rest.repository.PtextaareaRepository;
import ml.marcosibanez.rest.service.exception.InternalServerErrorException;
import ml.marcosibanez.rest.service.exception.MensajeException;
import ml.marcosibanez.rest.service.exception.NotFountException;

@Service
public class AboutService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AboutService.class);

	@Autowired
	AboutRepository aboutRepository;
	@Autowired
	PtextaareaRepository ptextaareaRepository;
	About about;
	Ptextarea ptextarea;
	@Transactional
	public String createAbout(About abouts) throws MensajeException {
		about = new About(abouts);
		try {
			aboutRepository.save(about);
		} catch (final Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR");
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		List<Ptextarea> ptextarea = new ArrayList<>();
		for(Ptextarea ptextareaOne : abouts.getPtextarea()) {
			ptextarea.add(new Ptextarea(ptextareaOne.getTextorder(),ptextareaOne.getTextcontent(),(long) 1));
		}
		try {
			ptextaareaRepository.saveAll(ptextarea);
		} catch (final Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR");
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		return "Resgistro exitoso ";
	}


	@Transactional
	public About findOneAbout() throws MensajeException {
		 about = aboutRepository.findById((long)1)
					.orElseThrow(() -> new NotFountException("SNOT-404-1", "ABOUT_NOT_FOUND"));
		return about;
	}

	
	public About updateAbout(About abouts) throws MensajeException {
		 List<Ptextarea> ptextarea;
		 about = aboutRepository.findById((long) 1)
				.orElseThrow(() -> new NotFountException("SNOT-404-1", "ABOUT_NOT_FOUND"));
		 about.setLogo(abouts.getLogo());
		 about.setTitle(abouts.getLogo());
		 
		 ptextarea = new ArrayList<>();
		 
		 for(Ptextarea ptextareaOne : abouts.getPtextarea()) {
			 Ptextarea parrafoF = ptextaareaRepository.findByTextorderAndFkabout(ptextareaOne.getTextorder(),(long)1)
						.orElseThrow(() -> new NotFountException("SNOT-404-1", "Article_NOT_FOUND"));	 
			 parrafoF.setTextorder(ptextareaOne.getTextorder());
			 parrafoF.setTextcontent(ptextareaOne.getTextcontent());
				ptextarea.add(parrafoF);
			}
			try {
				ptextaareaRepository.saveAll(ptextarea);
			} catch (final Exception e) {
				LOGGER.error("INTERNAL_SERVER_ERROR");
				throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
			}
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



