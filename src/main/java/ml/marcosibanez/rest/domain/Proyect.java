package ml.marcosibanez.rest.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

@Entity
public class Proyect implements Serializable {
    private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min = 3, max=50)
	@Column(length = 50, nullable = false )
	private String title;
	
	@NotNull
	@Size(min = 3, max=250)
	@Column(length = 250, nullable = false )
	private String textarea;
	
	
	@NotNull
	@Size(min = 3, max=10)
	@Column(length = 10, nullable = false )
	private String logo;
	
	@NotNull
	@Size(min = 3, max=10)
	@Column(length = 10, nullable = false )
	private String enlacebutton;

	public Proyect(String title, String textarea,
			String logo, String enlacebutton) {
		this.title = title;
		this.textarea = textarea;
		this.logo = logo;
		this.enlacebutton = enlacebutton;
	}

	public Proyect() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTextarea() {
		return textarea;
	}

	public void setTextarea(String textarea) {
		this.textarea = textarea;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getEnlacebutton() {
		return enlacebutton;
	}

	public void setEnlacebutton(String enlacebutton) {
		this.enlacebutton = enlacebutton;
	}

}
