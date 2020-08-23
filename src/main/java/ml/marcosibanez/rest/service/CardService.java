package ml.marcosibanez.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ml.marcosibanez.rest.domain.Card;
import ml.marcosibanez.rest.domain.SubCard;
import ml.marcosibanez.rest.repository.CardRepository;
import ml.marcosibanez.rest.repository.SubCardRepository;
import ml.marcosibanez.rest.service.exception.InternalServerErrorException;
import ml.marcosibanez.rest.service.exception.MensajeException;
import ml.marcosibanez.rest.service.exception.NotFountException;

@Service
public class CardService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CardService.class);

	@Autowired
	CardRepository cardRepository;
	
	@Autowired
	SubCardRepository subCardRepository;
	
	Card card;
	
	@Transactional
	public String createCard(Card cards) throws MensajeException {
		card = new Card();

		card.setLogotitle(cards.getLogotitle());
		card.setTitle(cards.getTitle());
		try {
			cardRepository.save(cards);
		} catch (final Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR");
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		Card cars = cardRepository.findByTitle(cards.getTitle())
					.orElseThrow(() -> new NotFountException("SNOT-404-1", "ABOUT_NOT_FOUND"));
		List<SubCard> subCards = new ArrayList<>();
		for(SubCard subCard : cards.getSubcards()) {
			SubCard subCar = new SubCard();
			subCar.setLogo(subCard.getLogo());
			subCar.setName(subCard.getName());
			subCar.setFkcard(cars.getId());
			subCards.add(subCar);
		}
		try {
			subCardRepository.saveAll(subCards);
		} catch (final Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR");
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		return "Resgistro exitoso ";
	}


	@Transactional
	public List<Card> findAllCard() throws MensajeException {
		List<Card> cards = new ArrayList<>();
		try {
			cards = cardRepository.findAll();
		} catch (final Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR");
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		return cards;
	}

	public Card updateCard(Card cards) throws MensajeException {
		 card = cardRepository.findByTitle(cards.getTitle())
				.orElseThrow(() -> new NotFountException("SNOT-404-1", "ABOUT_NOT_FOUND"));
		 card.setLogotitle(cards.getLogotitle());
		 card.setTitle(cards.getTitle());

		try {
			cardRepository.save(card);

		} catch (final Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR");
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		 List<SubCard> subCards = new ArrayList<>();
			for(SubCard subCard : cards.getSubcards()) {
				SubCard subCar = new SubCard();
				if (Boolean.TRUE.equals(subCardRepository.existsByNameAndFkcard(subCard.getName(),card.getId()))) {
					subCar = subCardRepository.findByNameAndFkcard(subCard.getName(),card.getId())
							.orElseThrow(() -> new NotFountException("SNOT-404-1", "ABOUT_NOT_FOUND"));
					subCar.setLogo(subCard.getLogo());
					subCar.setName(subCard.getName());
				
				}else {
				subCar.setLogo(subCard.getLogo());
				subCar.setName(subCard.getName());
				subCar.setFkcard(card.getId());
		     	}
				subCards.add(subCar);
			}
			try {
				subCardRepository.saveAll(subCards);

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



