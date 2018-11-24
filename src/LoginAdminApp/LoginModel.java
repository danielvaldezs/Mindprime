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

        PreparedStatement pr = null;
        ResultSet rs = null;

        String sqlSelect = "SELECT * FROM ADMIN where adminName = ? and password =?";
        //firstName y password son los nombres de las variables en las tablas sqlite
        try{
            pr = this.connection.prepareStatement(sqlSelect);
            pr.setString(1,adminame);
            pr.setString(2,pass);

            rs = pr.executeQuery();

            if(rs.next()){
                return true;
            }
            return false;
        }
        catch (SQLException ex){
            return false;
        }
        finally {
                pr.close();
                rs.close();
        }
    }
}
