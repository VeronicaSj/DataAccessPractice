/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Clase que personaliza un ArrayList de alumnos para que se ordene por si mismo
 * teniendo en cuenta los diferentes atributos por los que se puede ordenar.
 * @author veron
 */
public class AlumnArray extends ArrayList<Alumn>{
    Sorter sorter;//clase que nos hace la ordenación
    
    /**
     * Constructor de AlumnArray
     * @param c colleccion a partir de la que queremos formar el nuevo AlumnArray
     */
    public AlumnArray(Collection<? extends Alumn> c) {
        super(c);
    }

    public AlumnArray() {
        
    }

    /**
     * funcion que ordena la propia lista. 
     * @param index atributo por el que queremos hacer la ordenacion.
     * valor 0 para ordenar por matricula
     * valor 1 para ordenar por nombre
     * valor 2 para ordenar por nota 3
     * valor 3 para ordenar por nota 1
     * valor 4 para ordenar por nota final
     * valor 5 para ordenar por nota extra
     * @param orderMode opcion para ordenar en sentido contrario al que da por defecto Sorter
     * @return lista ordenada
     */
    public AlumnArray sort(int index, boolean orderMode){
        sorter = new Sorter(this, index, orderMode);
        return (AlumnArray) sorter.sort();
    }
}
