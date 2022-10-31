package by.grsu.anikevich.comission.db.model;

public class Speciality {
	private Integer id;
	private Integer firstSubjectId;
	private Integer secondSubjectId;
	private Integer thirdSubjectId;
	private Integer facultyId;
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFirstSubjectId() {
		return firstSubjectId;
	}

	public void setFirstSubjectId(Integer firstSubjectId) {
		this.firstSubjectId = firstSubjectId;
	}

	public Integer getSecondSubjectId() {
		return secondSubjectId;
	}

	public void setSecondSubjectId(Integer secondSubjectId) {
		this.secondSubjectId = secondSubjectId;
	}

	public Integer getThirdSubjectId() {
		return thirdSubjectId;
	}

	public void setThirdSubjectId(Integer thirdSubjectId) {
		this.thirdSubjectId = thirdSubjectId;
	}

	public Integer getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(Integer facultyId) {
		this.facultyId = facultyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Speciality [id=" + id + ", firstSubjectId=" + firstSubjectId + ", secondSubjectId=" + secondSubjectId
				+ ", thirdSubjectId=" + thirdSubjectId + ", facultyId=" + facultyId + ", name=" + name + "]";
	}

}
