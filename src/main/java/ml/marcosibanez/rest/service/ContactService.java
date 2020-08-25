package ml.marcosibanez.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ml.marcosibanez.rest.domain.Contact;
import ml.marcosibanez.rest.repository.ContactRepository;
import ml.marcosibanez.rest.service.dto.ContactDTO;
import ml.marcosibanez.rest.service.exception.NotFountException;
import ml.marcosibanez.rest.service.exception.MensajeException;
import ml.marcosibanez.rest.service.exception.NotFountException;

@Service
public class ContactService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ContactService.class);
	private static final String ERROR = "NOT_FOUND";
    private static final String CODE = "SNOT-404-1";
	@Autowired
	ContactRepository contactRepository;


	@Transactional
	public String createContact(ContactDTO contactDto) throws MensajeException {
		try {
			contactRepository.save(new Contact(contactDto));
		} catch (final Exception e) {
			LOGGER.error(CODE);
			throw new NotFountException(CODE, ERROR);
		}
		return "Resgistro exitoso ";
	}


	@Transactional
	public List<Contact> findAllContact() throws MensajeException {
		List<Contact> contacts = new ArrayList<>();
		try {
			contacts = contactRepository.findAll();
		} catch (final Exception e) {
			LOGGER.error(CODE);
			throw new NotFountException(CODE, ERROR);
		}
		return contacts;
	}


	@Transactional
	public String deleteContact(Long id) throws MensajeException {
		contactRepository.findById(id)
				.orElseThrow(() -> new NotFountException(CODE, ERROR));
		try {
			contactRepository.deleteById(id);
		} catch (Exception e) {
			LOGGER.error(CODE, e);
			throw new NotFountException(CODE, ERROR);
		}

		return "DELECT SUCCESS";
	}

}


