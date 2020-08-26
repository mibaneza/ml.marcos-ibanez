package ml.marcosibanez.rest.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Parrafo implements Serializable {
	private static final long serialVersionUID = 2405172041950251807L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long orderp;
	
	private String linkcodetop;

	private String linkimgtop;
	
	@Column(length = 65535, columnDefinition = "text")
	private String content;

	private String linkcodebot;

	private String linkimgbot;
	
	private String styles;


	@Column(name = "fk_article")
	private Long fkarticle;

	public Parrafo(Long order, String content) {
		this.orderp = order;
		this.content = content;
	}

	public Parrafo() {
	}

	public Long getId() {
		return id;
	}

	public Long getFkarticle() {
		return fkarticle;
	}

	public void setFkarticle(Long pkarticle) {
		this.fkarticle = pkarticle;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getOrder() {
		return orderp;
	}

	public void setOrder(Long order) {
		this.orderp = order;
	}

	public String getLinkcodetop() {
		return linkcodetop;
	}

	public void setLinkcodetop(String linkcodetop) {
		this.linkcodetop = linkcodetop;
	}

	public String getLinkimgtop() {
		return linkimgtop;
	}

	public void setLinkimgtop(String linkimgtop) {
		this.linkimgtop = linkimgtop;
	}

	public String getLinkcodebot() {
		return linkcodebot;
	}

	public void setLinkcodebot(String linkcodebot) {
		this.linkcodebot = linkcodebot;
	}

	public String getLinkimgbot() {
		return linkimgbot;
	}

	public void setLinkimgbot(String linkimgbot) {
		this.linkimgbot = linkimgbot;
	}

	public String getStyles() {
		return styles;
	}

	public void setStyles(String styles) {
		this.styles = styles;
	}

}
