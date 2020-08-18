package ml.marcosibanez.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ml.marcosibanez.rest.domain.SubCard;

@Repository
public interface SubCardRepository extends JpaRepository<SubCard, Long>{

}
