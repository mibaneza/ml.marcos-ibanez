package ml.marcosibanez.rest.service.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ml.marcosibanez.rest.domain.Article;
import ml.marcosibanez.rest.domain.Parrafo;

public class ArticleDTO implements Serializable {
    private static final long serialVersionUID = 2405172041950251807L;
    	
	
	@NotNull
	@Size(min = 2, max = 50)
	private String linkarticle;
	
	@NotNull
	@Size(min = 2, max = 50)
	private String titlearticle;
	
	@NotNull
	@Size(min = 2, max = 250)
	private String imgheaderlink;
	
	private List<ParrafoDTO> sectionr ;

	public ArticleDTO(String linkarticle,
			String titlearticle,
			String imgheaderlink) {
		this.linkarticle = linkarticle;
		this.titlearticle = titlearticle;
		this.imgheaderlink = imgheaderlink;
	}

	public ArticleDTO( Article article) {
		this.linkarticle = article.getLinkarticle();
		this.titlearticle = article.getTitlearticle();
		this.imgheaderlink = article.getImgheaderlink();

	}

	public String getTitlearticle() {
		return titlearticle;
	}

	public void setTitlearticle(String titlearticle) {
		this.titlearticle = titlearticle;
	}

	public String getImgheaderlink() {
		return imgheaderlink;
	}

	public void setImgheaderlink(String imgheaderlink) {
		this.imgheaderlink = imgheaderlink;
	}

	public List<ParrafoDTO> getSectionr() {
		return sectionr;
	}

	public void setSectionr(List<ParrafoDTO> sectionr) {
		this.sectionr = sectionr;
	}

	public String getLinkarticle() {
		return linkarticle;
	}
	public void setLinkarticle(String linkarticle) {
		this.linkarticle = linkarticle;
	}
	
}
