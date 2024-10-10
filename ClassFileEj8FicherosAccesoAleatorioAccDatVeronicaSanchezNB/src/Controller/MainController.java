/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import IO.FileManager;
import Model.Alumn;
import Model.AlumnArray;

/**
 * Class that controls the info exchange
 * @author veron
 */
public class MainController {
    //We will take all the alumns from the file at the beginning
    private static AlumnArray alumnList = new AlumnArray();
    private static AlumnArray filteredList = new AlumnArray();//will save the filtered alumn list
    public static FileManager fManag= new FileManager();//responsable of every file change

    /**
     * Constructor. It tries to load the <code>alumnList</code>
     */
    public MainController() {
        loadInfo();
    }
    
    /**
     * Funtion that saves the full <code>alumnList</code>
     * @return true if the performance was succesfull; false in other case 
     */
    public boolean saveInfo(){
        return fManag.writeFile(alumnList);
    }
    
    /**
     * Funtion that tries to load the <code>alumnList</code>
     * @return true if the performance was succesfull; false in other case 
     */
    public boolean loadInfo(){
        boolean res= false;
        alumnList =fManag.readFile();
        if (fManag.readFile()!=null) {
            res= true;
        }
        return res;
    }
    
    /**
     * <code>filteredList</code> getter
     * @return <code>filteredList</code> value from this class 
     */
    public AlumnArray getfilteredList(){
        return filteredList;
    }
    
    /**
     * <code>alumnList</code> getter
     * @return <code>alumnList</code> value from this class 
     */
    public AlumnArray getAlumnList(){
        return alumnList;
    }

    /**
     * <code>filteredList</code> setter
     * @param filteredList value
     */
    public void setFilteredList(AlumnArray filteredList) {
        this.filteredList = filteredList;
    }

    /**
     * <code>alumnList</code> setter
     * @param alumnList value
     */
    public void setAlumnList(AlumnArray alumnList) {
        this.alumnList = alumnList;
    }
    
    /**
     * Funtion that remove an alumn from the <code>alumnList</code>
     * @param alu we want to remove
     */
    public void removeAlumn(Alumn alu){
        alumnList.remove(alu);
    }
    /**
     * Funtion that updates an alumn from the <code>alumnList</code>
     * @param oldAlu alum that will be deleted
     * @param newAlu alum that will replace the old alumn
     */
    public void updateAlumn(Alumn oldAlu, Alumn newAlu){
        alumnList.remove(oldAlu);
        alumnList.add(newAlu);
    }
    
    /**
     * Funtion that adds an alumn to the <code>alumnList</code>
     * @param alu alum that will be added
     */
    public void addAlumn(Alumn alu){
        alumnList.add(alu);
    }
}
