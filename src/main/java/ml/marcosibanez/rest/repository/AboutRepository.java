package ml.marcosibanez.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ml.marcosibanez.rest.domain.About;

@Repository
public interface AboutRepository extends JpaRepository<About, Long>{

}
