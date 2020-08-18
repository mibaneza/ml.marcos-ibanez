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
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

@Entity
public class Card implements Serializable {
    private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min = 2, max = 10)
	@Column(length = 10, nullable = false)
	private String logotitle;
	
	@NotNull
	@Size(min = 3, max = 50)
	@Column(length = 50, nullable = false)
	private String title;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "fK_Card",
	referencedColumnName = "id")
	private List<SubCard> subcards = new ArrayList<>();

	public Card(String logotitle, String title,
			List<SubCard> subcards) {
		this.logotitle = logotitle;
		this.title = title;
		this.subcards = subcards;
	}

	public Card() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogotitle() {
		return logotitle;
	}

	public void setLogotitle(String logotitle) {
		this.logotitle = logotitle;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<SubCard> getSubcards() {
		return subcards;
	}

	public void setSubcards(List<SubCard> subcards) {
		this.subcards = subcards;
	}

}
