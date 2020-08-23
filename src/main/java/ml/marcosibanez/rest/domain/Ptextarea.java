package ml.marcosibanez.rest.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Ptextarea  implements Serializable {
    private static final long serialVersionUID = 1L;
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long textorder;
	
	@NotNull
	@Column(length = 65535, columnDefinition = "text")
	private String textcontent;
	
	@Column(name = "fk_about")
	private Long fkabout;

	public Ptextarea() {}

	public Ptextarea(Long textorder, String textcontent, Long fkabout) {
		this.textorder = textorder;
		this.textcontent = textcontent;
		this.fkabout = fkabout;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTextorder() {
		return textorder;
	}

	public void setTextorder(Long textorder) {
		this.textorder = textorder;
	}

	public String getTextcontent() {
		return textcontent;
	}

	public void setTextcontent(String textcontent) {
		this.textcontent = textcontent;
	}

	public Long getFkabout() {
		return fkabout;
	}

	public void setFkabout(Long fkabout) {
		this.fkabout = fkabout;
	}

}
