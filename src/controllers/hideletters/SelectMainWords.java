package controllers.hideletters;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectMainWords {

	// Metodo para leer palabras de la base de datos
	public String[] selectWords(Connection connection) {

		String palabras[] = new String[6]; // Arreglo para almacenar palabras de la base de datos
		String sql = "SELECT idWord, word, category, quantitySyllables " + "from Word " + "where mainWord = true "
				+ "order by random() " + "limit 6"; // SQL Query para leer las palabras

		try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

			// loop through the result set
			int i = 0;
			while (rs.next()) {
				palabras[i] = rs.getString("word");
				i++;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return palabras;
	}

}
