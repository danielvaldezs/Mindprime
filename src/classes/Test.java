package classes;

public class Test {

	private String correctAnswers;
	private String dateDone;
	private int idUser;

	public Test(String correctAnswers, String dateDone, int idUser) {
		this.correctAnswers = correctAnswers;
		this.dateDone = dateDone;
		this.idUser = idUser;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getCorrectAnswers() {
		return correctAnswers;
	}

	public void setCorrectAnswers(String correctAnswers) {
		this.correctAnswers = correctAnswers;
	}

	public String getDateDone() {
		return dateDone;
	}
	
	public void setDateDone(String dateDone) {
		this.dateDone = dateDone;
	}

	@Override
	public String toString() {
		return "Test [correctAnswers=" + correctAnswers + ", dateDone=" + dateDone + ", idUser=" + idUser + "]";
	}

}
