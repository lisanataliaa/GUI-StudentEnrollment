package main;

public class Mahasiswa {
	private String studentID;
	private String name;
	private String gender;

	public Mahasiswa(String studentID, String name, String gender) {
		super();
		this.studentID = studentID;
		this.name = name;
		this.gender = gender;
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
