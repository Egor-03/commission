package by.grsu.anikevich.comission.web.dto;

public class SpecialityDto {
	private Integer id;
	private Integer firstSubjectId;
	private String firstSubjectName;
	private Integer secondSubjectId;
	private String secondSubjectName;
	private Integer thirdSubjectId;
	private String thirdSubjectName;
	private Integer facultyId;
	private String facultyName;
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
	public String getFirstSubjectName() {
		return firstSubjectName;
	}

	public void setFirstSubjectName(String firstSubjectName) {
		this.firstSubjectName = firstSubjectName;
	}

	public String getSecondSubjectName() {
		return secondSubjectName;
	}

	public void setSecondSubjectName(String secondSubjectName) {
		this.secondSubjectName = secondSubjectName;
	}

	public String getThirdSubjectName() {
		return thirdSubjectName;
	}

	public void setThirdSubjectName(String thirdSubjectName) {
		this.thirdSubjectName = thirdSubjectName;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

}
