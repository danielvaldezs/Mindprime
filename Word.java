package main;

public class Word {

	private String word;
	private String category;
	private char initialLetter;
	private boolean mainWord;

	public Word() {
		// TODO Auto-generated constructor stub
	}

	public Word(String word, String category, char initialLetter, boolean mainWord) {
		this.word = word;
		this.category = category;
		this.initialLetter = initialLetter;
		this.mainWord = mainWord;
	}

	public String getWord() {
		return word;
	}

	public boolean isMainWord() {
		return mainWord;
	}

	public void setMainWord(boolean mainWord) {
		this.mainWord = mainWord;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public char getInitialLetter() {
		return initialLetter;
	}

	public void setInitialLetter(char initialLetter) {
		this.initialLetter = initialLetter;
	}

	@Override
	public String toString() {
		return "Word [word=" + word + ", category=" + category + ", initialLetter=" + initialLetter + "]";
	}

}
