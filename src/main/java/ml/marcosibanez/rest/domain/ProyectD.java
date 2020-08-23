package ml.marcosibanez.rest.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
public class ProyectD implements Serializable {
    private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min = 3, max=50)
	@Column(length = 50, nullable = false )
	private String title;

	private Boolean est;
	
	@NotNull
	@Size(min = 3, max=250)
	@Column(length = 250, nullable = false )
	private String textarea;
	
	
	@NotNull
	@Size(min = 1, max=250)
	@Column(length = 250, nullable = false )
	private String isotipo;
	
	@NotNull
	@Size(min = 3, max=250)
	@Column(length = 250, nullable = false )
	private String urlsc;
	
	@NotNull
	@Size(min = 1, max=250)
	@Column(length = 250, nullable = false )
	private String btname;

	public ProyectD(String title, Boolean est,
			String textarea, String isotipo,
			String urlsc, String btname) {
		this.title = title;
		this.est = est;
		this.textarea = textarea;
		this.isotipo = isotipo;
		this.urlsc = urlsc;
		this.btname = btname; 
	}
	public ProyectD(ProyectD proyectDs) {
		this.title = proyectDs.getTitle();
		this.est = proyectDs.getEst();
		this.textarea = proyectDs.getTextarea();
		this.isotipo = proyectDs.getIsotipo();
		this.urlsc = proyectDs.getUrlsc();
		this.btname = proyectDs.getBtname();
	}

	public ProyectD() {}
	public Long getId() {
		return id;
	}

	public String getBtname() {
		return btname;
	}
	public void setBtname(String btname) {
		this.btname = btname;
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

	public Boolean getEst() {
		return est;
	}

	public void setEst(Boolean est) {
		this.est = est;
	}

	public String getTextarea() {
		return textarea;
	}

	public void setTextarea(String textarea) {
		this.textarea = textarea;
	}

	public String getIsotipo() {
		return isotipo;
	}
	public void setIsotipo(String isotipo) {
		this.isotipo = isotipo;
	}
	public String getUrlsc() {
		return urlsc;
	}

	public void setUrlsc(String urlsc) {
		this.urlsc = urlsc;
	}

}
