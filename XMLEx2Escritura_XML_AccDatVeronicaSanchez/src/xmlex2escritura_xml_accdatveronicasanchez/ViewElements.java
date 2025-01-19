/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xmlex2escritura_xml_accdatveronicasanchez;

import java.awt.Component;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * CLASE RESCATADA DE EJERCICIOS ANTERIORES.
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
    public static File chooseFileLocation(int mode,String msg, String extensionFilter)
    {
        File res=null;
        JFileChooser fileCh=new JFileChooser(); //inicializamos el filechooser
        //filtramos lo que se puede elegir
        fileCh.setFileSelectionMode(mode);
        fileCh.setFileFilter(
                new FileNameExtensionFilter(extensionFilter, extensionFilter));
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

}