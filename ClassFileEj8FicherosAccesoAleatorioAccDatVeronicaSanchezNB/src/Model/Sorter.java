/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Model.AlumnArray;
import Model.Alumn;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Clase que realiza ordenaciones de listas de alumnos
 * @author veron
 */
public class Sorter {
    ArrayList<Alumn> list;
    int index;
    boolean otherOrderOn;

    /**
     * Constructor para el Sorter.
     * @param list lista para ordenar
     * @param index atributo por el que queremos hacer la ordenacion.
     * valor 0 para ordenar por matricula
     * valor 1 para ordenar por nombre
     * valor 2 para ordenar por nota 3
     * valor 3 para ordenar por nota 1
     * valor 4 para ordenar por nota final
     * valor 5 para ordenar por nota extra
     * @param otherOrderOn opcion para ordenar en sentido contrario al que da por defecto Sorter
     
     */
    public Sorter(ArrayList<Alumn> list, int index, boolean otherOrderOn) {
        this.list = list;
        this.index = index;
        this.otherOrderOn = otherOrderOn;
    }
    
    /**
     * funcion publica que da paso a la ordenación. En esta funcion se hace 
     * una diferenciación del orden que queremos
     * @return lista ordenada
     */
    public ArrayList<Alumn> sort(){
        if(otherOrderOn){ //opcion de orenacion 1 
            sortOtherOtherOn();
        }else{ //opcion de orenacion 2
            sortOtherOtherOff();
        }
        return list;
    }
    
    /**
     * funcion privada que da paso a la ordenación. En esta funcion se hace 
     * una diferenciación del campo por el que queremos ordenar
     */
    private void sortOtherOtherOn(){
        switch(index){
            case 0://matricula
                sortOrderByMatOtherOrderOn();
                break;
            case 1://nombre
                sortOrderByNameOtherOrderOn();
                break;
            case 2://nota1
                sortOrderByNota1OtherOrderOn();
                break;
            case 3://nota2
                sortOrderByNota2OtherOrderOn();
                break;
            case 4://notaF
                sortOrderByNotaFiOtherOrderOn();
                break;
            case 5://notaE
                sortOrderByNotaExOtherOrderOn();
                break;
        }
    }
    
    /**
     * funcion privada que da paso a la ordenación. En esta funcion se hace 
     * una diferenciación del campo por el que queremos ordenar
     */
    private void sortOtherOtherOff(){
        switch(index){
            case 0://matricula
                sortOrderByMatOtherOrderOff();
                break;
            case 1://nombre
                sortOrderByNameOtherOrderOff();
                break;
            case 2://nota1
                sortOrderByNota1OtherOrderOff();
                break;
            case 3://nota2
                sortOrderByNota2OtherOrderOff();
                break;
            case 4://notaF
                sortOrderByNotaFiOtherOrderOff();
                break;
            case 5://notaE
                sortOrderByNotaExOtherOrderOff();
                break;
        }
    }
    
    
    /**
     * funcion que hace una ordenacion de una lista de alumnos a partir de la 
     * matricula y la opcion de ordenación true
     */
    private void sortOrderByMatOtherOrderOn(){
        list.sort(new Comparator<Alumn>(){
            @Override
            public int compare(Alumn arg0, Alumn arg1) {
                int res = 0;
                if(arg0.getNMatricula() < arg1.getNMatricula()){
                    res = 1;
                }else if(arg0.getNMatricula() > arg1.getNMatricula()){
                    res = -1;
                }
                return res;
            }
        });
    }
    
    /**
     * funcion que hace una ordenacion de una lista de alumnos a partir del
     * nombre y la opcion de ordenación true
     */
    private void sortOrderByNameOtherOrderOn(){
        list.sort(new Comparator<Alumn>(){
            @Override
            public int compare(Alumn arg0, Alumn arg1) {
                int res = 0;
                int length1 =  arg1.getNombre().length();
                int length0 =  arg0.getNombre().length();
                boolean needSecondCheck = true;
                
                //recorremos las palabras letra a letra hasta que encontramos 
                //una que no coincida
                for(int i = 0 ; i < length1 && i < length0; i++){
                    char c0 = arg0.getNombre().charAt(i);
                    char c1 = arg1.getNombre().charAt(i);
                    if(c0!=c1){
                        if(c0>c1){
                           res = -1; 
                        }else if (c0<c1){
                            res = 1; 
                        }
                        needSecondCheck=false;
                        break;
                    }
                }
                
                /*esta comprobacion ordena en los casos en los que todas las 
                letras fuera coincidentes hasta el final de una de las 
                palabras, pero tuvieran tamaños distintos*/
                if(needSecondCheck){
                    if(length0<length1){
                        res = 1;
                    }else if(length0>length1){
                        res = -1;
                    }
                }
                
                return res;
            }
        });
    }
    
    /**
     * funcion que hace una ordenacion de una lista de alumnos a partir de la 
     * nota 1 y la opcion de ordenación true
     */
    private void sortOrderByNota1OtherOrderOn(){
        list.sort(new Comparator<Alumn>(){
            @Override
            public int compare(Alumn arg0, Alumn arg1) {
                int res = 0;
                if(arg0.getNot1Ev() < arg1.getNot1Ev()){
                    res = 1;
                }else if(arg0.getNot1Ev() > arg1.getNot1Ev()){
                    res = -1;
                }
                return res;
            }
        });
    }
    
