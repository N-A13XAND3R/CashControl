
package com.unal.cash.Database.datos;
import java.sql.*;

public class Conexion {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/test?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "admin";
    
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }
    
    public static void close(ResultSet rs) throws SQLException{
        rs.close();
    }
    
    public static void close(PreparedStatement smtm) throws SQLException{
        smtm.close();
    }
    
    public static void close(Connection conn) throws SQLException{
        conn.close();
    }
    
    public static void close(Statement smtm) throws SQLException{
        smtm.close();
    }
    public static void main(String[] args) {
        try {
            Connection c = Conexion.getConnection();
            System.out.println("coneccion exitosa: " +  c.toString());
            PersonaDAO p = new PersonaDAO();
            System.out.println("data: : " +  p.seleccionar().toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
