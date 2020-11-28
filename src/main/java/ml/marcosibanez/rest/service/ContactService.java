package ml.marcosibanez.rest.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ml.marcosibanez.rest.domain.Contact;
import ml.marcosibanez.rest.repository.ContactRepository;
import ml.marcosibanez.rest.service.dto.ContactDTO;
import ml.marcosibanez.rest.service.exception.NotFountException;
import ml.marcosibanez.rest.service.exception.MensajeException;


@Service
public class ContactService {

	private static final String ERROR = "NOT_FOUND";
    private static final String CODE = "SNOT-404-1";
	@Autowired
	ContactRepository contactRepository;


	@Transactional
	public String createContact(ContactDTO contactDto) throws MensajeException {
		try {
			contactRepository.save(new Contact(contactDto));
		} catch (final Exception e) {
	
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
	
			throw new NotFountException(CODE, ERROR);
		}
		return contacts;
	}
	
	@Transactional
	public String updateContact(ContactDTO contactDto, Long id) throws MensajeException {
		Contact contactDb = null;
		contactDb = contactRepository.findById(id)
				.orElseThrow(() -> new NotFountException(CODE, ERROR));
		contactDb.setEmail(contactDto.getEmail());
		contactDb.setName(contactDto.getName());
		contactDb.setMessage(contactDto.getMessage());
		contactDb.setSubject(contactDto.getSubject());
		
		try {
			contactRepository.save(contactDb);
		} catch (final Exception e) {
	
			throw new NotFountException(CODE, ERROR);
		}
		return "Actualizacion exitoso ";
	}

	@Transactional
	public String deleteContact(Long id) throws MensajeException {
		contactRepository.findById(id)
				.orElseThrow(() -> new NotFountException(CODE, ERROR));
		try {
			contactRepository.deleteById(id);
		} catch (Exception e) {
	
			throw new NotFountException(CODE, ERROR);
		}

		return "DELECT SUCCESS";
	}

}


