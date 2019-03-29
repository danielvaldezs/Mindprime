package Model;

import DatabaseConnection.dbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Word {
    private Integer idWord;
    private String word;
    private String category;
    private Integer quantitySyllables;
    private char initialLetter;
    private boolean mainWord;

    public Word(){

    }

    public Word(String word, String category, Integer quantitySyllables, char initialLetter, boolean mainWord) {
        this.idWord = idWord;
        this.word = word;
        this.category = category;
        this.quantitySyllables = quantitySyllables;
        this.initialLetter = initialLetter;
        this.mainWord = mainWord;
    }

    public Word(int idWord, String word, String category, int quantitySyllables, char initialLetter, boolean mainWord) {
        this.idWord = idWord;
        this.word = word;
        this.category = category;
        this.quantitySyllables = quantitySyllables;
        this.initialLetter = initialLetter;
        this.mainWord = mainWord;
    }

    public Integer getIdWord() {
        return idWord;
    }

    public void setIdWord(Integer idWord) {
        this.idWord = idWord;
    }

    public String getWord() {
        return word;
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

    public Integer getQuantitySyllables() {
        return quantitySyllables;
    }

    public void setQuantitySyllables(Integer quantitySyllables) {
        this.quantitySyllables = quantitySyllables;
    }

    public char getInitialLetter() {
        return initialLetter;
    }

    public void setInitialLetter(char initialLetter) {
        this.initialLetter = initialLetter;
    }

    public boolean isMainWord() {
        return mainWord;
    }

    public void setMainWord(boolean mainWord) {
        this.mainWord = mainWord;
    }

    @Override
    public String toString() {
        return "Word{" +
                "idWord=" + idWord +
                ", word='" + word + '\'' +
                ", category='" + category + '\'' +
                ", quantitySyllables=" + quantitySyllables +
                ", initialLetter=" + initialLetter +
                ", mainWord=" + mainWord +
                '}';
    }
}
