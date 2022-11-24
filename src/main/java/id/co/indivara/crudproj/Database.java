package id.co.indivara.crudproj;
import java.sql.*;

public class Database{
    
    //jdbc:mysql://localhost:8889/hr?autoReconnect=true&useSSL=false

    private Connection c;
    private String url = "jdbc:mysql://localhost:8889/apidb?autoReconnect=true&useSSL=false";
    private String user = "root";
    private String pass = "root";

    public Connection getConnection() throws Exception{
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        c = DriverManager.getConnection(url, user, pass);
        return c;
    }


}