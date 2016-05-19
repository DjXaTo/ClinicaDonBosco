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
        int admin = 0;
        try {
            String q = "SELECT tipo FROM Empleado WHERE nif = '" + nif + "'";
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            ResultSet res = pstm.executeQuery();
            while (res.next()) {
                admin = res.getInt("tipo");
            }
            res.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al iniciar sesión\n\n" + e.getMessage());
            e.printStackTrace();
        }
        if (admin == 1) {
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
    
    public Object[] getInfoEmpleados(String nif) {
        Object[] info = new Object[6];
        try {
            String q = "SELECT e.idEmpleado, p.nombre, p.apellidos, p.fechaNac, p.telefono, p.direccion FROM Empleado e, Personas p WHERE p.nif = '" + nif + "'";
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            ResultSet res = pstm.executeQuery();
            while (res.next()) {
                info[0] = res.getInt("e.idEmpleado");
                info[1] = res.getString("p.nombre");
                info[2] = res.getString("p.apellidos");
                info[3] = res.getString("p.fechaNac");
                info[4] = res.getDouble("p.telefono");
                info[5] = res.getString("p.direccion");
            }
            res.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener datos\n\n" + e.getMessage());
            e.printStackTrace();
        }
        return info;
    }
    
    public Object[] getInfoPorIDCita(String idCita, int nif) {
        Object[] info = new Object[8];
        try {
            String q = "SELECT c.idCita, c.idPaciente, p.nombre, p.apellidos, c.idEmpleado, c.fecha, c.motivo, c.recibo FROM Citas c, Personas p WHERE idCita = " + idCita + " AND p.nif = " + nif;
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            ResultSet res = pstm.executeQuery();
            while (res.next()) {
                info[0] = res.getInt("c.idCita");
                info[1] = res.getString("c.idPaciente");
                info[2] = res.getString("p.nombre");
                info[3] = res.getString("p.apellidos");
                info[4] = res.getString("c.idEmpleado");
                info[5] = res.getString("c.fecha");
                info[6] = res.getDouble("c.motivo");
                info[7] = res.getString("c.recibo");
                }
            res.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener datos de cita\n\n" + e.getMessage());
            e.printStackTrace();
        }
        return info;
    }
    
    
    
    
    
    
    
    
    //MÉTODO PARA HACER QUE LAS CELDAS DE LAS TABLAS NO SEAN EDITABLES
    public class ModeloTablaNoEditable extends DefaultTableModel {

        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }
    

    /** Obtiene registros de la tabla Personas y los devuelve en un DefaultTableModel*/
    public DefaultTableModel getPaciente()
    {
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"nombre","aseguradora", "telefono", "direccion", "nif"};
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
    Object[][] data = new String[registros][5];
      try{
          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
         PreparedStatement pstm = this.getConexion().prepareStatement("SELECT p.nombre, b.aseguradora, p.telefono, p.direccion, p.nif FROM Personas p, Paciente b");
         ResultSet res = pstm.executeQuery();
         int i=0;
         while(res.next()){
                
                data[i][0] = res.getString( "p.nombre" );
                data[i][1] = res.getString( "b.aseguradora" );
                data[i][2] = res.getString( "p.telefono");
                data[i][3] = res.getString( "p.direccion");
                data[i][4] = res.getString( "p.nif");
                
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
    public DefaultTableModel getPersona()
    {
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"nombre", "apellidos", "fechaNac" ,"aseguradora", "telefono", "direccion", "nif"};
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
    Object[][] data = new String[registros][7];
      try{
          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
         PreparedStatement pstm = this.getConexion().prepareStatement("SELECT p.nombre, p.apellidos, p.fechaNac, b.aseguradora, p.telefono, p.direccion, p.nif FROM Personas p, Paciente b");
         ResultSet res = pstm.executeQuery();
         int i=0;
         while(res.next()){
                
                data[i][0] = res.getString( "p.nombre" );
                data[i][1] = res.getString( "p.apellidos" );
                data[i][2] = res.getString( "p.fechaNac" );
                data[i][3] = res.getString( "b.aseguradora" );
                data[i][4] = res.getString( "p.telefono");
                data[i][5] = res.getString( "p.direccion");
                data[i][6] = res.getString( "p.nif");
                
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


     
    public boolean NuevaPersona(int DNI,String Nombre ,String Apellidos, Date fechaNac, int telefono, String direccion)
    {
       
        
            
            //Se arma la consulta
            String q=" INSERT INTO Personas (nif, nombre, apellidos, fechaNac, telefono, direccion) "
                    + "VALUES (" + DNI + "','" + Nombre + "', '" + Apellidos + "', '" + fechaNac + "', '" + telefono + "', '" + direccion + ") ";
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
    public boolean EliminarPersona( String nombre )
    {
         boolean res=false;
        //se arma la consulta
        String q = " DELETE FROM Paciente WHERE  nombre='" + nombre + "'";
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
        
        
       
         
    
    

    


        
   
        
      
        
        
    
    
    
   
    
    

