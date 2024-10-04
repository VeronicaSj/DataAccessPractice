/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classfileej3lecturaficherossecuencialaccdatveronicasancheznb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author veron
 */
public class FileManager {
    private static File userDir = null;
    private static ArrayList<File> fileListToCount = new ArrayList<File>();
    

    public FileManager(File userDir) {
        this.userDir=userDir;
        auxRecursiveSetSonFileList(userDir);
    }
    
    /**
     * Getter del tamaño del Array fileListToCount
     * @return tamaño del Array fileListToCount
     */
    public int getFileListToCountLength(){
        return fileListToCount.size();
    }
            
    /**
     * Funcion que devuelve el recuento de vocales de los ficheros dentro del 
     * directorio userDir (Atributo de esta clase). Hace uso de la Clase 
     * FileReader
     * @return recuento de vocales del directorio userDir 
     */
    public int getCountVocalsFileReader(){
        int res = 0;
        for (int i = 0; i < fileListToCount.size(); i++) {
            res = res + auxCountVocalsFileReader(fileListToCount.get(i));
        }
        return res;
    }
    
    /**
     * Funcion que devuelve el recuento de vocales de los ficheros dentro del 
     * directorio userDir (Atributo de esta clase). Hace uso de la Clase 
     * BuffReader
     * @return recuento de vocales del directorio userDir 
     */
    public int getCountVocalsBuffReader(){
        int res = 0;
        for (int i = 0; i < fileListToCount.size(); i++) {
            res = res + auxCountVocalsBuffReader(fileListToCount.get(i));
        }
        return res;
    }
    
    /**
     * Funcion auxiliar que hace elrecuento de vocales de un fichero pasado por
     * parametro
     * Hace uso de la Clase FileReader
     * @param file archivo del que queremos hacer el recuento de vocales
     * @return recuento de vocales
     */
    private int auxCountVocalsFileReader(File file){
        int res = 0;
        FileReader fr = null;
        
        try {//intentamos crear el reader
            fr = new FileReader( file );
            int letra;
            
            try {//la funcion read puede dar error
                
                letra = fr.read();
                while( letra != -1 )
                {
                    if(letra=='a' || letra=='e' || letra=='i' || 
                            letra=='o'|| letra=='u' || letra=='A' || letra=='E' 
                            || letra=='I' || letra=='O'|| letra=='U' ){
                        res++;
                    }
                    // LEEMOS LO ÚLTIMO
                    letra = fr.read();
                }
                
            } catch (IOException ex) {//si la fincion read ha dado error
                res = -1;//devolvemos codigo interno de error
            }
        } catch (FileNotFoundException e) {//en caso de error al crear el reader
            fr = null;
            
        }finally{//finalmente liberamos los recursos
            if(fr != null){ //si fr se ha inicializado
                try {//intentamos cerrarlo
                    fr.close();
                } catch (IOException ex) {}
            }
        }
        
        return res;
    }
    
    /**
     * Funcion auxiliar que hace elrecuento de vocales de un fichero pasado por
     * parametro
     * Hace uso de la Clase BuffReader
     * @param file archivo del que queremos hacer el recuento de vocales
     * @return recuento de vocales
     */
    private int auxCountVocalsBuffReader(File file){
        int res = 0;
        FileReader fr = null;
        BufferedReader br = null;
        
        try {//intentamos crear los readers
            fr = new FileReader( file );
            br=new BufferedReader( fr );
            
            String line;
            
            try {//la funcion read puede dar error
                line = br.readLine();
                while( line != null )
                {
                       res = res + auxCountVocals(line);
                       line = br.readLine();
                }
                
            } catch (IOException ex) {//si la fincion read ha dado error
                res = -1;//devolvemos codigo interno de error
            }
        } catch (FileNotFoundException e) { //en caso de error al crear los reader
            // los ponemos a null
            fr = null;
            br = null;

        }finally{//finalmente liberamos los recursos
            if(fr != null){ //si fr se ha inicializado
                try {//intentamos cerrarlo
                    fr.close();
                } catch (IOException ex) {}
            }
            if(br != null){//si br se ha inicializado
                try {//intentamos cerrarlo
                    br.close();
                } catch (IOException ex) {}
            }
        }
        
        return res;
    }
    
    /**
     * Funcion auxiliar que hace el recuento de vocalees de un string pasado por parametro
     * @param str string del que queremos hacer el recuento de vocales
     * @return recuento de vocales
     */
    private int auxCountVocals(String str){
        int res =0;
        
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i)=='a' || str.charAt(i)=='e' || str.charAt(i)=='i' 
                || str.charAt(i)=='o' || str.charAt(i)=='u'
                || str.charAt(i)=='A' || str.charAt(i)=='E' || str.charAt(i)=='I' 
                || str.charAt(i)=='O' || str.charAt(i)=='U'){
                res++;
            }
        }
        
        return res;
    }

    /**
     * Funcion auxiliar recursiva que carga un ArrayList de ficheros con todos 
     * los ficheros contenidos en un directorio o el los subirectorios del 
     * directorio principal
     * @param parentfile directorio principal del que queremos obtener los 
     * ficheros
     */
    private void auxRecursiveSetSonFileList(File parentfile) {
        System.out.println(parentfile.getAbsolutePath());
        if(parentfile.isDirectory()){//si el parametro pasado es un directorio
            
            File[] sonFileList=parentfile.listFiles(); //obtenemos los subfiles
            if (sonFileList!=null) {
                for (int i = 0; i < sonFileList.length; i++) { //recorremos la lista de subfiles
                    //pasamos al siguiente nivel de la funcion recursiva
                    auxRecursiveSetSonFileList(sonFileList[i]);
                }
            }
            
        } else {
            fileListToCount.add(parentfile);
        }
    }
}
