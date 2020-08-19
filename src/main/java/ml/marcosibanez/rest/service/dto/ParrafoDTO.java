package ml.marcosibanez.rest.service.dto;

import java.io.Serializable;

import javax.persistence.Column;

import ml.marcosibanez.rest.domain.Parrafo;

public class ParrafoDTO implements Serializable {
    private static final long serialVersionUID = 2405172041950251807L;
    
	
	private Long orders;
	
	private String content;

	public ParrafoDTO(Long orders, String content) {
		this.orders = orders;
		this.content = content;
	}
	public ParrafoDTO(Parrafo parrafo) {
		this.orders = parrafo.getOrder();
		this.content = parrafo.getContent();
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
	

	
	
}
