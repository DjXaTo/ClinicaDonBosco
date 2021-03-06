
package Controlador;

import Modelo.Modelo;
import Vista.Interfaz;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
//import java.util.Date;


public class Controlador implements ActionListener, MouseListener {

    Interfaz vista ; //PARA PODER ACCEDER A LA INTERFAZ DE LA APLICACIÓN
    Modelo modelo = new Modelo(); //PARA PODER ACCEDER AL MODELO Y A LOS DATOS DE LA BD

    public Controlador(Interfaz i) {
        this.vista = i;
        
    }
    
    public enum Clinica
    {
        //Inicio de sesion
        btnConectar,//
        btnSalir, //
        //Frame de administrativos
            //Panel de botones
        btnPrincipal,
        btnCitas,
        btnPersonas,
        btnPacientes,
        btnPersonal,
        btnSalirAdmin, //
        btnListarPac, //
        btnModificarPac, //
        btnListarPer, //
        btnconverPac, //
        btnconverEm, //
        btnModificarPer, //
        
            //Panel principal
            //Panel personas
        
            //Panel pacientes
        
            //Panel empleados
            
            //Panel citas
        btnCrearCita
        
        //Frame de medicos
            //Panel principal
        
    }
    public void iniciar() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(vista);
            vista.setVisible(true);
        } catch (UnsupportedLookAndFeelException ex) {}
          catch (ClassNotFoundException ex) {}
          catch (InstantiationException ex) {}
          catch (IllegalAccessException ex) {}
        
        //this.vista.btnlistarPer.setActionCommand("btnlistarPer");
        //this.vista.btnlistarPer.addActionListener((ActionListener) this);
        
        this.vista.btnCrearCita.setActionCommand("btnCrearCita");
        this.vista.btnCrearCita.addActionListener((ActionListener) this);
        
        this.vista.btnModificarPac.setActionCommand("btnModificarPac");
        this.vista.btnModificarPac.addActionListener((ActionListener) this);
        
        this.vista.btnModificarPer.setActionCommand("btnModificarPac");
        this.vista.btnModificarPer.addActionListener((ActionListener) this);
        
        this.vista.btnconverEm.setActionCommand("btnconverEm");
        this.vista.btnconverEm.addActionListener((ActionListener) this);
        
        this.vista.btnlistarPac.setActionCommand("btnlistarPac");
        this.vista.btnlistarPac.addActionListener((ActionListener) this);
        
        this.vista.btnconverPac.setActionCommand("btnconverPac");
        this.vista.btnconverPac.addActionListener((ActionListener) this);
        
        //this.vista.btnSalirAdmin.setActionCommand("btnSalirAdmin");
        //this.vista.btnSalirAdmin.addActionListener((ActionListener) this);
        
        this.vista.btnSalir.setActionCommand("btnSalir");
        this.vista.btnSalir.addActionListener((ActionListener) this);
        
        this.vista.btnConectar.setActionCommand(" btnConectar");
        this.vista.btnConectar.addActionListener((ActionListener) this);
        
        
        
        
        
        
      
        
        
        //CONTROLAMOS OTRAS NECESIDADES COMO LOS MOUSELISTENER O LA VISIBILIDAD DE ALGUNOS PANELES            

        vista.tablaPacientes.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                
                if( e.getButton()== 1)//boton izquierdo
                {
                    int filaPaciente = vista.tablaPacientes.rowAtPoint(e.getPoint());
                    int filaPersona = vista.tablaPersonas.rowAtPoint(e.getPoint());
                    Date date = new Date (vista.tablaPersonas.getValueAt(vista.tablaPersonas.getSelectedRow(), 5).toString).toString());
                    date = new SimpleDateFormat("dd-MM-yyyy").parse(dateValue);
                    if (filaPaciente > -1){                
                        vista.txtDNIPacientes.setText(vista.tablaPacientes.getValueAt(vista.tablaPacientes.getSelectedRow(), 0).toString());
                        vista.txtaseguradoraPac.setText(vista.tablaPacientes.getValueAt(vista.tablaPacientes.getSelectedRow(), 0).toString());
                
                        if (filaPersona >-1){
                            vista.txtDNIpersona.setText(vista.tablaPersonas.getValueAt(vista.tablaPersonas.getSelectedRow(), 0).toString());
                            vista.txtnombrePer.setText(vista.tablaPersonas.getValueAt(vista.tablaPersonas.getSelectedRow(), 0).toString());
                            vista.txtapellidosPer.setText(vista.tablaPersonas.getValueAt(vista.tablaPersonas.getSelectedRow(), 0).toString());
                            vista.txtdireccionPer.setText(vista.tablaPersonas.getValueAt(vista.tablaPersonas.getSelectedRow(), 0).toString());
                            vista.txttelefonoPer.setText(vista.tablaPersonas.getValueAt(vista.tablaPersonas.getSelectedRow(), 0).toString());
                            vista.txtfechanacPer.setDate(date);
                            //vista.txtfechanacPer.setDate(vista.tablaPersonas.getValueAt(vista.tablaPersonas.getSelectedRow(), 0).toDate());
                 
                 
                        }
             
                    }
                
                }
            }
        });
        
        vista.btnSalir.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                
            }
        });
        
