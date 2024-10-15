/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package classfileej6guardadoadiscoaccdatveronicasancheznb;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author veron
 */
public class ClassFileEj6GuardadoADiscoAccDatVeronicaSanchezNB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FileManager fm=new FileManager();//Clases que controla los ficheros
        ViewElements viewE =new ViewElements(null);//clase que controla la vista
        
        //elegimos el archivo que queremos normalizar
        File userFile=viewE.chooseFileLocation(JFileChooser.FILES_ONLY, 
                "Seleccione el archivo que quiere nprmalizar: ");
        if (userFile!=null) {//validamos seleccion
            if (viewE.askUserConfirmation("¿Estás seguro?")) {//pedimos confirmacion
                //pedimos normalizacion
                if (fm.normaliceFile(userFile)) {//si ha ido bien, msg ok
                    viewE.msg("Archivo normalizado correctamente");
                }else{//si ha ido mal,msg error
                    viewE.msgError("Error durante la normalizacion, por favor, "
                            + "revise la integrida de su archivo");
                }
            }
        }
    }
    
    
}
