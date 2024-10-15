/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classfileej2creadoborradoaccdatveronicasancheznb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author veron
 */
public class FileManagr {
    private static File fileSystem = null;//userDir+"//familia"
    private static String[] fsGen1 = {"Abuelo", "Abuela"};
    private static String[] fsGen2 = {"Padre", "Madre"};
    private static String[] fsGen3 = {"Hijo", "Hija"};
    private static int fsGen3Count = 0;

    public FileManagr(File parent) {
        //quiero trabajar en la carpeta familia  para trabajar de una forma un 
        //poco más "segura"
        fileSystem = new File(parent.getAbsolutePath()+"//Familia");
    }
    
    /**
     * Funcion que genera el sistema de archivos en la ruta fyleSystem 
     * (atributo de la clase). Devuelve un booleano indicando si el resultado 
     * de la operacion ha sido correcto
     * @return True si el fileSystem se ha creado correctamente. 
     * False en caso contrario
     */
    public static boolean createFileSystem()
    {
        //variables
        boolean res= true;
        File auxFilePointer=null;
        String pathFamily = "";
        String pathGen1 ="";

        //crear carpeta familia para meterle el fileSystem
        auxFilePointer = fileSystem;
        auxFilePointer.mkdir();
        
        //recogemos la ruta del fichero auxiliar para luego ir editandola con 
        //la lista de la siguiente generacion
        pathFamily=auxFilePointer.getAbsolutePath();
        
        //recorremos la lista de la siguiente generacion
        for(int i = 0 ; i<fsGen1.length; i++)
        {
            //creamos las subcarpetas de la correspondiente generacion
            auxFilePointer = new File(pathFamily+"//"+fsGen1[i]);
            auxFilePointer.mkdir();
            
            //recogemos la ruta del fichero auxiliar para luego ir editandola con 
            //la lista de la siguiente generacion.
            pathGen1=auxFilePointer.getAbsolutePath();
            
            //recorremos la lista de la siguiente generacion
            for(int j = 0 ; j<fsGen2.length; j++)
            {
                //creamos las subcarpetas de la correspondiente generacion
                auxFilePointer = new File(pathGen1+"//"+fsGen2[j]);
                auxFilePointer.mkdir();
                
                //recorremos la lista de la siguiente generacion
                for(int k = 0 ; k<fsGen3.length; k++)
                {
                    fsGen3Count++;//ajustamos el numerito del archivo 
                    //creamos el puntero
                    File auxTxtFile = new File(auxFilePointer.getAbsolutePath()+"//"+fsGen3[k]+fsGen3Count+".txt");
                    
                    //intentamos crear el archivo
                    try
                    {
                        auxTxtFile.createNewFile();
                    }
                    catch(IOException e) 
                    {   
                        res= false;//informamos en caso de error
                    }
                }
            }
        }
        
        return res;
    }
    
    /**
     * Funcion que encapsula la busqueda del directorio "Familia"
     * @return true si el Directorio existe. False en caso contrario
     */
    public static boolean findFamilyFile()
    {
        return fileSystem.exists();
    }
    
    /**
     * Funcion que encapsula la busqueda recursiva deun fichero dentro del 
     * fileSystem
     * @param fileName nombre del fichero que buscamos
     * @return puntero con la ruta completa del directorio que estamos buscado. 
     * Null en caso de que el directorio no exista
     */
    public File findFile(String fileName)
    {
        return recursiveFindFile(fileSystem, fileName);
    }
    
    /**
     * Funcion auxiliar recursiva que recorre el fyleSystem y busca un archivo 
     * con el nombre pasado por parametro. 
     * @param parentfile carpeta que queremos comprobar
     * @param fileName nombre del archivo que estamos buscando
     * @return puntero con la ruta completa del directorio que estamos buscado. 
     * Null en caso de que el directorio no exista
     */
    private File recursiveFindFile(File parentfile, String fileName)
    {File locationRes= null;
            File[] sonFileList=parentfile.listFiles();//obtenemos los subfiles
            //si todavia no hemos encontrado el archivo, recorremos la lista de subfiles
            for (int i = 0; i < sonFileList.length &&
                    locationRes==null; i++) {
                if(sonFileList[i].isDirectory()){//si el siguiente nivel es un directorio
                    //pasamos al siguiente nivel de recursividad
                    locationRes=recursiveFindFile(sonFileList[i], fileName);
                }
                System.out.println(sonFileList[i].getName()+" "+ fileName);
                //si el nombre del file del siguiente nivel coincide con el que buscamos
                if (sonFileList[i].getName().equals(fileName)) {
                    //damos la señal de que lo hemos encontrado
                    locationRes=sonFileList[i];
                    break;
                }
            }
        return locationRes;
    }
    
    /**
     * Funcion que encapsula el borrado en cascada (Hecho con una funcion 
     * recursiva) de la ruta pasada por parametro
     * @param userFile Ruta que queremos borrar
     */
    public void deleteFile(File userFile) 
    {
        recursiveDeleteFile(userFile);
    }
    
    /**
     * Funcion auxiliar recursiva que recorre y borra un directorio o un archivo.
     * Si el parametro pasado no tiene nada por debajo lo borra, si tiene algo, 
     * lo recorre y pasa por parametro los subfiles a esta misma funcion
     * 
     * ESTOY PRESUPONIENDO QUE LOS ARCHIVOS DE TEXTO SIEMPRE ESTAN VACIOS. 
     * @param parentfile 
     */
    private void recursiveDeleteFile(File parentfile)
    {
        if(parentfile.isDirectory()){//si el parametro pasado es un directorio
            File[] sonFileList=parentfile.listFiles(); //obtenemos los subfiles
            for (int i = 0; i < sonFileList.length; i++) { //recorremos la lista de subfiles
                //pasamos al siguiente nivel de la funcion recursiva
                recursiveDeleteFile(sonFileList[i]);
            }
        }
        //cuando no encontramos nada por debajo del archivo pasado por parametro
        parentfile.delete();//lo borramos
    }
}
