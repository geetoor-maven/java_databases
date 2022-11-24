package id.co.indivara.crudproj;
import java.security.PublicKey;
import java.sql.*;
import java.util.ArrayList;

public class EmployeeDAO {
    private Database db;
    private Connection conn;
    private String dbTable = "employee";
    public ResultSet resultSet;
    
    public EmployeeDAO(Database db) throws Exception{
        this.db = db;
        this.conn = db.getConnection();
    }

    // GETT ALL EMPLOYE

    public ArrayList<Employee> getAllEmployees() throws Exception{
        String sql = "SELECT * FROM " + this.dbTable;
        Statement statement = this.conn.createStatement();
        resultSet = statement.executeQuery(sql);

        ArrayList<Employee> allEmploye = new ArrayList<Employee>();

        while(resultSet.next()){
            Employee employee = new Employee();
            employee.setId(resultSet.getString("id"));
            employee.setName(resultSet.getString("name"));
            employee.setEmail(resultSet.getString("email"));
            employee.setDesignation(resultSet.getString("designation"));
            employee.setCreated(resultSet.getString("created"));
            allEmploye.add(employee);
        }
        statement.close();
        return allEmploye;
    }

    // FIND ID EMPLOYE
    public Employee findEmploye(String id) throws Exception{
        String sql = "SELECT * FROM " + this.dbTable + " WHERE id ='" + id +"'";
        Statement statement = this.conn.createStatement();
        resultSet = statement.executeQuery(sql);

        Employee employee = null;
        if(resultSet.next()){
            employee = new Employee();
            employee.setId(resultSet.getString("id"));
            employee.setName(resultSet.getString("name"));
            employee.setEmail(resultSet.getString("email"));
            employee.setDesignation(resultSet.getString("designation"));
            employee.setCreated(resultSet.getString("created"));
        }
        statement.close();
        return employee;
    }

    // CREATE EMPLOYE 
    public boolean createEmploye(Employee e) throws Exception{
        String sql = "INSERT INTO employee VALUES(?,?,?,?,?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        
        statement.setString(1, e.getId());
        statement.setString(2, e.getName());
        statement.setString(3, e.getEmail());
        statement.setString(4, e.getDesignation());
        statement.setString(5, e.getCreated());

        int hasil = statement.executeUpdate();
        statement.close();

        if(hasil > 0){
            return true;
        }
        return false;

    }

    // UPDATE EMPLOYE
    public boolean updateEmploye(Employee e) throws Exception{
        String sql = "UPDATE " + this.dbTable + " SET name=?, email=?, designation=? WHERE id=?";
        
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, e.getName());
        statement.setString(2, e.getEmail());
        statement.setString(3, e.getDesignation());
        statement.setString(4, e.getId());

        int hasil = statement.executeUpdate();
        statement.close();

        if(hasil > 0){
            return true;
        }
        return false;
    }

    // DELETE EMPLOYE
    public boolean deleteEmploye(String nip) throws Exception{
        String sql = "DELETE FROM " + this.dbTable + " WHERE id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, nip);

        int hasil = statement.executeUpdate();
        statement.close();
        if(hasil > 0){
            return true;
        }
        return false;
    }
}
