/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classfileej5escriturasecuencialficherosaccdatveronicasancheznb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author veron
 */
public class FileManager {

    public FileManager() {
    }
    
    /**
     * Funcion que crea un archivo
     * @param file archivo (no existente) que queremos crar
     * @return true si todo ha ido bien. false en caso contrario
     */
    public boolean createFile(File file){
        boolean res=false;
        try {
            res = file.createNewFile();
        } catch (IOException ex) {
            res=false;
        }
        return res;
    }
    
    /**
     * Funcion que añade un texto al final de un archivo
     * @param file archivo al que queremos añadir el texto
     * @param str texto que queremos añadir
     * @return true si todo ha ido bien. False en caso contrario
     */
    public boolean apendText(File file, String str){
        boolean res=false;
        String oldText = readFile(file);
        if (oldText!=null) {
          res=writeFile(file, oldText+str);  
        }
        return res;
    }
    
    /**
     * Funcion que añade un texto al principio de un archivo
     * @param file archivo al que queremos añadir el texto
     * @param str texto que queremos añadir
     * @return true si todo ha ido bien. False en caso contrario
     */
    public boolean addBeginningText(File file, String str){
        boolean res=false;
        String oldText = readFile(file);
        if (oldText!=null) {
          res=writeFile(file, str+oldText);  
        }
        return res;
    }
    
    
    /**
     * Funcion auxiliar que escribe un texto en un archivo
     * @param file archivo que será escrito
     * @param finalStr texto para escribir
     * @return true en caso de que todo salga bien. False en caso de error
     */
    private boolean writeFile(File file, String finalStr){
        boolean res=false;//false será nuestro valor de error
        
        FileWriter fw = null;
        
        try { //el constructor FileWriter(file) y la funcion apend pueden dar excepcion
            fw = new FileWriter(file);
            fw.append(finalStr);
            res=true;
        } catch (IOException ex) {
            res=false;
        }finally{
            //liberamos los recursos
            if (fw!=null) {
                try {
                    fw.close();
                } catch (IOException ex) {}
            }
        }
        
        return res;
    }
    
    /**
     * Funcion que lee y devuelve el contenido de un archivo
     * @param file archivo que queremos lerr
     * @return texto contenido en el archivo o null en caso de error
     */
    private String readFile(File file){
        String res=null;//null será nuestro valor de error
        
        FileReader fr = null;
        try {//el constructor FileReader(file)puede dar excepcion
            fr = new FileReader(file);
            char[] charlist = new char[(int)file.length()];
            try {//la funcion read puede dar excepcion
                fr.read(charlist);
                res = new String(charlist);//si todo ha ido bien devolvemos el texto
            } catch (IOException ex) {
                res=null;
            }
        } catch (FileNotFoundException ex) {
            res=null;
        }finally{
            //liberamos los recursos
            if (fr!=null) {
                try {
                    fr.close();
                } catch (IOException ex) {}
            }
        }
            
        return res;
    }
}
