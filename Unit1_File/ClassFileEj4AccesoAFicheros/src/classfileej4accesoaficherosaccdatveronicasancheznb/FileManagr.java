/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classfileej4accesoaficherosaccdatveronicasancheznb;

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
    private static File userDir = null;
    private static ArrayList<File> fileList = new ArrayList<File>();
    

    public FileManagr(File userDir) {
        this.userDir=userDir;
        auxRecursiveSetSonFileList(userDir);
    }
    
    
    /**
     * Funcion que devuelve todas las localizaciones de la userWord en los 
     * ficheros dentro del directorio userDir (Atributo de esta clase)
     * @param userWord palabra que queremos buscar
     * @return string con las diferentes localizaciones de la userWord
     */
    public String getWordLocation(String userWord) {
        String res=null;
        for (int i = 0; i < fileList.size(); i++) {
            String wordLocationFromFile= auxGetWordLocationReaderManagement(
                    fileList.get(i), userWord);
            if (wordLocationFromFile != null) {
                if (res==null) {
                    res="";
                }
                res = res + "\n"+wordLocationFromFile;
            }
        }
        return res;
    }
    
    /**
     * Funcion auxiliar que hace la busqueda de userWord en el archivo indicado 
     * en el parametro file. 
     * Esta funcion contiene el codigo para controlar las excepciones 
     * relacionadas al reader y llama a la funcion 
     * auxReadWordLocation(BufferedReader br, File file, String userWord) en 
     * caso de no haber excepciones
     * @param file Archivo en el que queremos leer la palabra del usuario
     * @param userWord palabra que queremos buscar
     * @return String con el nombre del archivo en el que estamos buscando la 
     * userWord y las diferentes localizaciones de la palabra
     */
    private String auxGetWordLocationReaderManagement(File file, String userWord) {
        String res=null;
        
        FileReader fr = null;
        BufferedReader br = null;
        
        try {//intentamos crear los readers
            fr = new FileReader( file );
            br=new BufferedReader( fr );
            
            try {//la funcion read puede dar error
                res = auxReadWordLocation(br, file, userWord);
            } catch (IOException ex) {//si la funcion read ha dado error
                res = null;//devolvemos codigo interno de error
                System.out.println("ERROR");
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
     * Funcion auxiliar que hace la busqueda de userWord en el archivo indicado 
     * en el parametro file. Utiliza un bufferedReader (br) previamente inicializado 
     * y libre de excepciones
     * @param br lector con el que vamos a recorrer el fichero
     * @param file fichero que queremos recorrer
     * @param userWord palabra que vamos a buscar
     * @return String con el nombre del archivo en el que estamos buscando la 
     * userWord y las diferentes localizaciones de la palabra
     * @throws IOException posible error de la ejecucion de la funcion 
     * br.readLine() utilizada dentro de esta funcion
     */
    private String auxReadWordLocation(BufferedReader br, File file, 
            String userWord) throws IOException {
        String res=null;
        
        String line = br.readLine();
        int lineCount = 1;
        while( line != null )
        {
            int columnLocation = line.indexOf(userWord, 
                            0);

            if (columnLocation >= 0) {//si hemos encontrado la palabra
                if(res==null){//la primera vez que encuentra la palabra
                    //al resultado le añadimos la ruta del archivo
                    res="ARCHIVO: " + file.getAbsolutePath();
                }

                //encontrar todas las instancias de la palabra en la linea
                do {
                    res=res+"\n\tLinea: " + lineCount + 
                            ", Columna: "+ columnLocation;
                    columnLocation = line.indexOf(userWord, 
                            columnLocation+1);
                } while (columnLocation>= 0); 
            }

               line = br.readLine();
               lineCount++;

        }
        return res;
    }
    
    
    
    //ESTAS FUNCIONES SON REUTILIZADAS DEL EJERCICIO 3
    
    /**
     * Getter del tamaño del Array fileListToCount
     * @return tamaño del Array fileListToCount
     */
    public int getFileListToCountLength(){
        return fileList.size();
    }
            
    /**
     * Funcion que devuelve el recuento de vocales de los ficheros dentro del 
     * directorio userDir (Atributo de esta clase). Hace uso de la Clase 
     * FileReader
     * @return recuento de vocales del directorio userDir 
     */
    public int getCountVocalsFileReader(){
        int res = 0;
        for (int i = 0; i < fileList.size(); i++) {
            res = res + auxCountVocalsFileReader(fileList.get(i));
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
        for (int i = 0; i < fileList.size(); i++) {
            res = res + auxCountVocalsBuffReader(fileList.get(i));
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
        if(parentfile.isDirectory()){//si el parametro pasado es un directorio
            
            File[] sonFileList=parentfile.listFiles(); //obtenemos los subfiles
            if (sonFileList!=null) {
                for (int i = 0; i < sonFileList.length; i++) { //recorremos la lista de subfiles
                    //pasamos al siguiente nivel de la funcion recursiva
                    auxRecursiveSetSonFileList(sonFileList[i]);
                }
            }
            
        } else {
            fileList.add(parentfile);
        }
    }
}
