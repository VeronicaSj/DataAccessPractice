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
    private static final long LONG_ROW=4+(50*2)+4+4+4+4+(10*2);// Bytes lenght for each row
    private static RandomAccessFile raf=null;
    
    /**
     * Constructor. Creates the working file if not existing.
     */
    public FileManager() {
        createFile(FILE);
    }
    
    /**
     * Funtion that tries to write a whole AlumnArray at once at the <code>FILE</code>
     * @param alumnList AlumnArray that will be written
     * @return true if the performance was succesfull; false in other case 
     */
    public boolean writeFile(AlumnArray alumnList){
        boolean res=false;//error value
        
        if(alumnList.size()>0){//if there are values
            //we try to write. If theres a mistake at writing, we stop
            for (int i = 0; i <alumnList.size() && writeAlumn(alumnList.get(i)) ; i++) {
                if(i== alumnList.size()-1){//if we are at the end of the array
                    res=true;//confirmation value
                }
            }
        }else{//if theres no value
            res=true;//confirmation value but nothing is done
        }
        
        return res;
    }
    
    /**
     * Funtion that tries to write an alumn at once at the <code>FILE</code>
     * @param alumn alumn that will be written
     * @return true if the performance was succesfull; false in other case
     */
    private boolean writeAlumn(Alumn alumn){
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
                    String dateStr = (date.getMonth()+1)+"/"+date.getDate()
                            +"/"+date.getYear();
                    
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
    
    /**
     * Funtion that tries to read the <code>FILE</code>
     * @return AlumnArray found in the file. false in error case
     */
    public AlumnArray readFile(){
        AlumnArray res=null;//error value
        try {
            raf = new RandomAccessFile(FILE, "r");
            Alumn auxAlumn;
            if (FILE.length()>0) {//if theres something
                try {
                    auxAlumn = auxReadAlumn();

                    //if we could read something we open the array
                    res= new AlumnArray();

                    //while theres still info we try to read
                    while(raf.getFilePointer() < FILE.length()){
                        auxAlumn=auxReadAlumn();
                        if (auxAlumn.getEnrollment()>0) {//if valid alumn
                            res.add(auxAlumn);
                        }
                    }
                } catch (IOException ex) {
                    res=null;
                }
            }else  {//if theres no thing to read
                res=new AlumnArray(); //return empti array
            }
        } catch (FileNotFoundException ex) {
            res=null;
        }finally{//Resource Release
            if (raf!= null) { try {raf.close(); } catch (IOException ex) {} }
        }
        
        return res;
    }
    
    /**
     * auxiliar Funtion that tries to read the next alumn from the 
     * <code>FILE</code>
     * @return the read alumn
     * @throws IOException
     */
    private Alumn auxReadAlumn() throws IOException {
        Alumn alumn= new Alumn();
        
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
            System.out.println(raf.getFilePointer());
        }
        alumn.setBirthD(new Date());
        
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

