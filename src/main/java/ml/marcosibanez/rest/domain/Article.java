package ml.marcosibanez.rest.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(	name = "article", 
uniqueConstraints = { 
	@UniqueConstraint(columnNames = "linkarticle")
})
public class Article implements Serializable {
    private static final long serialVersionUID = 1905122041950251207L;
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	

	@Column(length = 50)
	private String linkarticle;
	

	@Size(min = 2, max = 50)
	@Column(length = 50, nullable = false)
	private String titlearticle;

	@Size(min = 3, max = 250)
	@Column(length = 250, nullable = true)
	private String imgheaderlink;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_article", referencedColumnName = "id")
	private List<Parrafo> section = new ArrayList<>();

	@Column(name = "fecha_insert")
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	private Date finsert;

	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	@Column(name = "fecha_update")
	private Date fupdate;

	public Article(String linkarticle, String titlearticle,
			String imgheaderlink) {
		this.linkarticle = linkarticle;
		this.titlearticle = titlearticle;
		this.imgheaderlink = imgheaderlink;
	}

	public Article() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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


	public Date getFinsert() {
		return finsert;
	}

	public void setFinsert(Date finsert) {
		this.finsert = finsert;
	}

	public Date getFupdate() {
		return fupdate;
	}

	public void setFupdate(Date fupdate) {
		this.fupdate = fupdate;
	}

	public String getLinkarticle() {
		return linkarticle;
	}

	public void setLinkarticle(String linkarticle) {
		this.linkarticle = linkarticle;
	}
	public List<Parrafo> getSection() {
		return section;
	}

	public void setSection(List<Parrafo> section) {
		this.section = section;
	}
}
