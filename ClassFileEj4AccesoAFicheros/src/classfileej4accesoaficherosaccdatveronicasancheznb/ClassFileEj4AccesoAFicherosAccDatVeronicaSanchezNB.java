/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package classfileej4accesoaficherosaccdatveronicasancheznb;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author veron
 */
public class ClassFileEj4AccesoAFicherosAccDatVeronicaSanchezNB {
    private static FileManagr fileMan; //controlador del fileSystem

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File userFileSystemLocation=null; //donde el usuario quiere contar las vocales
        
        //preguntamos al usuario donde contar las vocales
        userFileSystemLocation = chooseDirectoryLocation();
        if(userFileSystemLocation != null){//si se ha dado una localizacion 
            fileMan = new FileManagr(userFileSystemLocation);
            if (fileMan.getFileListToCountLength()>0) {//si la localizacion tiene archivos
                //intentamos contar las vocales de las dos formas propuestas
                int countFR = fileMan.getCountVocalsFileReader();
                int countBR = fileMan.getCountVocalsBuffReader();

                if(countFR >= 0){//si la cuenta de vocales con FileReader no ha dado fallo
                    if(countBR >= 0){//si la cuenta de vocales con BufferedReader no ha dado fallo
                        //damos el resultado
                        JOptionPane.showMessageDialog(null, 
                            "La cuenta de vocales con FileReader = "+countFR 
                                + "\nLa cuenta de vocales con BufferdReader = "+countBR,
                            "Resultado", JOptionPane.INFORMATION_MESSAGE);
                    }else{//si la cuenta de vocales ha dado fallo con FileReader
                        msgError("La cuenta de vocales con FileReader ha dado "
                                + "fallo");
                    }
                }else{//si la cuenta de vocales ha dado fallo con BufferedReader
                    msgError("La cuenta de vocales con BufferedReader ha dado "
                                + "fallo");
                }
                
                //preguntamos al usuario la palabra que quiere buscar
                String userWord = JOptionPane.showInputDialog(null, "introduzca la palabra que quiere "
                + "buscar: ");
                if(userWord==null){//si el usuario ha pulsado algun boton para salir de la app
                    msgClosingApp();
                }else if(userWord.length()==0){ 
                    //si no se ha escrito ningun nombre, mensage de error
                    msgError("No se ha introducido ningun nombre");
                }else{//si lo introducido por el usuario es correcto
                    //Hacemos la busqueda de la palabra del usuario
                    String wordLocation = fileMan.getWordLocation(userWord);
                    
                    if(wordLocation != null){//si la busqueda no ha dado fallo
                        //damos el resultado
                        JOptionPane.showMessageDialog(null, 
                        "La palabra \"" + userWord + "\" se encuentra en: \n" +
                            wordLocation,
                        "Resultado", JOptionPane.INFORMATION_MESSAGE);
                    }else{//si la cuenta de vocales ha dado fallo con FileReader
                        msgError("La busqueda de la palabra \"" + userWord
                                + "\" ha dado fallo");
                    }
                }
            } else { //si la localizacion no tiene archivos
                msgError("No hay archivos para leer en el directorio indicado");
            }
        }else{//si el usuario ha pulsado algun boton para salir de la app
            msgClosingApp();
        }
    }
    
    /**
     * Funcion que pregunta al usuario a, través de un JFileChooser, donde 
     * quiere hacer el recuento de vocales"
     * @return futura ubicacion de filesystem "Familia"
     */
    private static File chooseDirectoryLocation()
    {
        JFileChooser fileCh=new JFileChooser(); //inicializamos el filechooser
        //filtro para que solo podamos elegir directorios
        fileCh.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
        
        //ponemos bonito el folechooser
        fileCh.setDialogTitle("Elija donde quiere Hacer el recuento de vocales");
        
        //preguntamos la localizacionde Para crear el filesystem
        fileCh.showDialog(null, "Aceptar");

        return fileCh.getSelectedFile();
    }
    
    /**
     * Funcion que muestra por pantalla un mensaje de que la aplicacion se está
     * cerrando a través de un JOptionPane
     */
    private static void msgClosingApp(){
        JOptionPane.showMessageDialog(null, "Cerrando applicacion", "Info", 
                                JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Funcion que muestra por pantalla un mensaje de error a través de un 
     * JOptionPane
     * @param msg mensaje de error
     */
    private static void msgError(String msg){
        JOptionPane.showMessageDialog(null, msg, "Error", 
                            JOptionPane.ERROR_MESSAGE);
    }
}
