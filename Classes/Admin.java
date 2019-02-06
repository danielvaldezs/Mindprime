package main;

public class Admin {

	private String firstName;
	private String lastName;
	private String password;
	private String adminName;

	public Admin() {
		// TODO Auto-generated constructor stub
	}

	public Admin(String firstName, String lastName, String password, String adminName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.adminName = adminName;
	}
	

	@Override
	public String toString() {
		return "Admin [firstName=" + firstName + ", lastName=" + lastName + ", password=" + password + ", adminName="
				+ adminName + "]";
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
