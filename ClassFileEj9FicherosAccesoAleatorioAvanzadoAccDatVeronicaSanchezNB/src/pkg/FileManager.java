package pkg;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import pkg.Alumn;
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

    enum ColumnNames{
        ENROLLMENT, NAME, MARK_1, MARK_2, MARK_FINAL, MARK_EXTRA, BIRTH_D;
    }
    
    private static final File FILE= new File("alumn.txt");
    private static final long LONG_ROW=4+(50*2)+4+4+4+4+(10*2);// Bytes lenght for each row
    private static RandomAccessFile raf=null;
    HashMap<ColumnNames, Integer> columnPos = new HashMap<ColumnNames, Integer>();
    
    /**
     * Constructor. Creates the working file if not existing and asing a 
     * position for each Column
     */
    public FileManager() {
        createFile(FILE);
        
        columnPos.put(ColumnNames.ENROLLMENT, 0);
        columnPos.put(ColumnNames.NAME, 4);
        columnPos.put(ColumnNames.MARK_1, 4+(50*2));
        columnPos.put(ColumnNames.MARK_2, 4+(50*2)+4);
        columnPos.put(ColumnNames.MARK_FINAL, 4+(50*2)+4+4);
        columnPos.put(ColumnNames.MARK_EXTRA, 4+(50*2)+4+4+4);
        columnPos.put(ColumnNames.BIRTH_D, 4+(50*2)+4+4+4+4);
    }
    
    
    /**
     * devuelve el campo correspondiente a la columna de nombre nomColumna del 
     * registro indicado en numRegistro.
     * @param numRegistro
     * @param nomColumna
     * @return 
     */
    public String selectCampo(int numRegistro, ColumnNames nomColumna){
        String res="";
        try {
            raf = new RandomAccessFile(FILE, "r");
            try {
                raf.seek(auxCalculeFilePointer(numRegistro, nomColumna));
                res = auxReadColumn(nomColumna);
                
            } catch (IOException ex) {
                res=null;
            }
        } catch (FileNotFoundException ex) {
            res=null;
        }finally{//Resource Release
            auxCloseRaf();
        }
        
        return res;
    }

    /**
     * Devuelve una lista con TODOS los valores del campo buscado. 
     * (SELECT columna FROM fichero).
     * @param nomColumna
     * @return 
     */
    public List selectColumna(ColumnNames nomColumna ){
        List res = new ArrayList<String>();
        try {
            raf = new RandomAccessFile(FILE, "r");
            try {
                for (int i = 0; raf.getFilePointer()<(FILE.length()-LONG_ROW);
                        i++) {
                    raf.seek(auxCalculeFilePointer(i, nomColumna));
                    res.add(auxReadColumn(nomColumna));
                }
            } catch (IOException ex) {
                res=null;
            }
        } catch (FileNotFoundException ex) {
            res=null;
        }finally{//Resource Release
            auxCloseRaf();
        }
        
        return res;
    }
    
    /**
     * Devuelve una lista con los datos del registro de la posición numRegistro.
     * (SELECT FROM fichero WHERE ... )
     * @param numRegistro
     * @return 
     */
    public List selectRowList(int numRegistro ){
        List res = new ArrayList<String>();
        Alumn alumn = readAlumn(numRegistro);
        
        //insert into the list
        res.add( alumn.getEnrollment()+"");
        res.add(alumn.getName());
        res.add(alumn.getMark1Ev()+"");
        res.add(alumn.getMark2Ev()+"");
        res.add( alumn.getMarkFinal()+"");
        res.add(alumn.getMarkExtra()+"");
        res.add(DateHelper.auxParseDateToString(alumn.getBirthD()));
        
        return res;
    }

    /**
     * Igual resultado que el anterior pero en una clase HashMap.
     * @param numRegistro
     * @return 
     */
    public Map selectRowMap( int numRegistro ){
        Map res = new HashMap<ColumnNames, String>();
        Alumn alumn = readAlumn(numRegistro);
        
        //insert into the map
        res.put(ColumnNames.ENROLLMENT, alumn.getEnrollment()+"");
        res.put(ColumnNames.NAME, alumn.getName());
        res.put(ColumnNames.MARK_1, alumn.getMark1Ev()+"");
        res.put(ColumnNames.MARK_2, alumn.getMark2Ev()+"");
        res.put(ColumnNames.MARK_FINAL, alumn.getMarkFinal()+"");
        res.put(ColumnNames.MARK_EXTRA, alumn.getMarkExtra()+"");
        res.put(ColumnNames.BIRTH_D, DateHelper.auxParseDateToString(alumn.getBirthD()));
        
        return res;
    }

    
    /**
     * Modifica en el fichero todos los campos para el registro indicado en row 
     * que se reciben en un Map
     * @param row
     * @param map
     * @return 
     */
    public boolean update( int row, HashMap<ColumnNames, String> map) 
            throws IllegalArgumentException {
        boolean res=false;
        
        try {
            raf = new RandomAccessFile(FILE, "rw");
            try {
                raf.seek(row*LONG_ROW );
                
                //write
                auxWriteColumn(ColumnNames.ENROLLMENT, 
                        map.get(ColumnNames.ENROLLMENT));
                auxWriteColumn(ColumnNames.NAME, map.get(ColumnNames.NAME));
                auxWriteColumn(ColumnNames.MARK_1, map.get(ColumnNames.MARK_1));
                auxWriteColumn(ColumnNames.MARK_2, map.get(ColumnNames.MARK_2));
                auxWriteColumn(ColumnNames.MARK_FINAL, 
                        map.get(ColumnNames.MARK_FINAL));
                auxWriteColumn(ColumnNames.MARK_EXTRA, 
                        map.get(ColumnNames.MARK_EXTRA));
                auxWriteColumn(ColumnNames.BIRTH_D, 
                        map.get(ColumnNames.BIRTH_D));
                
                res=true;//confirmation value
            } catch (IOException ex) {
                res=false;
            }
        } catch (FileNotFoundException ex) {
            res=false;
        }finally{//Resource Release
            auxCloseRaf();
        }
        return res;
    } 

    /**
     * Modifica en el fichero SOLO el valor del campo "campo" para el registro 
     * indicado en row.
     * @param row
     * @param campo
     * @param valor
     * @return 
     */
    public  boolean update( int row, ColumnNames  campo, String valor )
            throws IllegalArgumentException {
        boolean res=false;
        try {
            raf = new RandomAccessFile(FILE, "rw");
            try {
                raf.seek(auxCalculeFilePointer(row, campo));
                auxWriteColumn(campo, valor);
                
                res=true;//confirmation value
            } catch (IOException ex) {
                res=false;
            }
        } catch (FileNotFoundException ex) {
            res=false;
        }finally{//Resource Release
            auxCloseRaf();
        }
        return res;
    } 
    
    /**
     * limpia los datos del registro indicado.
     * @param row
     * @return 
     */
    public  boolean delete( int row ){
        boolean res=false;
        Alumn alumn=new Alumn();
        return auxWriteAlumn(row, alumn);
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
            auxWriteAlumn(id, alumn);
        } else {//if alumn is invalid
            res=true; //confirmation value but nothing is done
        }
        
        return res;
    }
    
    /**
     * Funtion that tries to read one alumn from the 
     * <code>FILE</code>
     * @param id alumn id from the alumn we want to read
     * @return the read alumn, null if there is a problem
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
            auxCloseRaf();
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
    
        
    /**
     * Auxiliar function that release Resources
     */
    private void auxCloseRaf(){
        if (raf!= null) { 
            try {
                raf.close();
            } catch (IOException ex) {} 
        }
    }
    
    /**
     * Auxiliar function that calcule file pointer position
     * @param row
     * @param column
     * @return file pointer position
     */
    private long auxCalculeFilePointer(int row, ColumnNames column){
        return (row*LONG_ROW) + columnPos.get(column);
    }
    
    /**
     * Auxiliar function that writes the alumn name into the file. 
     * Should be used only when the RandomAccessFile is open and the pointer 
     * is at the correct position
     * @param name
     * @throws IOException 
     */
    private void auxWriteName(String name) throws IOException {
        for (int i = 0; i < 50-name.length(); i++) {
            raf.writeChar(0); 
        }
        for (int i = 0; i < name.length(); i++) {
            raf.writeChar(name.charAt(i)); 
        }
    }
    
    /**
     * Auxiliar function that writes the alumn birth date into the file. 
     * Should be used only when the RandomAccessFile is open and the pointer 
     * is at the correct position
     * @param date
     * @throws IOException 
     */
    private void auxWriteDate(Date date) throws IOException {
        String dateStr = DateHelper.auxParseDateToString(date);

        for (int i = 0; i < dateStr.length(); i++) {
            raf.writeChar(dateStr.charAt(i)); 
        }
        for (int i = 0; i < 10-dateStr.length(); i++) {
            raf.writeChar(0); 
        }
    }
    
    
    
    
    /**
     * Auxiliar function that writes an alumn into a position in the file
     * @param position
     * @param alumn
     * @return true if writen
     */
    private boolean auxWriteAlumn(int position, Alumn alumn){
        boolean res=false;//error value
        int id = alumn.getEnrollment();
        
        try {
            raf = new RandomAccessFile(FILE, "rw");
            try {
                //set the filepointer
                raf.seek(position*LONG_ROW);

                //enrolment id
                raf.writeInt(id);
                
                //write name
                auxWriteName(alumn.getName());
                
                //write califications
                raf.writeFloat(alumn.getMark1Ev());
                raf.writeFloat(alumn.getMark2Ev());
                raf.writeFloat(alumn.getMarkFinal());
                raf.writeFloat(alumn.getMarkExtra());
                //write date
                auxWriteDate(alumn.getBirthD());
                
                res=true;//confirmation value
            } catch (IOException ex) {
                res=false;
            }
        } catch (FileNotFoundException ex) {
            res=false;
        }finally{//Resource Release
            auxCloseRaf();
        }
        
        return res;
    }
    

    /**
     * Auxiliar function that read the value from a column. 
     * Should be used only when the RandomAccessFile is open and the pointer 
     * is at the correct position
     * @param nomColumna
     * @return
     * @throws IOException 
     */
    private String auxReadColumn(ColumnNames nomColumna) throws IOException {
        String res="";
        switch (nomColumna){
            case ENROLLMENT:
                res=""+raf.readInt();
                break;
            case NAME:
                for (int i = 0; i < 50; i++) {
                    res = res + raf.readChar();
                }
                break;
            case BIRTH_D:
                for (int i = 0; i < 10; i++) {
                    res = res + raf.readChar();
                }
                break;
            default:
                res=""+raf.readFloat();
        }
        return res;
    }

    /**
     * Auxiliar function that writes a value into the "column" of a "row"
     * Should be used only when the RandomAccessFile is open and the pointer 
     * is at the correct position
     * @param campo
     * @param valor
     * @throws IOException
     * @throws IllegalArgumentException 
     */
    private void auxWriteColumn(ColumnNames campo, String valor) 
            throws IOException, IllegalArgumentException {
        switch(campo){
            case ENROLLMENT:
                raf.writeInt(Integer.parseInt(valor));
                break;
            case NAME:
                auxWriteName(valor);
                break;
            case BIRTH_D:
                Date date = new Date(Date.parse(valor));
                auxWriteDate(date);
                break;
            default:
                raf.writeFloat(Float.parseFloat(valor));
        }
    }
    
}

