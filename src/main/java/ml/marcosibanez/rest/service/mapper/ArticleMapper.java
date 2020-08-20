package ml.marcosibanez.rest.service.mapper;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ml.marcosibanez.rest.domain.Article;
import ml.marcosibanez.rest.domain.Parrafo;
import ml.marcosibanez.rest.service.dto.ArticleDTO;
import ml.marcosibanez.rest.service.dto.ParrafoDTO;





@Component 
public class ArticleMapper {
	
	public List<ArticleDTO> mapper (List<Article> articles){
		
		List<ArticleDTO> articleDTOs = new ArrayList<>();
		for(Article article : articles){
			ArticleDTO	articleDTO = new ArticleDTO(article);
			List<Parrafo> parrafos = article.getSection();
			List<ParrafoDTO> parrafoDTOs = new ArrayList<>();
			
			for(Parrafo parrafo : parrafos) {
				parrafoDTOs.add(new ParrafoDTO(parrafo));
			}
			articleDTO.setSectionr(parrafoDTOs);
			articleDTOs.add(articleDTO);
		}
		return articleDTOs;
	}
public ArticleDTO mapperByOne (Article article){
		
			ArticleDTO	articleDTO = new ArticleDTO(article);
			List<Parrafo> parrafos = article.getSection();
			List<ParrafoDTO> parrafoDTOs = new ArrayList<>();
			
			for(Parrafo parrafo : parrafos) {
				parrafoDTOs.add(new ParrafoDTO(parrafo));
			}
			articleDTO.setSectionr(parrafoDTOs);
		
		
		return articleDTO;
	}

}
