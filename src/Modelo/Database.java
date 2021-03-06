
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static String  host = "85.136.228.2";  
    private String db = "clinica";
    private String user = "oreo";
    private String password = "oreo";
    private String url = "jdbc:mysql://"+host+"/"+db;
    private Connection conn = null;
    
    public void conectar(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(this.url, this.user , this.password);    
            System.out.println("Conexion MySQL realizada con exito.");
        }catch(SQLException e){
            System.out.println("Conexion NO realizada con exito por error de SQL.");
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            System.out.println("Conexion NO realizada con exito por error de Class.");
            e.printStackTrace();
        }
    }

    //Aplicamos el funcionamiento de Singleton para evitar crear un segundo objeto de Conexión mediante la reutilización del ya existente
    public Connection getConexion() {
        try{
            if(this.conn.isClosed()){
                Class.forName("com.mysql.jdbc.Driver");
                this.conn = DriverManager.getConnection(this.url, this.user , this.password);    
            }
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return this.conn;
    }
    
    public void setDatabase(String db){
        this.db = db;
    }
    
    public void setUser(String user){
        this.user = user;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public void setURL(String ip, String puerto){
        this.url = "jdbc:mysql://" + ip + ":" + puerto + "/" + db;
    }
}