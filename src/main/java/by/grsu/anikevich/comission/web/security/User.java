package by.grsu.anikevich.comission.web.security;

import java.io.Serializable;

public class User  implements Serializable {

	public User(String email, String password, String firstName, String lastName) {
		super();
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private boolean blocked;


	public boolean isBlocked() {
		return blocked;
	}

	public User setBlocked(boolean blocked) {
		this.blocked = blocked;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}