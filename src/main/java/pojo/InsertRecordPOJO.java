package pojo;

public class InsertRecordPOJO {

	private String birthday;
	private String gender;
	private String name;
	private String natID;
	private String salary;
	private String tax;

	public InsertRecordPOJO(String birthday, String gender, String name, String natID, String salary, String tax) {
		this.birthday = birthday;
		this.gender = gender;
		this.name = name;
		this.natID = natID;
		this.salary = salary;
		this.tax = tax;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public String getGender() {
		return this.gender;
	}

	public String getName() {
		return this.name;
	}

	public String getNatID() {
		return this.natID;
	}

	public String getSalary() {
		return this.salary;
	}

	public String getTax() {
		return this.tax;
	}

}
