package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public abstract class DatabaseQueryDao <T> {



    protected Connection conn;

    /*Constructor*/

    public DatabaseQueryDao(){
        initConnection();
    }

    protected void initConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex1) {
            System.exit(1);
        }
        try {
            String serverName = "localhost";
            String mydatabase = "tpbdnote";
            String url = "jdbc:mysql://" + serverName + "/" + mydatabase+"?serverTimezone=UTC";

            String username = "root";
            String password = "";
            conn=DriverManager.getConnection(url,username,password);
        }catch (SQLException ex2) {
            System.exit(1);
        }
    }


}
