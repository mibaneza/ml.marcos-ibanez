package ml.marcosibanez.rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ml.marcosibanez.rest.domain.SubCard;

@Repository
public interface SubCardRepository extends JpaRepository<SubCard, Long>{
	Optional<SubCard> findByNameAndFkcard(String name, Long fkcard);
	Boolean existsByNameAndFkcard(String name, Long fkcard);
}
