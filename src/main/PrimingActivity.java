package main;

public class PrimingActivity {

	private float movementTime;
	private float answerTime;
	private String answer;
	private int idTest;
	private int idWord;

	public PrimingActivity(float movementTime, float answerTime, String answer, int idTest, int idWord) {
		this.movementTime = movementTime;
		this.answerTime = movementTime;
		this.answer = answer;
		this.idTest = idTest;
		this.idWord = idWord;
	}

	public PrimingActivity() {
	}

	public float getMovementTime() {
		return movementTime;
	}

	public void setMovementTime(float movementTime) {
		this.movementTime = movementTime;
	}

	public float getAnswerTime() {
		return answerTime;
	}

	public void setAnswerTime(float answerTime) {
		this.answerTime = answerTime;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getIdTest() {
		return idTest;
	}

	public void setIdTest(int idTest) {
		this.idTest = idTest;
	}

	public int getIdWord() {
		return idWord;
	}

	public void setIdWord(int idWord) {
		this.idWord = idWord;
	}
}
