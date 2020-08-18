package ml.marcosibanez.rest.service.dto;

import java.util.HashSet;
import java.util.Set;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import ml.marcosibanez.rest.domain.Role;

public class UserDTO {

		@NotBlank
		@Size(min = 3, max = 12)
		private String username;

		@NotBlank
		@Size(max = 120)
		private String password;


		public UserDTO(String username, String password) {
			this.username = username;
			this.password = password;
		}

		public UserDTO() {}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}


}
