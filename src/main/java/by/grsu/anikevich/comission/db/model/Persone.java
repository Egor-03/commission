package by.grsu.anikevich.comission.db.model;

public class Persone {
	private Integer id;
	private Integer roleId;
	private String firstName;
	private String secondName;
	private String patronymic;
	private String mail;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Override
	public String toString() {
		return "Persone [id=" + id + ", roleId=" + roleId + ", firstName=" + firstName + ", secondName=" + secondName
				+ ", patronymic=" + patronymic + ", mail=" + mail + "]";
	}
}
