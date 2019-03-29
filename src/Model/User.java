package Model;

public class User {

    private int id;
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


    public User() {

    }

    public User(int id, String firstName, String lastName, String institution, String group, String birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.institution = institution;
        this.group = group;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return firstName + " " + lastName + "     " + birthDate + "     " + institution + "     " + group ;
    }
}
