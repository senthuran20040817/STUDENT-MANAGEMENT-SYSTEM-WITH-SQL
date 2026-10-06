/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagementsystemmysql;
import java.sql.*;
import java.sql.DriverManager;
/**
 *
 * @author SENTHURAN
 */
public class DBConnection {
    private static DBConnection dBConnection;
    private Connection connection;
            
    private DBConnection() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost/student_management_system","root","12345");
    }
    
    public static DBConnection getInstance() throws ClassNotFoundException, SQLException{
        if(dBConnection == null){
            dBConnection = new DBConnection();
        }
        return dBConnection;
    }
            
    public Connection getConnection(){
        return connection;
    }      
            
            
            
            
}
