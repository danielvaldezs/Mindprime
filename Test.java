package main;

public class Test {

	private String correctAnswer;
	private String failedAnswer;
	private float finalTime;

	public Test(String correctAnswer, String failedAnswer, float finalTime) {
		this.correctAnswer = correctAnswer;
		this.failedAnswer = failedAnswer;
		this.finalTime = finalTime;
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

}
