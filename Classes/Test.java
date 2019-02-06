package main;

public class Test {

	private String correctAnswer;
	private String failedAnswer;
	private float finalTime;
	private int idUser;

	public Test(String correctAnswer, String failedAnswer, float finalTime, int idUser) {
		this.correctAnswer = correctAnswer;
		this.failedAnswer = failedAnswer;
		this.finalTime = finalTime;
		this.idUser = idUser;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public String getFailedAnswer() {
		return failedAnswer;
	}

	public void setFailedAnswer(String failedAnswer) {
		this.failedAnswer = failedAnswer;
	}

	public float getFinalTime() {
		return finalTime;
	}

	public void setFinalTime(float finalTime) {
		this.finalTime = finalTime;
	}
	
	@Override
	public String toString() {
		return "Test [correctAnswer=" + correctAnswer + ", failedAnswer=" + failedAnswer + ", finalTime=" + finalTime
				+ ", idUser=" + idUser + "]";
	}


}
