/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package Modelo;

import java.sql.*;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ciclo
 */
public class Modelo extends Database {
    
    /** Constructor de clase */
    public Modelo (){
    }
    
    //MÉTODO INCIO DE SESIÓN
    public boolean iniciarSesion(String nif, String pass) {
        String contraseña = "";
        try {
            String q = "SELECT pass FROM Personas WHERE nif = '" + nif + "'";
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            ResultSet res = pstm.executeQuery();
            while (res.next()) {
                contraseña = res.getString("pass");
            }
            res.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al iniciar sesión\n\n" + e.getMessage());
            e.printStackTrace();
        }
        if (pass.equals(contraseña)) {
            return true;
        } else {
            return false;
        }
    }
    
    //MÉTODO PARA COMPROBAR SI EL USUARIO INICIADO ES ADMINISTRADOR O MEDICO
    public boolean esAdmin(String nif) {
        int ad = 0;
        try {
            String q = "SELECT tipo FROM Empleado WHERE nif = '" + nif + "'";
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            ResultSet res = pstm.executeQuery();
            while (res.next()) {
                ad = res.getInt("tipo");
            }
            res.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al iniciar sesión\n\n" + e.getMessage());
            e.printStackTrace();
        }
        if (ad == 1) {
            return true;
        } else {
            return false;
        }
    }
    
    public DefaultListModel listaCitas(String fecha) {
        DefaultListModel listmodel = new DefaultListModel();
        try {
            String q = "SELECT idCita FROM Citas WHERE fecha = '" + fecha + "'";
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            ResultSet res = pstm.executeQuery();
            while (res.next()) {
                String cod = String.valueOf(res.getInt("idCita"));
                listmodel.addElement(cod);
            }
            res.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener datos\n\n" + e.getMessage());
            e.printStackTrace();
        }
        return listmodel;
    }
    
    
    
    
    //MÉTODO PARA HACER QUE LAS CELDAS DE LAS TABLAS NO SEAN EDITABLES
    public class ModeloTablaNoEditable extends DefaultTableModel {

        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }
    

    /** Obtiene registros de la tabla PRODUCTO y los devuelve en un DefaultTableModel*/
    public DefaultTableModel getPaciente()
    {
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"idPaciente","nif","aseguradora"};
      //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
      //para formar la matriz de datos
      try{
         PreparedStatement pstm = this.getConexion().prepareStatement( "SELECT count(*) as total FROM Paciente");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
    //se crea una matriz con tantas filas y columnas que necesite
    Object[][] data = new String[registros][3];
      try{
          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
         PreparedStatement pstm = this.getConexion().prepareStatement("SELECT * FROM Paciente");
         ResultSet res = pstm.executeQuery();
         int i=0;
         while(res.next()){
                data[i][0] = res.getString( "idPaciente" );
                data[i][1] = res.getString( "nif" );
                data[i][2] = res.getString( "aseguradora" );
                
            i++;
         }
         res.close();
         //se añade la matriz de datos en el DefaultTableModel
         tablemodel.setDataVector(data, columNames );
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return tablemodel;
    }

    /** Registra un nuevo producto */
    public boolean NuevoPaciente(String idPaciente, int nif , String aseguradora)
    {
       
        
            
            //Se arma la consulta
            String q=" INSERT INTO Paciente ( idPaciente , nif , aseguradora) "
                    + "VALUES ( '" + idPaciente + "','" + nif + "', '" + aseguradora + ") ";
            //se ejecuta la consulta
            try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                pstm.execute();
                pstm.close();
                return true;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
            }
            return false;
        
        
    }


    /** Elimina un registro dado su ID -> Llave primaria */
    public boolean EliminarPaciente( String idPaciente )
    {
         boolean res=false;
        //se arma la consulta
        String q = " DELETE FROM Paciente WHERE  idPaciente='" + idPaciente + "'";
        //se ejecuta la consulta
         try {
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            res=true;
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return res;
    }

    /** Metodo privado para validar datos */

  }
        
        
       
         
    
    

    


        
   
        
      
        
        
    
    
    
   
    
    

