package ml.marcosibanez.rest.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import ml.marcosibanez.rest.service.dto.ContactDTO;


@Entity
public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min = 3, max=100)
	@Column(length = 100, nullable = false )
	private String name;
	
	@Email
	@Size(min = 5, max=250)
	@Column(length = 254, nullable = false )
	private String email;
	
	
	@NotNull
	@Size(min = 3, max=250)
	@Column(length = 250, nullable = false )
	private String subject;
	
	@NotNull
	@Size(min = 4, max=65535)
	@Column(length = 65535, columnDefinition = "text")
	private String message;

	@Column(name = "fecha_insert")
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	private Date finsert;

	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	@Column(name = "fecha_update")
	private Date fupdate;

	public Contact(String name,  String email,
			String subject, String message) {
		this.name = name;
		this.email = email;
		this.subject = subject;
		this.message = message;
	}
	public Contact(ContactDTO contactDto) {
		this.name = contactDto.getName();
		this.email = contactDto.getEmail();
		this.subject = contactDto.getSubject();
		this.message = contactDto.getMessage();
	}

	public Contact() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

}
