/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package Modelo;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ciclo
 */
public class Modelo extends Database {
    
    
    public class ModeloTablaNoEditable extends DefaultTableModel {

        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }
    
    
    public void insertPaciente(String id, int nif, String aseguradora) {
        try {
            String q = "INSERT INTO Paciente (id, nif, aseguradora) VALUES"
                    + " ('" + id + "','" + nif + "','" + aseguradora + "',juan,45678903,Adeslas)";
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
        
    public DefaultTableModel tablaPacientes() {
        DefaultTableModel tablemodel = new ModeloTablaNoEditable();
        tablemodel = new DefaultTableModel();
        try {
            tablemodel.addColumn("id");
            tablemodel.addColumn("nif");
            tablemodel.addColumn("aseguradora");

           
             String q = "idPaciente, NIF, Aseguradora FROM Paciente";
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            ResultSet res = pstm.executeQuery();

            String[] data = new String[3];

            while (res.next()) {
                data[0] = res.getString("ID");
                data[1] = res.getString("NIF");
                data[2] = res.getString("Aseguradora");
                tablemodel.addRow(data);
            }

            res.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener datos\n\n" + e.getMessage());
            e.printStackTrace();
        }
        return tablemodel;
    }
}

        
   
        
      
        
        
    
    
    
   
    
    

