/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classfileej6guardadoadiscoaccdatveronicasancheznb;

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
    
    /**
     * Funcion que recoge el texto de un archivo pasado por parametro y lo 
     * reescribe editado para:
     * Forzar Mayúscula después de .(punto) y eliminar consecutivos espacios en 
     * blanco.
     * @param file archivo que queremos normalizar
     * @return true si la operacion se ha realizado correctamente, false en 
     * caso de error
     */
    public boolean normaliceFile(File file){
        boolean res = false;
        String orgTexto= readFile(file);//leemos el archivo pasado por parametro
        
        if (orgTexto!=null) { //checkeamos la lectura correcta
            try {
                File workFile=File.createTempFile("ex6New", null);//creamos el archivo

                //normalizamos el texto
                //intentamos pasar los cambios al archivo de trabajo
                if (writeFile(workFile, auxNormaliceText(orgTexto)) ){
                    /*si sale bien intentamos borrar fichero original
                    si sale bien sustituimos el anterior por el nuevo*/
                    if(deleteFile(file) && workFile.renameTo(file)) {
                        //si todo lo anterior ha salido bien
                        res=true;//devolvemos true
                    }else{ //un error  funciones puede probocar perdida de info
                        //vamos a intentar recuperar un backup
                        file.createNewFile();//si hemos borrado el archivo, lo creamos
                        writeFile(file, orgTexto);//le escribimos el texto original
                        //si esto da eror, no queda otra que perder info
                        res=false;
                    }
                }
            } catch (IOException ex) {
                res=false;
            }
        }
        
        return res;
    }
    /**
     * Funcion auxiliar que devuelve un texto normalizado. es decir, fuerza 
     * Mayúscula después de .(punto) y eliminar espacios consecutivos en 
     * blanco.
     * @return 
     */
    private String auxNormaliceText(String orgText){
        String res="";
        
        //esta variable activa la busqueda de la letra que debe ir en mayuscula
        boolean tryMayus=false;
        String auxMayus="";
        String auxMinus="";
        char lastChar;
        //Por optimizacion voy a recorrer el texto 1 vez
        for (int i = 0; i < orgText.length(); i++) {
            //recogemos la letra actual
            lastChar= orgText.charAt(i);
            
            if (lastChar=='.') { //si encontramos un punto
                tryMayus=true;//activamos la busqueda de la mayuscula
            }
            
            if(tryMayus){//si la busqueda de la mayuscula esta activa
                //cogemos la letra en version mayuscula y minuscula
                auxMayus=(""+lastChar).toUpperCase();
                auxMinus=(""+lastChar).toLowerCase();
                //si la version mayuscula y la version minuscula son diferentes
                //eso significa que es un caracter alfabetico ("Mayusculable")
                if (!auxMayus.equals(auxMinus)){//si encontramos el caracter
                    lastChar=auxMayus.charAt(0);//la ponemos en mayuscula
                    tryMayus=!tryMayus;//dejamos de buscarla
                }
            }
            
            //comprobamos el espacio repetido
            if(lastChar==' '){//si hay espacio
                while(orgText.charAt(i+1)==' '){//mientras encontremos espacios
                    i++;//nos saltamos el siguiente caracter(el espacio repetido)
                }
            }
            
            res=res+lastChar;
        }
        
        return res;
    }
    
    /**
     * Funcion auxiliar que borra un archivo
     * @param file archivo para borrar
     * @return true en caso de borrado, false en caso de error
     */
    private boolean deleteFile(File file){
        boolean res = false;//false es nuestro codigo de error
        if (writeFile(file, "")) {//vaciamos fichero
            if (file.delete()) {//si se vacia bien, borramos fichero
                res=true;//si se borra bien, mandamos menaje de ok
            }
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
