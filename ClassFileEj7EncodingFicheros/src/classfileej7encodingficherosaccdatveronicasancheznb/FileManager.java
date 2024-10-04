/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classfileej7encodingficherosaccdatveronicasancheznb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author veron
 */
public class FileManager {
    
    
    /**
     * Funcion auxiliar que escribe un texto en un archivo
     * @param file archivo que será escrito
     * @param finalStr texto para escribir
     * @param charset encoding de escritura del texto
     * @return true en caso de que todo salga bien. False en caso de error
     */
    public boolean writeFile(File file, String finalStr, String charset){
        boolean res=false;//false será nuestro valor de error
        
        FileOutputStream fos=null;
        OutputStreamWriter osw = null;
        
        try {
            fos = new FileOutputStream(file);
            osw=new OutputStreamWriter(fos, Charset.forName(charset));
            try {
                osw.append(finalStr);
                res=true;
            } catch (IOException ex) {
                res=false;
            }
        } catch (FileNotFoundException ex) {
            res=false;
        }
        
        
        return res;
    }
    
    /**
     * Funcion que lee y devuelve el contenido de un archivo
     * @param file archivo que queremos leer
     * @param charset encoding de lectura del texto
     * @return texto contenido en el archivo o null en caso de error
     */
    public String readFile(File file, String charset){
        String res=null;//null será nuestro valor de error
        
        FileInputStream fis=null;
        InputStreamReader isr=null;
        
        try {
            fis = new FileInputStream(file);
            isr=new InputStreamReader(fis, Charset.forName(charset));
            
            try {//la funcion read puede dar excepcion
                int intChar = isr.read();
                res="";//si la primra lectura no da excepcion, inicializamos
                while (intChar!=-1) {
                    res=res+ (char) intChar;
                }
                //si todo ha ido bien devolvemos el texto
            } catch (IOException ex) {
                res=null;
            }
        } catch (FileNotFoundException ex) {
            res=null;
        }
            
        return res;
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
    
}
