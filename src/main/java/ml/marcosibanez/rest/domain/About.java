package ml.marcosibanez.rest.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
public class About implements Serializable {
    private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min = 3, max=50)
	@Column(length = 50, nullable = false )
	private String title;
		
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_about", referencedColumnName = "id")
	private List<Ptextarea> ptextarea = new ArrayList<>();
	
	@NotNull
	@Size(min = 3, max=250)
	@Column(length = 250, nullable = false )
	private String logo;

	public About() {}

	public About(Long id, String title, List<Ptextarea> ptextarea,
			String logo) {
		this.id = id;
		this.title = title;
		this.ptextarea = ptextarea;
		this.logo = logo;
	}
	public About(About about) {
		this.title = about.getTitle();
		this.logo = about.getLogo();
	}
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

	public List<Ptextarea> getPtextarea() {
		return ptextarea;
	}

	public void setPtextarea(List<Ptextarea> ptextarea) {
		this.ptextarea = ptextarea;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

}
