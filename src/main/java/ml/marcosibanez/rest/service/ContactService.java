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
import ml.marcosibanez.rest.service.exception.InternalServerErrorException;
import ml.marcosibanez.rest.service.exception.MensajeException;
import ml.marcosibanez.rest.service.exception.NotFountException;

@Service
public class ContactService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ContactService.class);

	@Autowired
	ContactRepository contactRepository;


	@Transactional
	public String createContact(ContactDTO contactDto) throws MensajeException {
		try {
			contactRepository.save(new Contact(contactDto));
		} catch (final Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR");
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		return "Resgistro exitoso ";
	}


	@Transactional
	public List<Contact> findAllContact() throws MensajeException {
		List<Contact> contacts = new ArrayList<Contact>();
		try {
			contacts = contactRepository.findAll();
		} catch (final Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR");
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		return contacts;
	}


	@Transactional
	public String deleteContact(Long id) throws MensajeException {
		contactRepository.findById(id)
				.orElseThrow(() -> new NotFountException("SNOT-404-1", "CONTACT_NOT_FOUND"));
		try {
			contactRepository.deleteById(id);
		} catch (Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR", e);
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}

		return "DELECT SUCCESS";
	}

}


