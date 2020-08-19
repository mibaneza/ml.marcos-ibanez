package ml.marcosibanez.rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ml.marcosibanez.rest.domain.Article;

@Repository
public interface ArticleRepository  extends JpaRepository<Article, Long>{
	Optional<Article> findByLinkarticle(String linkarticle);

}
