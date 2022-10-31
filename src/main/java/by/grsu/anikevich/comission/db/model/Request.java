package by.grsu.anikevich.comission.db.model;

public class Request {
	private Integer id;
	private Integer personId;
	private Integer specialityId;
	private Integer stateId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public Integer getSpecialityId() {
		return specialityId;
	}

	public void setSpecialityId(Integer specialityId) {
		this.specialityId = specialityId;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	@Override
	public String toString() {
		return "Request [id=" + id + ", personId=" + personId + ", specialityId=" + specialityId + ", stateId="
				+ stateId + "]";
	}
}