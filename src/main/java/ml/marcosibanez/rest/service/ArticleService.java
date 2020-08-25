package ml.marcosibanez.rest.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ml.marcosibanez.rest.domain.Article;
import ml.marcosibanez.rest.domain.Parrafo;
import ml.marcosibanez.rest.repository.ArticleRepository;
import ml.marcosibanez.rest.repository.ParrafoRepository;
import ml.marcosibanez.rest.service.dto.ArticleDTO;
import ml.marcosibanez.rest.service.dto.ParrafoDTO;
import ml.marcosibanez.rest.service.exception.MensajeException;
import ml.marcosibanez.rest.service.exception.NotFountException;
import ml.marcosibanez.rest.service.mapper.ArticleMapper;

@Service
public class ArticleService {
	
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
			throw new NotFountException(MENSAJEERROR, MENSAJEERROR);
		}
		Article articles = articleRepository.findByLinkarticle(articleDTO.getLinkarticle())
				.orElseThrow(() -> new NotFountException(MENSAJEERROR, MENSAJEERROR));
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
			
			throw new NotFountException(MENSAJEERROR, MENSAJEERROR);
		}
		articleDTOs  = articleMapper.mapper(articles);
		return articleDTOs;
	}
	@Transactional
	public ArticleDTO findByLinkarticle(String linkarticle) throws MensajeException {
		ArticleDTO articleDTO;
			article =  articleRepository.findByLinkarticle(linkarticle)
					.orElseThrow(() -> new NotFountException(MENSAJEERROR, MENSAJEERROR));
	
		articleDTO  = articleMapper.mapperByOne(article);
		return articleDTO;
	}
	@Transactional
	public String existsByLinkarticle() throws MensajeException {
		String ga ;
		try {
			if(Boolean.TRUE.equals(parrafoRepository.existsByOrderpAndFkarticle((long)1,(long)1))) {
				ga="true";
			}else {ga="false";}

		} catch (final Exception e) {
			
			throw new NotFountException(MENSAJEERROR, MENSAJEERROR);
		}
		return ga;
	}
	public ArticleDTO updateArticle(ArticleDTO articleDTO) throws MensajeException {
		ArticleDTO articleDTOs;
		Article articleup = articleRepository.findByLinkarticle(articleDTO.getLinkarticle())
				.orElseThrow(() -> new NotFountException(MENSAJEERROR, MENSAJEERROR));
		articleup.setLinkarticle(articleDTO.getLinkarticle());
		articleup.setTitlearticle(articleDTO.getTitlearticle());
		articleup.setImgheaderlink(articleDTO.getImgheaderlink());


		try {
			articleRepository.save(articleup);

		} catch (final Exception e) {
			
			throw new NotFountException(MENSAJEERROR, MENSAJEERROR);
		}
		
		List<ParrafoDTO> parrafoDTO = articleDTO.getSectionr();		
		List<Parrafo> parrafos = new ArrayList<>();
		
		for(ParrafoDTO parrafoDto : parrafoDTO) {
			Parrafo parrafoF = new Parrafo();
			if(Boolean.TRUE.equals(parrafoRepository.existsByOrderpAndFkarticle(parrafoDto.getOrders(),articleup.getId()))) {
				 parrafoF = parrafoRepository.findByOrderpAndFkarticle(parrafoDto.getOrders(),articleup.getId())
						.orElseThrow(() -> new NotFountException(MENSAJEERROR, MENSAJEERROR));
				parrafoF.setContent(parrafoDto.getContent());
				parrafoF.setLinkcodetop(parrafoDto.getLinkcodetop());
				parrafoF.setLinkimgtop(parrafoDto.getLinkimgtop());
				parrafoF.setLinkcodebot(parrafoDto.getLinkcodebot());
				parrafoF.setLinkimgbot(parrafoDto.getLinkimgbot());
				parrafos.add(parrafoF);
			}else {
				parrafoF.setOrder(parrafoDto.getOrders());
				parrafoF.setFkarticle(articleup.getId());
				parrafoF.setContent(parrafoDto.getContent());
				parrafoF.setLinkcodetop(parrafoDto.getLinkcodetop());
				parrafoF.setLinkimgtop(parrafoDto.getLinkimgtop());
				parrafoF.setLinkcodebot(parrafoDto.getLinkcodebot());
				parrafoF.setLinkimgbot(parrafoDto.getLinkimgbot());
				parrafos.add(parrafoF);
			}
			
		}
		try {
			parrafoRepository.saveAll(parrafos);
		} catch (final Exception e) {
		
			throw new NotFountException(MENSAJEERROR, MENSAJEERROR);
		}
		articleDTOs  = articleMapper.mapperByOne(articleup);
		return articleDTOs;
	}

	@Transactional
	public String deleteArticle(Long id) throws MensajeException {
		articleRepository.findById(id)
				.orElseThrow(() -> new NotFountException(MENSAJEERROR, MENSAJEERROR));
		try {
			articleRepository.deleteById(id);
		} catch (Exception e) {
		
			throw new NotFountException(MENSAJEERROR, MENSAJEERROR);
		}

		return "DELECT SUCCESS";
	}
}
