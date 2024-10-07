/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IO;

import Model.Alumn;
import Model.AlumnArray;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author veron
 */
public class FileManager {
    private static final File FILE= new File("alumn.txt");
    private static final long LONG_REG=4+(50*2)+8+8+8+8+(10*2);// Bytes por registro >>> LINEA
    private static long numReg = 0;    // Número de registros dentro del fichero
    private static RandomAccessFile raf=null;
    
    /**
     * constructor que genera el fichero a traves de una ruta
     * @param path ruta del fichero que queremos gestionar
     */
    public FileManager() {
        createFile(FILE);
        FILE.setWritable(true);
    }
    
    
    public boolean writeFile(AlumnArray alumnList){
        boolean res=false;//false será nuestro valor de error
        
        
        
        return res;
    }
    
    private boolean writeAlumn(Alumn alumn){
        boolean res=false;
        
        
        
        return res;
    }
    
    
    public AlumnArray readFile(){
        AlumnArray res=null;//null será nuestro valor de error
        
        
        
        return res;
    }
    
    private Alumn readAlumn(){
        Alumn alumn=null;

        
        
        return alumn;
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

