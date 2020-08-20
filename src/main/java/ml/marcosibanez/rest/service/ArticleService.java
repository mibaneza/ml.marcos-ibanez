package ml.marcosibanez.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ml.marcosibanez.rest.domain.Article;
import ml.marcosibanez.rest.domain.Parrafo;
import ml.marcosibanez.rest.repository.ArticleRepository;
import ml.marcosibanez.rest.repository.ParrafoRepository;
import ml.marcosibanez.rest.service.dto.ArticleDTO;
import ml.marcosibanez.rest.service.dto.ParrafoDTO;
import ml.marcosibanez.rest.service.exception.InternalServerErrorException;
import ml.marcosibanez.rest.service.exception.MensajeException;
import ml.marcosibanez.rest.service.exception.NotFountException;
import ml.marcosibanez.rest.service.mapper.ArticleMapper;

@Service
public class ArticleService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ArticleService.class);
	private static final String MENSAJEERROR = "INTERNAL_SERVER_ERROR";
	
	@Autowired
	ArticleRepository articleRepository;
	
	@Autowired
	ParrafoRepository parrafoRepository;
	
	@Autowired
	ArticleMapper articleMapper;
	
	Article article;



	public String createArticle(ArticleDTO articleDTO) throws MensajeException {
		article = new Article(
				articleDTO.getLinkarticle()
				,articleDTO.getTitlearticle()
				,articleDTO.getImgheaderlink()			
				);
		try {
			articleRepository.save(article);
		} catch (final Exception e) {
			LOGGER.error(MENSAJEERROR);
			throw new NotFountException(MENSAJEERROR, MENSAJEERROR);
		}
		Article articles = articleRepository.findByLinkarticle(articleDTO.getLinkarticle())
				.orElseThrow(() -> new NotFountException("SNOT-404-1", "Article_NOT_FOUND"));
		List<ParrafoDTO> parrafoDTO = articleDTO.getSectionr();
		List<Parrafo> parrafos = new ArrayList<>();
		
		for(ParrafoDTO parrafoDto : parrafoDTO) {
			Parrafo parrafo = new Parrafo(parrafoDto.getOrders(),parrafoDto.getContent());
			parrafo.setFkarticle(articles.getId());
			parrafo.setLinkcodetop(parrafoDto.getLinkcodetop());
			parrafo.setLinkimgtop(parrafoDto.getLinkimgtop());
			parrafo.setLinkcodebot(parrafoDto.getLinkcodebot());
			parrafo.setLinkimgbot(parrafoDto.getLinkimgbot());
			parrafos.add(parrafo);
		}
		try {
			parrafoRepository.saveAll(parrafos);
		} catch (final Exception e) {
			LOGGER.error(MENSAJEERROR);
			throw new NotFountException(MENSAJEERROR, MENSAJEERROR);
		}
		return "Resgistro exitoso ";
	}


	@Transactional
	public List<ArticleDTO> findAllArticle() throws MensajeException {
		List<ArticleDTO> articleDTOs;
		List<Article> articles;
		try {
			articles =  articleRepository.findAll();
		} catch (final Exception e) {
			LOGGER.error(MENSAJEERROR);
			throw new NotFountException(MENSAJEERROR, MENSAJEERROR);
		}
		articleDTOs  = articleMapper.mapper(articles);
		return articleDTOs;
	}
	@Transactional
	public ArticleDTO findByLinkarticle(String linkarticle) throws MensajeException {
		ArticleDTO articleDTO;
			article =  articleRepository.findByLinkarticle(linkarticle)
					.orElseThrow(() -> new NotFountException("SNOT-404-1", "Article_NOT_FOUND"));
	
		articleDTO  = articleMapper.mapperByOne(article);
		return articleDTO;
	}
	
	public ArticleDTO updateArticle(ArticleDTO articleDTO) throws MensajeException {
		
		Article article = articleRepository.findByLinkarticle(articleDTO.getLinkarticle())
				.orElseThrow(() -> new NotFountException("SNOT-404-1", "Article_NOT_FOUND"));
		article.setLinkarticle(articleDTO.getLinkarticle());
		article.setTitlearticle(articleDTO.getTitlearticle());
		article.setImgheaderlink(articleDTO.getImgheaderlink());


		try {
			articleRepository.save(article);

		} catch (final Exception e) {
			LOGGER.error(MENSAJEERROR);
			throw new NotFountException(MENSAJEERROR, MENSAJEERROR);
		}
		
		List<ParrafoDTO> parrafoDTO = articleDTO.getSectionr();		
		List<Parrafo> parrafos = new ArrayList<>();
		
		for(ParrafoDTO parrafoDto : parrafoDTO) {
			Parrafo parrafoF = parrafoRepository.findByOrderpAndFkarticle(parrafoDto.getOrders(),article.getId())
					.orElseThrow(() -> new NotFountException("SNOT-404-1", "Article_NOT_FOUND"));
			Parrafo parrafo = new Parrafo(parrafoF.getOrder(),parrafoDto.getContent());
			parrafo.setFkarticle(parrafoF.getFkarticle());
			parrafo.setId(parrafoF.getId());
			parrafo.setLinkcodetop(parrafoF.getLinkcodetop());
			parrafo.setLinkimgtop(parrafoF.getLinkimgtop());
			parrafo.setLinkcodebot(parrafoF.getLinkcodebot());
			parrafo.setLinkimgbot(parrafoF.getLinkimgbot());
			parrafos.add(parrafo);
		}
		try {
			parrafoRepository.saveAll(parrafos);
		} catch (final Exception e) {
			LOGGER.error(MENSAJEERROR);
			throw new NotFountException(MENSAJEERROR, MENSAJEERROR);
		}
		return articleDTO;
	}

	@Transactional
	public String deleteArticle(Long id) throws MensajeException {
		articleRepository.findById(id)
				.orElseThrow(() -> new NotFountException("SNOT-404-1", "Article_NOT_FOUND"));
		try {
			articleRepository.deleteById(id);
		} catch (Exception e) {
			LOGGER.error(MENSAJEERROR, e);
			throw new NotFountException(MENSAJEERROR, MENSAJEERROR);
		}

		return "DELECT SUCCESS";
	}
}