//        vista.btnPrincipal.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e){
//                 vista.panelPrincipal.setVisible(true);
//                 vista.panelCitas.setVisible(false);
//                 vista.panelPersonas.setVisible(false);
//                 vista.panelPacientes.setVisible(false);
//                 vista.panelPersonal.setVisible(false);
//            }
//
//            @Override
//            public void mousePressed(MouseEvent e) {
//                vista.btnPrincipal.setBorder(BorderFactory.createLineBorder(Color.black));
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//                vista.btnPrincipal.setBorder(null);
//            }
//        });
//        
//        vista.btnCitas.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e){
//                 vista.panelPrincipal.setVisible(false);
//                 vista.panelCitas.setVisible(true);
//                 vista.panelPersonas.setVisible(false);
//                 vista.panelPacientes.setVisible(false);
//                 vista.panelPersonal.setVisible(false);
//            }
//
//            @Override
//            public void mousePressed(MouseEvent e) {
//                vista.btnPrincipal.setBorder(BorderFactory.createLineBorder(Color.black));
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//                vista.btnPrincipal.setBorder(null);
//            }
//        });
//        
//        vista.btnPersonas.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e){
//                 vista.panelPrincipal.setVisible(false);
//                 vista.panelCitas.setVisible(false);
//                 vista.panelPersonas.setVisible(true);
//                 vista.panelPacientes.setVisible(false);
//                 vista.panelPersonal.setVisible(false);
//            }
//
//            @Override
//            public void mousePressed(MouseEvent e) {
//                vista.btnPrincipal.setBorder(BorderFactory.createLineBorder(Color.black));
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//                vista.btnPrincipal.setBorder(null);
//            }
//        });
//        
//        vista.btnPacientes.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e){
//                 vista.panelPrincipal.setVisible(false);
//                 vista.panelCitas.setVisible(false);
//                 vista.panelPersonas.setVisible(false);
//                 vista.panelPacientes.setVisible(true);
//                 vista.panelPersonal.setVisible(false);
//            }
//
//            @Override
//            public void mousePressed(MouseEvent e) {
//                vista.btnPrincipal.setBorder(BorderFactory.createLineBorder(Color.black));
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//                vista.btnPrincipal.setBorder(null);
//            }
//        });
//        
//        vista.btnPersonal.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e){
//                 vista.panelPrincipal.setVisible(false);
//                 vista.panelCitas.setVisible(false);
//                 vista.panelPersonas.setVisible(false);
//                 vista.panelPacientes.setVisible(false);
//                 vista.panelPersonal.setVisible(true);
//            }
//
//            @Override
//            public void mousePressed(MouseEvent e) {
//                vista.btnPrincipal.setBorder(BorderFactory.createLineBorder(Color.black));
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//                vista.btnPrincipal.setBorder(null);
//            }
//        });
//        
//        vista.btnDesconectar.addMouseListener(new MouseAdapter() {
//        
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                vista.frameAdmin.setVisible(false);
//                //vista.JFrame.setVisible(true);
//            }
//
//            @Override
//            public void mousePressed(MouseEvent e) {
//                vista.btnDesconectar.setBorder(BorderFactory.createLineBorder(Color.black));
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//                vista.btnDesconectar.setBorder(null);
//            }
//        });
//      
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         switch ( Clinica.valueOf( e.getActionCommand() ) )
        {
            case btnListarPac:
                //obtiene del modelo los registros en un DefaultTableModel y lo asigna en la vista
                this.modelo.getPaciente();
                       
                break;
            case btnListarPer:
                this.modelo.getPersona();
               
                break;
            case btnModificarPac:
                this.modelo.modificarPac(vista.txtaseguradoraPac.getText());
               
                break;
            case btnModificarPer:
                this.modelo.modificarPer(vista.txtnombrePer.getText(),vista.txtapellidosPer.getText(),(Date) vista.txtfechanacPer.getDate(),Integer.parseInt(vista.txttelefonoPer.getText()),vista.txtdireccionPer.getText());
                
                break;
            case btnconverEm:
                break;
                
            case btnCrearCita:
                this.vista.dialogCita.setVisible(true);
                break;
                
        }
        
       
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    //ESTE MÉTODO SERVIRÁ PARA LIMITAR LA ESCRITURA DE CIFRAS EN UN JTEXTFIELD
    public static void SCifras(JTextField a) {
        a.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isLetter(c)) {
                    e.consume();
                }
            }
        });
    }

    //ESTE MÉTODO SERVIRÁ PARA LIMITAR LA ESCRITURA DE LETRAS EN UN JTEXTFIELD
    public static void SLetras(JTextField a) {
        a.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
    }
}
