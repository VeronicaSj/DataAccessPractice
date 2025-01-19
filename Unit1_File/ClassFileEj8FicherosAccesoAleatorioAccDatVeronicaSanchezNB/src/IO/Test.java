/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IO;

import Model.AlumnArray;
import java.io.File;

/**
 *
 * @author veron
 */
public class Test {
    private static final File FILE= new File("alumn.txt");

    public Test() {
        //System.out.println(FILE.length());
    
        /*FileManager fm= new FileManager();
        AlumnArray aa= fm.readFile();
        if (aa!=null) {
            for (int i = 0; i < aa.size(); i++) {
                if (aa.get(i)!=null) {
                    System.out.println(aa.get(i).toString());
                }else{
                    System.out.println("TEST: leido y devuelto a lista alumno nulo"); 
                }
            }
        }else{
            System.out.println("TEST: array devuelto nulo");    
        }*/
        
    }
    
}
