package ml.marcosibanez.rest.service.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class ContactDTO {
	
	@NotBlank
	@Size(min = 3, max=50)
	private String name;
	
	@Email
	@Size(min = 12, max=100)
	private String email;
	
	@NotBlank
	@Size(min = 3, max=100)
	private String subject;
	
	@NotBlank
	@Size(min = 5, max=65535)
	private String message;

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

}
