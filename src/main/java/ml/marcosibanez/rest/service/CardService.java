package ml.marcosibanez.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ml.marcosibanez.rest.domain.Card;
import ml.marcosibanez.rest.repository.CardRepository;
import ml.marcosibanez.rest.service.exception.InternalServerErrorException;
import ml.marcosibanez.rest.service.exception.MensajeException;
import ml.marcosibanez.rest.service.exception.NotFountException;

@Service
public class CardService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CardService.class);

	@Autowired
	CardRepository cardRepository;


	@Transactional
	public String createCard(Card cards) throws MensajeException {
		try {
			cardRepository.save(cards);
		} catch (final Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR");
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		return "Resgistro exitoso ";
	}


	@Transactional
	public List<Card> findAllCard() throws MensajeException {
		List<Card> cards = new ArrayList<Card>();
		try {
			cards = cardRepository.findAll();
		} catch (final Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR");
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		return cards;
	}

	public Card updateCard(Card cards) throws MensajeException {
		Card card = cardRepository.findById(cards.getId())
				.orElseThrow(() -> new NotFountException("SNOT-404-1", "ABOUT_NOT_FOUND"));
		try {
			cardRepository.save(card);

		} catch (final Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR");
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		return cards;
	}

	@Transactional
	public String deleteCard(Long id) throws MensajeException {
		cardRepository.findById(id)
				.orElseThrow(() -> new NotFountException("SNOT-404-1", "ABOUT_NOT_FOUND"));
		try {
			cardRepository.deleteById(id);
		} catch (Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR", e);
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		return "DELECT SUCCESS";
	}

}



