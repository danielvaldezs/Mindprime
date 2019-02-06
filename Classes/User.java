package main;

public class User {

	private String firstName;
	private String lastName;
	private String institution;
	private String group;
	private String birthDate;

	public User(String firstName, String lastName, String institution, String group, String birthDate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.institution = institution;
		this.group = group;
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", institution=" + institution + ", group="
				+ group + ", birthDate=" + birthDate + "]";
	}

	public User() {

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

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

}
