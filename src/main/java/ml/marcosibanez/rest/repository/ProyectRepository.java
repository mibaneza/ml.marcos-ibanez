package ml.marcosibanez.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ml.marcosibanez.rest.domain.ProyectD;

@Repository
public interface ProyectRepository extends JpaRepository<ProyectD, Long>{

}
