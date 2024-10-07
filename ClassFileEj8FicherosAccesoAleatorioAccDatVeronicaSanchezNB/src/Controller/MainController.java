/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import IO.FileManager;
import Model.Alumn;
import Model.AlumnArray;

/**
 *
 * @author veron
 */
public class MainController {
    private static AlumnArray filteredList = new AlumnArray();//lista que nos ayuda a filtrar los alumnos
    private static AlumnArray alumnList = new AlumnArray();//lista que contiene los alumnos que vamos a gestionar
    public static FileManager fManag= new FileManager();//Clase que nos hace las peticiones al fichero

    /**
     * Constructor que inicializa la lista de alumnos
     */
    public MainController() {
        loadInfo();
    }
    
    /**
     * funcion para guardar la informacion en el fihero
     */
    public boolean saveInfo(){
        return fManag.writeFile(alumnList);
    }
    
    public boolean loadInfo(){
        boolean res= false;
        alumnList =fManag.readFile();
        if (fManag.readFile()!=null) {
            res= true;
        }
        return res;
    }
    
    /**
     * funcion que retorna la lista sobre la que haremos las seleciones
     * @return devuelve un ArrayList con la ultima seleccion de alumnos que hicimos
     */
    public AlumnArray getfilteredList(){
        return filteredList;
    }
    
    /**
     * funcion que retorna la lista de alumnos que estamos gestionando
     * @return  devuelve la lista de alumnos que estamos gestionando
     */
    public AlumnArray getAlumnList(){
        return alumnList;
    }

    /**
     * Setter para la lista de alumnos filtrados
     * @param filteredList un AlumnArray
     */
    public void setFilteredList(AlumnArray filteredList) {
        this.filteredList = filteredList;
    }

    /**
     * setter para la lista de alumnos que estamos gestionando
     * @param alumnList un AlumnArray
     */
    public void setAlumnList(AlumnArray alumnList) {
        this.alumnList = alumnList;
    }
    
    /**
     * funcion para borrar un alumno de la lista de alumnos que estamos gestionando
     * @param alu alumno que queremos modificar
     */
    public void removeAlumn(Alumn alu){
        alumnList.remove(alu);
    }
    /**
     * funcion para modificar un alumno de la lista de alumnos que estamos gestionando
     * @param oldAlu alumno que queremos cambiar
     * @param newAlu alumno por el que vamos a remplazar
     */
    public void updateAlumn(Alumn oldAlu, Alumn newAlu){
        alumnList.remove(oldAlu);
        alumnList.add(newAlu);
    }
    
    /**
     * funcion para añadir un alumno a la lista de alumnos que estamos gestionando
     * @param alu alumno que queremos añadir
     */
    public void addAlumn(Alumn alu){
        alumnList.add(alu);
    }
}
