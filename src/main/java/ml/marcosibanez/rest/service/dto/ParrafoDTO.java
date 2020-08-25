package ml.marcosibanez.rest.service.dto;

import java.io.Serializable;

import ml.marcosibanez.rest.domain.Parrafo;

public class ParrafoDTO implements Serializable {
    private static final long serialVersionUID = 2405172041950251807L;
    
	
	private Long orders;
	
	private String linkcodetop;
	
	private String linkimgtop;
	
	private String content;
	
	private String linkcodebot;
	
	private String linkimgbot;

	public ParrafoDTO(Long orders, String content) {
		this.orders = orders;
		this.content = content;
	}
	public ParrafoDTO(Parrafo parrafo) {
		this.orders = parrafo.getOrder();
		this.linkcodetop = parrafo.getLinkcodetop();
		this.linkimgtop = parrafo.getLinkimgtop();
		this.content = parrafo.getContent();
		this.linkcodebot = parrafo.getLinkcodebot();
		this.linkimgbot = parrafo.getLinkimgbot();
	}
	
	public ParrafoDTO() {}
	public Long getOrders() {
		return orders;
	}
	public void setOrders(Long orders) {
		this.orders = orders;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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

}
