package LoginAdminApp;

import DatabaseConnection.dbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {

    Connection connection;

    public LoginModel(){
        try{
            this.connection = dbConnection.getConnection();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        if(this.connection == null){
            System.exit(1);
        }
    }

    public boolean isDatabaseConnected(){
        return this.connection != null;
    }

    public boolean isLogin(String adminame, String pass) throws Exception{

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sqlSelect = "SELECT * FROM ADMIN where adminName = ? and password =?"; //firstName and password are the name of the attributes in sqlite
        try{
            preparedStatement = this.connection.prepareStatement(sqlSelect);
            preparedStatement.setString(1,adminame);
            preparedStatement.setString(2,pass);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return true;
            }
            return false;
        }
        catch (SQLException ex){
            return false;
        }
        finally {
                preparedStatement.close();
                resultSet.close();
        }
    }
}
