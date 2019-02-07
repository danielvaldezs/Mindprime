package main;

//import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectMainWords {

	public String[] selectWords(Connection connection) {

		String palabras[] = new String[6];
		String sql = "SELECT idWord, word, category, quantitySyllables " + "from Word " + "where mainWord = true "
				+ "order by random() " + "limit 6";

		try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

			// loop through the result set
			int i = 0;
			while (rs.next()) {

//                System.out.println(rs.getInt("idWord") +  "\t" + 
//                                   rs.getString("word") + "\t" +
//                                   rs.getString("category") + "\t" +
//                                   rs.getInt("quantitySyllables"));
//            	System.out.println(rs.getString("word"));
				palabras[i] = rs.getString("word");
				i++;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return palabras;
	}

//	public String[] saveWords(String palabras[]) {
//		String palabrasDb[] = new String[palabras.length];
//		return palabrasDb;
//	}

}
