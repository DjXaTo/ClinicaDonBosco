/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.*;
import javax.swing.JOptionPane;

public class Conexion {
    private static Connection conn = null;
    private static String bd = "clinica";
    private static String user = "oreo";
    private static String password = "oreo";
    private static String  host = "85.136.228.2";      
    private static String server = "jdbc:mysql://"+host+"/"+bd;
    //Connection conn = null;
           


    public static void main(String[] args) { 
        Connection conection = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conection = DriverManager.getConnection(server, user, password);
            System.out.println("conexion a la base de datos "+server+"...ok");
        }catch (ClassNotFoundException ex){
            System.out.println("Error cargando el Driver MYSQL JDBC");
        }catch (SQLException ex){ 
           System.out.println(ex.getMessage());
        }  
    }
        
    public String consultaPrueba() {
        
        try {
            String q = "SELECT pass FROM Personas";
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            ResultSet res = pstm.executeQuery();
            res.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al iniciar sesión\n\n" + e.getMessage());
            e.printStackTrace();
        }
        return "";
    }

    public Connection getConexion() {
        try{
            if(this.conn.isClosed()){
                Class.forName("com.mysql.jdbc.Driver");
                this.conn = DriverManager.getConnection(this.server, this.user , this.password);    
            }
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return this.conn;
    }
}
