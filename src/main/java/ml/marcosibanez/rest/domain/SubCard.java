package ml.marcosibanez.rest.domain;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import com.sun.istack.NotNull;

@Entity
@Table(name = "sub-card")
public class SubCard implements Serializable {
    private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min = 2, max=10)
	@Column(length = 10, nullable = false )
	private String  logo;
	
	@NotNull
	@Size(min = 3, max=50)
	@Column(length = 50, nullable = false )
	private String name;
	
	@Column(name = "fK_Card")
	private Long fkcard;

	public SubCard(String logo, String name) {
		this.logo = logo;
		this.name = name;
	}

	public SubCard() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getFkcard() {
		return fkcard;
	}

	public void setFkcard(Long fkcard) {
		this.fkcard = fkcard;
	}

}
