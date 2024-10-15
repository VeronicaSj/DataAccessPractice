/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg;

import java.util.Date;

/**
 *
 * @author veron
 */
public class DateHelper {
    /**
     * Auxiliar function that return a string from a date using the "MM/DD/YYYY"
     * format
     * @param date
     * @return date using the "MM/DD/YYYY" format
     */
    public static String auxParseDateToString(Date date){
        return auxForce2char(""+(date.getMonth()+1))+"/"
                        + auxForce2char(""+date.getDate()) + "/" 
                        + (date.getYear()+1900);
    }
    
    /**
     * Auxiliar function that force a string to have two characters. 
     * Used at date parsing to string
     * @param str
     * @return normaliced string
     */
    private static String auxForce2char(String str){
        String res="";
        if(str.length()==1){
            res = 0+str;
        }
        return res;
    }
}
