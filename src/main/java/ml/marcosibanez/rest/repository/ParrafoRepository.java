package ml.marcosibanez.rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ml.marcosibanez.rest.domain.Parrafo;

@Repository
public interface ParrafoRepository  extends JpaRepository<Parrafo, Long>{
	Optional<Parrafo> findByOrderpAndFkarticle(Long orderp, Long fkarticle);
	Boolean existsByOrderpAndFkarticle(Long orderp, Long fkarticle);
}
