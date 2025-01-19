/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


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
    private static final long LONG_ROW=4+(50*2)+4+4+4+4+(10*2);// Bytes lenght for each row
    private static RandomAccessFile raf=null;
    
    /**
     * Constructor. Creates the working file if not existing.
     */
    public FileManager() {
        createFile(FILE);
    }
    
    /**
     * Funtion that tries to write an alumn at once at the <code>FILE</code>
     * @param alumn alumn that will be written
     * @return true if the performance was succesfull; false in other case
     */
    public boolean writeAlumn(Alumn alumn){
        boolean res=false;//error value
        int id = alumn.getEnrollment();
        
        if (id>0) {//if alumn is valid
            try {
                raf = new RandomAccessFile(FILE, "rw");
                try {
                    
                    //set the filepointer
                    raf.seek(id*LONG_ROW);
                    
                    //enrolment id
                    raf.writeInt(id);
                    
                    //write name
                    String name = alumn.getName();
                    for (int i = 0; i < 50-name.length(); i++) {
                        raf.writeChar(0); 
                    }
                    for (int i = 0; i < name.length(); i++) {
                        raf.writeChar(name.charAt(i)); 
                    }
                    
                    //write califications
                    raf.writeFloat(alumn.getMark1Ev());
                    raf.writeFloat(alumn.getMark2Ev());
                    raf.writeFloat(alumn.getMarkFinal());
                    raf.writeFloat(alumn.getMarkExtra());
                    
                    //write date
                    Date date = alumn.getBirthD();
                    String dateStr = force2char(""+(date.getMonth()+1))+"/"
                            + force2char(""+date.getDate()) + "/" 
                            + (date.getYear()+1900);
                    
                    for (int i = 0; i < 10; i++) {
                        raf.writeChar(dateStr.charAt(i)); 
                    }
                    
                    res=true;//confirmation value
                } catch (IOException ex) {
                    res=false;
                }
            } catch (FileNotFoundException ex) {
                res=false;
            }finally{//Resource Release
                if (raf!= null) { try {raf.close(); } catch (IOException ex) {} }
            }
        } else {//if alumn is invalid
            res=true; //confirmation value but nothing is done
        }
        
        return res;
    }
    
    private String force2char(String str){
        String res="";
        if(str.length()==1){
            res = 0+str;
        }
        return res;
    }
    
    /**
     * auxiliar Funtion that tries to read one alumn from the 
     * <code>FILE</code>
     * @param id alumn id from the alumn we want to read
     * @return the read alumn
     * @throws IOException
     */
    public Alumn readAlumn(int id){
        Alumn alumn= new Alumn();
        
        try {
            raf = new RandomAccessFile(FILE, "r");
            try {
                
                raf.seek(id*LONG_ROW);
                
                //read enrolment id
                alumn.setEnrollment(raf.readInt());
                
                //read name
                String name = "";
                for (int i = 0; i < 50; i++) {
                    name = name + raf.readChar();
                }
                alumn.setName(name);

                //read califications
                alumn.setMark1Ev(raf.readFloat());
                alumn.setMark2Ev(raf.readFloat());
                alumn.setMarkFinal(raf.readFloat());
                alumn.setMarkExtra(raf.readFloat());

                //read date
                String date = "";
                for (int i = 0; i < 10; i++) {
                    date = date + raf.readChar();
                }
                alumn.setBirthD(new Date(date));
            } catch (IOException ex) {
                alumn=null;
            }

        } catch (FileNotFoundException ex) {
            alumn=null;
        }finally{//Resource Release
            if (raf!= null) { try {raf.close(); } catch (IOException ex) {} }
        }
        
        return alumn;
    }
    
    /**
     * Funtion that creates a file
     * @param file file to be created. Is asumed that the file doesn't exist
     * @return true if the performance was succesfull; false in other case
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