    /**
     * funcion que hace una ordenacion de una lista de alumnos a partir de la 
     * nota 2 y la opcion de ordenación true
     */
    private void sortOrderByNota2OtherOrderOn(){
        list.sort(new Comparator<Alumn>(){
            @Override
            public int compare(Alumn arg0, Alumn arg1) {
                int res = 0;
                if(arg0.getNot2Ev() < arg1.getNot2Ev()){
                    res = 1;
                }else if(arg0.getNot2Ev() > arg1.getNot2Ev()){
                    res = -1;
                }
                return res;
            }
        });
    }
    
    /**
     * funcion que hace una ordenacion de una lista de alumnos a partir de la 
     * nota final y la opcion de ordenación true
     */
    private void sortOrderByNotaFiOtherOrderOn(){
        list.sort(new Comparator<Alumn>(){
            @Override
            public int compare(Alumn arg0, Alumn arg1) {
                int res = 0;
                if(arg0.getNotaFinal()< arg1.getNotaFinal()){
                    res = 1;
                }else if(arg0.getNotaFinal() > arg1.getNotaFinal()){
                    res = -1;
                }
                return res;
            }
        });
    }
    
    /**
     * funcion que hace una ordenacion de una lista de alumnos a partir de la 
     * nota extra y la opcion de ordenación true
     */
    private void sortOrderByNotaExOtherOrderOn(){
        list.sort(new Comparator<Alumn>(){
            @Override
            public int compare(Alumn arg0, Alumn arg1) {
                int res = 0;
                if(arg0.getNotaExtra()< arg1.getNotaExtra()){
                    res = 1;
                }else if(arg0.getNotaExtra() > arg1.getNotaExtra()){
                    res = -1;
                }
                return res;
            }
        });
    }
    
    /**
     * funcion que hace una ordenacion de una lista de alumnos a partir de la 
     * matricula y la opcion de ordenación false
     */
    private void sortOrderByMatOtherOrderOff(){
        list.sort(new Comparator<Alumn>(){
            @Override
            public int compare(Alumn arg0, Alumn arg1) {
                int res = 0;
                if(arg0.getNMatricula() < arg1.getNMatricula()){
                    res = -1;
                }else if(arg0.getNMatricula() > arg1.getNMatricula()){
                    res = 1;
                }
                return res;
            }
        });
    }
    
    /**
     * funcion que hace una ordenacion de una lista de alumnos a partir del
     * nombre y la opcion de ordenación false
     */
    private void sortOrderByNameOtherOrderOff(){
        list.sort(new Comparator<Alumn>(){
            @Override
            public int compare(Alumn arg0, Alumn arg1) {
                int res = 0;
                int length1 =  arg1.getNombre().length();
                int length0 =  arg0.getNombre().length();
                boolean needSecondCheck = true;
                
                //recorremos las palabras letra a letra hasta que encontramos 
                //una que no coincida
                for(int i = 0 ; i < length1 && i < length0; i++){
                    char c0 = arg0.getNombre().charAt(i);
                    char c1 = arg1.getNombre().charAt(i);
                    if(c0!=c1){
                        if(c0>c1){
                           res = 1; 
                        }else if (c0<c1){
                            res = -1; 
                        }
                        needSecondCheck=false;
                        break;
                    }
                }
                
                /*esta comprobacion ordena en los casos en los que todas las 
                letras fuera coincidentes hasta el final de una de las 
                palabras, pero tuvieran tamaños distintos*/
                if(needSecondCheck){
                    if(length0<length1){
                        res = -1;
                    }else if(length0>length1){
                        res = 1;
                    }
                }
                
                return res;
            }
        });
    }
    
    /**
     * funcion que hace una ordenacion de una lista de alumnos a partir de la 
     * nota 1 y la opcion de ordenación false
     */
    private void sortOrderByNota1OtherOrderOff(){
        list.sort(new Comparator<Alumn>(){
            @Override
            public int compare(Alumn arg0, Alumn arg1) {
                int res = 0;
                if(arg0.getNot1Ev() < arg1.getNot1Ev()){
                    res = -1;
                }else if(arg0.getNot1Ev() > arg1.getNot1Ev()){
                    res = 1;
                }
                return res;
            }
        });
    }
    
    /**
     * funcion que hace una ordenacion de una lista de alumnos a partir de la 
     * nota 2 y la opcion de ordenación false
     */
    private void sortOrderByNota2OtherOrderOff(){
        list.sort(new Comparator<Alumn>(){
            @Override
            public int compare(Alumn arg0, Alumn arg1) {
                int res = 0;
                if(arg0.getNot2Ev() < arg1.getNot2Ev()){
                    res = -1;
                }else if(arg0.getNot2Ev() > arg1.getNot2Ev()){
                    res = 1;
                }
                return res;
            }
        });
    }
    
    /**
     * funcion que hace una ordenacion de una lista de alumnos a partir de la 
     * nota final y la opcion de ordenación false
     */
    private void sortOrderByNotaFiOtherOrderOff(){
        list.sort(new Comparator<Alumn>(){
            @Override
            public int compare(Alumn arg0, Alumn arg1) {
                int res = 0;
                if(arg0.getNotaFinal() < arg1.getNotaFinal()){
                    res = -1;
                }else if(arg0.getNotaFinal() > arg1.getNotaFinal()){
                    res = 1;
                }
                return res;
            }
        });
    }
    
    /**
     * funcion que hace una ordenacion de una lista de alumnos a partir de la 
     * nota extra y la opcion de ordenación false
     */
    private void sortOrderByNotaExOtherOrderOff(){
        list.sort(new Comparator<Alumn>(){
            @Override
            public int compare(Alumn arg0, Alumn arg1) {
                int res = 0;
                if(arg0.getNotaExtra() < arg1.getNotaExtra()){
                    res = -1;
                }else if(arg0.getNotaExtra() > arg1.getNotaExtra()){
                    res = 1;
                }
                return res;
            }
        });
    }
}
