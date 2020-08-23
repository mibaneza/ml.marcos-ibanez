package ml.marcosibanez.rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ml.marcosibanez.rest.domain.Ptextarea;

@Repository
public interface PtextaareaRepository extends JpaRepository<Ptextarea, Long>{
	Optional<Ptextarea> findByTextorderAndFkabout(Long textorder, Long fkabout);
}
