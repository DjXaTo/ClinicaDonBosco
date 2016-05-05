/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package Modelo;

import java.sql.*;

/**
 *
 * @author Ciclo
 */
public class Modelo extends Database {
    
    public void insertPaciente(String id, int nif, String aseguradora) {
        try {
            String sql = "INSERT INTO Paciente (id, nif, aseguradora) VALUES"
                    + " ('" + id + "','" + nif + "','" + aseguradora + "',juan,45678903,Adeslas)";
            PreparedStatement pstm = this.getConexion().prepareStatement(sql);
            pstm.execute();
            pstm.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
   
        
      
        
        
    }
    
    
   
    
    
}
