/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package classfileej2creadoborradoaccdatveronicasancheznb;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Clase PRINCIPAL. Contiene la interfaz de usuario. 
 * No he creado una interfaz completa porque este ejercicio era muy secuencial 
 * y no hacia falta una pantalla principal
 * @author veron
 */
public class ClassFileEj2CreadoBorradoAccDatVeronicaSanchezNB {
    private static FileManagr fileMan; //controlador del fileSystem
    
    public static void main(String[] args)
    {
        File userFileSystemLocation=null; //donde el usuario quiere crear el fileSystem
        String deleteFileName="";//nombre del usuario que vamos a borrar
        String fileOrDir = "";//tipo de file que vamos a borrar

        //preguntamos al usuario donde crear el fileSystem Familia
        userFileSystemLocation = chooseFileSystemLocation();
        if(userFileSystemLocation != null){//si se ha dado una localizacion 
            fileMan = new FileManagr(userFileSystemLocation);
            //buscar si en el directorio introducido ya existe la carpeta familia
            //si la carpeta Familia existe, no deberiamos crearla de nuevo
            if (fileMan.findFamilyFile()==false) { // carpeta familia no existe
                //creamos el filesystem, puede dar error
                if(fileMan.createFileSystem()){ //intentamos crear el fileSystem
                    //preguntar si el usuario quiere borrar un archivo o un directorio
                    fileOrDir = askUserFileOrDir();
                    if (fileOrDir!=null) {//si se ha introducido un tipo de file
                        deleteFileName = askUserFileToDelete(fileOrDir);
                        if(deleteFileName==null){//si el usuario ha pulsado algun boton para salir de la app
                            msgClosingApp();
                        }else if(deleteFileName.length()==0){ 
                            //si no se ha escrito ningun nombre, mensage de error
                            msgError("No se ha introducido ningun nombre");
                        }else{//si lo introducido por el usuario es correcto
                            //buscar si el archivo del usuario existe
                            File fileToDelete = fileMan.findFile(deleteFileName);
                            if(fileToDelete!=null){//si el archivo existe
                                //pregunta de seguridad
                                if (askUserSafeDelete(fileToDelete.getAbsolutePath())) {
                                    //si el usuario está de acuerdo, borramos
                                    fileMan.deleteFile(fileToDelete);
                                } else {//si el usuario ha pulsado algun boton para salir de la app
                                    msgClosingApp();
                                }
                            } else { //si el archivo no existe
                                msgError("El archivo indicado no existe. "
                                        + "Cerrando applicacion");
                            }
                        }
                    }else{//si el usuario ha pulsado algun boton para salir de la app
                        msgClosingApp();
                    }
                }else{ //caso de error al crear el FileSystem
                    msgError("Ha ocurrido un error durante la creacion del "
                                    + "FileSystem");
                }
            }else{ //carpeta familia existe
                msgError("La carpeta Familia ya existe dentro de la carpeta"
                                + " indicada. Cerrando applicacion");
            }
        }else{//si el usuario ha pulsado algun boton para salir de la app
            msgClosingApp();
        }
                
    }

    /**
     * Funcion que pregunta al usuario a, través de un JFileChooser, donde 
     * quiere crear el filesystem "Familia"
     * @return futura ubicacion de filesystem "Familia"
     */
    private static File chooseFileSystemLocation()
    {
        JFileChooser fileCh=new JFileChooser(); //inicializamos el filechooser
        //filtro para que solo podamos elegir directorios
        fileCh.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
        
        //ponemos bonito el folechooser
        fileCh.setDialogTitle("Elija donde quiere crear los archivos de la familia");
        
        //preguntamos la localizacionde Para crear el filesystem
        fileCh.showDialog(null, "Aceptar");

        return fileCh.getSelectedFile();
    }

    /**
     * Funcion que pregunta al usuario a través de un JOptionPane  que File quiere borrar a 
     * través del nombre.
     * @param userFileType "Archivo" si se quiere borrar un archivo
     * @return nombre del archivo que el usuario quiere borrar
     */
    private static String askUserFileToDelete(String userFileType)
    {
        String fileDeleteName = "";

        fileDeleteName = JOptionPane.showInputDialog(null, "introduzca el "
                + "NOMBRE del "+userFileType+" para borrar: ");
        if (fileDeleteName !=null) {//si se ha dado un nombre de fichero
            if (fileDeleteName.length()>0 //si se ha escrito algun nombre y
                    && userFileType=="Archivo"//si queremos borrar un archivo y
                    && !fileDeleteName.contains(".")){ //si no se ha escrito extension
                //si no se ha escrito extension la ponemos nosotros 
                fileDeleteName = fileDeleteName + ".txt";
            }
        }

        return fileDeleteName;
    }

    /**
     * Funcion que pregunta a trvés de un JOptionPane al user si quiere borrar
     * un directorio o un archivo.
     * @return "Directorio" o "Archivo" dependiendo de la eleccion del usuario
     */
    private static String askUserFileOrDir()
    {
        String res;
        
        //preguntamos al user si quiere borrar un archivo o un directorio
        Object optPaneRes= JOptionPane.showInputDialog(null, 
                "¿Quiere borrar un archivo o un directorio?", 
                "PREGUNTA", JOptionPane.QUESTION_MESSAGE, null, 
                new Object[] {"Directorio", "Archivo"}, 
                new Object());
        
        //si se ha introducido algo 
        if (optPaneRes!=null) {
            res= optPaneRes.toString();//retornamos el texto de lo introducido
        }else{//si se ha pulsado algun boton para salir de la app
            res=null;//retornamos null
        }
        return res; 
    }
    
    /**
     * funcion que realiza una pregunta de seguridad al usuario a traves de un 
     * JOptionPane. Esta función evita que se realicen borrados por error
     * @param fileNamePath Ruta de la que queremos confirmar el borrado
     * @return true si el usuario ha dado el ok para el borrado. False en caso 
     * contrario
     */
    private static boolean askUserSafeDelete(String fileNamePath){
        boolean res = false;
        int selectedOpt=
        JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres "
                + "borrar la ruta \""+fileNamePath+"\" ?", "Alerta", 
                            JOptionPane.OK_CANCEL_OPTION);
        if (selectedOpt==JOptionPane.OK_OPTION) {
            res = true;
        }
        return res;
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
