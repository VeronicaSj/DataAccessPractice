/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classfileej6guardadoadiscoaccdatveronicasancheznb;


import java.awt.Component;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author veron
 */
public class ViewElements {
    private Component parentComponent;

    public ViewElements(Component parentComponent) {
        this.parentComponent=parentComponent;
    }
    
    
    /**
     * Funcion auxiliar que muestra por pantalla un selector de archivos.
     * @param mode tipo de archivos que se ofreceran:
     * JFileChooser.FILES_ONLY; 
     * qJFileChooser.DIRECTORIES_ONLY;
     * JFileChooser.FILES_AND_DIRECTORIES. 
     * @param msg mensaje del selector de archivos
     * @return archivo seleccionado por el usuario
     */
    public File chooseFileLocation(int mode,String msg)
    {
        File res=null;
        JFileChooser fileCh=new JFileChooser(); //inicializamos el filechooser
        fileCh.setFileSelectionMode(mode); //filtramos lo que se puede elegir
        fileCh.setDialogTitle(msg);//ponemos bonito el filechooser
        
        //lanzamos el filechooser
        if(fileCh.showDialog(null, "Aceptar")==JFileChooser.APPROVE_OPTION){
            //si se ha pulsado el boton de aceptar, recogemos la seleccion
            res= fileCh.getSelectedFile();
        }
        return res;
    }
    
    /**
     * Funcion auxiliar que muestra por pantalla un mensaje de error a través de
     * un JOptionPane
     * @param msg mensaje de error
     */
    public void msgError(String msg){
        JOptionPane.showMessageDialog(this.parentComponent, msg, "Error", 
                            JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Funcion auxiliar que muestra por pantalla un mensaje a través de un 
     * JOptionPane
     * @param msg mensaje 
     */
    public void msg(String msg){
        JOptionPane.showMessageDialog(this.parentComponent, msg, "Info", 
                            JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * funcion que realiza una pregunta de seguridad al usuario a traves de un 
     * JOptionPane. 
     * @param fileNamePath mensaje de advertencia
     * @return true si el usuario ha dado el ok para el borrado. False en caso 
     * contrario
     */
    public boolean askUserConfirmation(String msg){
        boolean res = false;
        int selectedOpt=
        JOptionPane.showConfirmDialog(this.parentComponent, msg, "Alerta", 
                            JOptionPane.OK_CANCEL_OPTION);
        if (selectedOpt==JOptionPane.OK_OPTION) {
            res = true;
        }
        return res;
    }
}
