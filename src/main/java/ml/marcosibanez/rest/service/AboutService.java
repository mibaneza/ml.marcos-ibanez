package ml.marcosibanez.rest.service;

import java.util.ArrayList;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ml.marcosibanez.rest.domain.About;
import ml.marcosibanez.rest.domain.Ptextarea;
import ml.marcosibanez.rest.repository.AboutRepository;
import ml.marcosibanez.rest.repository.PtextaareaRepository;
import ml.marcosibanez.rest.service.exception.MensajeException;
import ml.marcosibanez.rest.service.exception.NotFountException;

@Service
public class AboutService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AboutService.class);

	private static final String ERROR = "NOT_FOUND";
    private static final String CODE = "SNOT-404-1";
	 
	@Autowired
	AboutRepository aboutRepository;
	@Autowired
	PtextaareaRepository ptextaareaRepository;
	About about;
	Ptextarea ptextarea;
	
	@Transactional
	public String createAbout(About abouts) throws MensajeException {
		about = new About();
		about.setLogo(abouts.getLogo());
		about.setTitle(abouts.getTitle());
		try {
			aboutRepository.save(about);
		} catch (final Exception e) {
			LOGGER.error(ERROR);
			throw new NotFountException(CODE, ERROR);
		}
		About aboutt = aboutRepository.findById((long) 1)
				.orElseThrow(() -> new NotFountException(CODE, ERROR));
		List<Ptextarea> ptextarea = new ArrayList<>();
		for(Ptextarea ptextareaOne : abouts.getPtextarea()) {
			ptextarea.add(new Ptextarea(ptextareaOne.getTextorder(),ptextareaOne.getTextcontent(),aboutt.getId()));
		}
		try {
			ptextaareaRepository.saveAll(ptextarea);
		} catch (final Exception e) {
			LOGGER.error(ERROR);
			throw new NotFountException(CODE, ERROR);
		}
		return "Resgistro exitoso ";
	}


	@Transactional
	public About findOneAbout() throws MensajeException {
		 about = aboutRepository.findById((long)1)
					.orElseThrow(() -> new NotFountException(CODE, ERROR));
		return about;
	}

	
	public About updateAbout(About abouts) throws MensajeException {
		 List<Ptextarea> ptextarea;
		 about = aboutRepository.findById((long) 1)
				.orElseThrow(() -> new NotFountException(CODE, ERROR));
		 about.setLogo(abouts.getLogo());
		 about.setTitle(abouts.getLogo());
		 
		try {
			aboutRepository.save(about);

		} catch (final Exception e) {
			LOGGER.error(ERROR);
			throw new NotFountException(CODE, ERROR);
		}
	 ptextarea = new ArrayList<>();
		 
		 for(Ptextarea ptextareaOne : abouts.getPtextarea()) {
			 Ptextarea parrafoF = ptextaareaRepository.findByTextorderAndFkabout(ptextareaOne.getTextorder(),(long)1)
						.orElseThrow(() -> new NotFountException(CODE, ERROR));	 
			 parrafoF.setTextorder(ptextareaOne.getTextorder());
			 parrafoF.setTextcontent(ptextareaOne.getTextcontent());
				ptextarea.add(parrafoF);
			}
			try {
				ptextaareaRepository.saveAll(ptextarea);
			} catch (final Exception e) {
				LOGGER.error(ERROR);
				throw new NotFountException(CODE, ERROR);
			}
		return abouts;
	}

	@Transactional
	public String deleteAbout(Long id) throws MensajeException {
		aboutRepository.findById(id)
				.orElseThrow(() -> new NotFountException(CODE, ERROR));
		try {
			aboutRepository.deleteById(id);
		} catch (Exception e) {
			LOGGER.error("NOT_FOUND", e);
			throw new NotFountException(CODE, ERROR);
		}

		return "DELECT SUCCESS";
	}

}



