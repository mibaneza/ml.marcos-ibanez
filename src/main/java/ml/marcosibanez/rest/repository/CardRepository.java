package ml.marcosibanez.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ml.marcosibanez.rest.domain.Card;

@Repository
public interface CardRepository  extends JpaRepository<Card, Long>{

}
