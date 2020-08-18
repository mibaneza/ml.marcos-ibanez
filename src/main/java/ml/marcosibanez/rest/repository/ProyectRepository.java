package ml.marcosibanez.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ml.marcosibanez.rest.domain.Proyect;

@Repository
public interface ProyectRepository extends JpaRepository<Proyect, Long>{

}
