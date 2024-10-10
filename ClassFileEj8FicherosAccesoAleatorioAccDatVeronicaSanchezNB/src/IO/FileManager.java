/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IO;

import Model.Alumn;
import Model.AlumnArray;
import java.io.EOFException;
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
import java.util.Date;
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
    private static final long LONG_REG=4+(50*2)+4+4+4+4+(10*2);// Bytes por registro >>> LINEA
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
        
        if(alumnList.size()>0){
            for (int i = 0; i <alumnList.size() && writeAlumn(alumnList.get(i)) ; i++) {
                if(i== alumnList.size()-1){
                    res=true;
                }
            }
        }else{
            res=true;
        }
        
        return res;
    }
    
    private boolean writeAlumn(Alumn alumn){
        boolean res=false;
        
        if (alumn.getNMatricula()>0) {
            try {
                raf = new RandomAccessFile(FILE, "rw");
                try {
                    raf.seek(alumn.getNMatricula()*LONG_REG);
                    raf.writeInt(alumn.getNMatricula());
                    String name = alumn.getNombre();
                    for (int i = 0; i < 50-name.length(); i++) {
                        raf.writeChar(0); 
                    }
                    for (int i = 0; i < name.length(); i++) {
                        raf.writeChar(name.charAt(i)); 
                    }
                    raf.writeFloat(alumn.getNot1Ev());
                    raf.writeFloat(alumn.getNot2Ev());
                    raf.writeFloat(alumn.getNotaFinal());
                    raf.writeFloat(alumn.getNotaExtra());
                    
                    
                    for (int i = 0; i < 10; i++) {
                        raf.writeChar(i); 
                    }
                    
                    res=true;
                } catch (IOException ex) {
                    System.out.println("writeAlumn(Alumn alumn) IOException ex");
                    res=false;
                }
            } catch (FileNotFoundException ex) {
                System.out.println("writeAlumn(Alumn alumn) FileNotFoundException ex");
                res=false;
            }finally{//liberamos recursos
                if (raf!= null) { try {raf.close(); } catch (IOException ex) {} }
            }
        } else {
            res=true;
        }
        
        return res;
    }
    
    
    public AlumnArray readFile(){
        AlumnArray res=null;//null será nuestro valor de error
        try {
            raf = new RandomAccessFile(FILE, "r");
            
            Alumn auxAlumn;
            if (FILE.length()>0) {
                try {
                    auxAlumn = readAlumn();

                    //si hemos podido leer un alumno abrimos el array
                    res= new AlumnArray();

                    while(raf.getFilePointer() < FILE.length()){
                        auxAlumn=readAlumn();
                        if (auxAlumn.getNMatricula()>0) {
                            res.add(auxAlumn);
                        }
                    }

                } catch (IOException ex) {
                    System.out.println("readFile() IOException ex");
                    res=null;
                }
            }else  {
                res=new AlumnArray();
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println("readFile() FileNotFoundException ex");
            res=null;
        }finally{//liberamos recursos
            if (raf!= null) { try {raf.close(); } catch (IOException ex) {} }
        }
        
        return res;
    }
    
    private Alumn readAlumn() throws IOException {
        Alumn alumn= new Alumn();
        
        alumn.setNMatricula(raf.readInt());

        System.out.println(raf.getFilePointer());
        
        String name = "";
        for (int i = 0; i < 50; i++) {
            name = name + raf.readChar();
            System.out.println(raf.getFilePointer());
        }

        System.out.println(raf.getFilePointer());
        
        alumn.setNombre(name);
        
        
        alumn.setNot1Ev(raf.readFloat());
        System.out.println(raf.getFilePointer());
        alumn.setNota2Ev(raf.readFloat());
        alumn.setNotaFinal(raf.readFloat());
        alumn.setNotaExtra(raf.readFloat());

        System.out.println(raf.getFilePointer());
        String date = "";
        for (int i = 0; i < 10; i++) {
            date = date + raf.readChar();
            System.out.println(raf.getFilePointer());
        }
        System.out.println(raf.getFilePointer());
        

        alumn.setbirthD(new Date());
        
        System.out.println(alumn.toString());
        System.out.println(raf.getFilePointer());
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

