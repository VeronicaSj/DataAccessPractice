package pkg;


import pkg.FileManager;
import pkg.Alumn;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


/**
 * Clase controlador del intercambio de informacion
 * @author veron
 */
public class MainClass {
    private static Scanner sc = new Scanner(System.in);
    private static String userOption = "";
    
    public static final FileManager FILE_MAN= new FileManager();
    
    /**
     * Funcion que contiene la "interfaz" de usuario 
     * @param args 
     */
    public static void main(String[] args) {
        do {            
            System.out.println("¿Que quieres hacer con la lista de alumnos?\n" +
                "        1 -> leer \n" +
                "        2 -> añadir\n" +
                "        3 -> modificar\n" +
                    
                "        4 -> leer campo de un alumno " +
                "        5 -> lista con TODOS los valores del campo buscado\n" +
                "        6 -> lista con los datos de un alumno\n" +
                "        7 -> diccionario con los datos de un alumno\n" +
                "        8 -> modificar a partir de un diccionario\n" +
                "        9 -> modificar SOLO el valor de un campo\n" +
                "        10 -> borrar alumno\n" +
                "        11 -> salir");
            userOption= sc.nextLine();
            
            switch (userOption){
                case "1":
                    read();
                    break;
                case "2":
                    add();
                    break;
                case "3":
                    update();
                    break;
                case "4":
                    readAlumnColumn();
                    break;
                case "5":
                    readAllColumn();
                    break;
                case "6":
                    readAlumnList();
                    break;
                case "7":
                    readAlumnDict();
                    break;
                case "8":
                    updateAlumnDict();
                    break;
                case "9":
                    updateAlumnColumn();
                    break;
                case "10":
                    delete();
                    break;
                case "11":
                    return;
            }
        } while (userOption !="4");
        
    }
    
    
    
    
    
    
    
    
    
    
    /**
     * Funcion que recoge el id y la columna de un alumno y muestra el valor 
     * asociado o un mensaje de "no encontrado"
     */
    private static void readAlumnColumn() {
        System.out.println("Introduce el id del alumno: ");
        int alumnId = askForInt("Id: ");
        FileManager.ColumnNames colName= auxAskForColumn();
        String res = FILE_MAN.selectCampo(alumnId, colName);
        
        if (res!=null) {
            System.out.println("Resultado: "+ res);
        }else{
            System.out.println("Dato no encontrado");
        }
    }

    /**
     * Funcion que recoge la columna de un alumno y muestra todos los valores 
     * asociados o un mensaje de "no encontrado"
     */
    private static void readAllColumn() {
        FileManager.ColumnNames colName= auxAskForColumn();
        List res = FILE_MAN.selectColumna(colName);
        
        if (res!=null) {
            System.out.println("Resultado: ");
            for (Object re : res) {
                System.out.println(re);
            }
        }else{
            System.out.println("Dato no encontrado");
        }
    }

    /**
     * Funcion que recoge el id de un alumno y muestra el valor 
     * asociado recorriendo una lista o un mensaje de "no encontrado"
     */
    private static void readAlumnList() {
        System.out.println("Introduce el id del alumno: ");
        int alumnId = askForInt("Id: ");
        
        List res = FILE_MAN.selectRowList(alumnId);
        
        if (res!=null) {
            System.out.println("Resultado: ");
            for (Object re : res) {
                System.out.println(re);
            }
        }else{
            System.out.println("Alumno no encontrado");
        }
    }

    /**
     * Funcion que recoge el id de un alumno y muestra el valor 
     * asociado recorriendo un Map o un mensaje de "no encontrado"
     */
    private static void readAlumnDict() {
        System.out.println("Introduce el id del alumno: ");
        int alumnId = askForInt("Id: ");
        
        Map res = FILE_MAN.selectRowMap(alumnId);
        
        if (res!=null) {
            System.out.println("Resultado: " );
            System.out.println("Id: "+
                    res.get(FileManager.ColumnNames.ENROLLMENT));
            System.out.println("Nombre: "+
                    res.get(FileManager.ColumnNames.NAME));
            System.out.println("Nota1: "+
                    res.get(FileManager.ColumnNames.MARK_1));
            System.out.println("Nota2: "+
                    res.get(FileManager.ColumnNames.MARK_2));
            System.out.println("Nota final: "+
                    res.get(FileManager.ColumnNames.MARK_FINAL));
            System.out.println("Nota extra: "+
                    res.get(FileManager.ColumnNames.MARK_EXTRA));
            System.out.println("Cumpleaños: "+
                    res.get(FileManager.ColumnNames.BIRTH_D));
        }else{
            System.out.println("Alumno no encontrado");
        }
    }

    /**
     * Funcion que recoge los datos de un alumno y lo actualiza o muestra un 
     * mensaje de error
     */
    private static void updateAlumnDict() {
        System.out.println("Introduce los datos del alumno: ");
        Alumn alumn = askForAlumn();
        HashMap map = new HashMap<FileManager.ColumnNames, Object>();
        
        if (alumn!=null) {
            map.put(FileManager.ColumnNames.ENROLLMENT, alumn.getEnrollment()+"");
            map.put(FileManager.ColumnNames.NAME, alumn.getName());
            map.put(FileManager.ColumnNames.MARK_1, alumn.getMark1Ev()+"");
            map.put(FileManager.ColumnNames.MARK_2, alumn.getMark2Ev()+"");
            map.put(FileManager.ColumnNames.MARK_FINAL, alumn.getMarkFinal()+"");
            map.put(FileManager.ColumnNames.MARK_EXTRA, alumn.getMarkExtra()+"");
            map.put(FileManager.ColumnNames.BIRTH_D, 
                    DateHelper.auxParseDateToString(alumn.getBirthD()));
            try{
                if (FILE_MAN.update(alumn.getEnrollment(), map)) {
                    System.out.println("Alumno actualizado");
                }else{
                    System.out.println("Alumno no actualizado");
                }
            }catch (IllegalArgumentException ex){
                System.out.println("Error de formato de fecha");
            }
                
            
        }
    }

    /**
     * Funcion que recoge el id, la columna y un valor de 
     * actualización de un alumno y actualiza al alumno introducido o muestra 
     * un mensaje de error
     */
    private static void updateAlumnColumn() {
        System.out.println("Introduce el id del alumno: ");
        int alumnId = askForInt("Id: ");
        FileManager.ColumnNames colName= auxAskForColumn();
        String res = askForString("introduzca el valor: ");
        
        if (FILE_MAN.update(alumnId, colName, res)) {
            System.out.println("Alumno actualizado");
        }else{
            System.out.println("Alumno no actualizado");
        }
    }

    /**
     * Funcion que recoge el id de un alumno y borra al alumno introducido o 
     * muestra un mensaje de error
     */
    private static void delete() {
        System.out.println("Introduce el id del alumno: ");
        int alumnId = askForInt("Id: ");
        
        if (FILE_MAN.delete(alumnId)) {
            System.out.println("Alumno borrado");
        }else{
            System.out.println("Alumno no encontrado");
        }
    }
    
    /**
     * Funcion auxiliar que pregunta al usuario por un campo del tipo de dato 
     * alumno
     * @return campo seleccionado por el usuario
     */
    private static FileManager.ColumnNames auxAskForColumn() {
        FileManager.ColumnNames col = null;
        
        System.out.println("Elige el campo:\n" +
                "        1 -> nombre\n" +
                "        2 -> Nota 1\n" +
                "        3 -> Nota 2\n" +
                "        4 -> Nota Final\n" +
                "        5 -> Nota Extra\n" +
                "        6 -> Fecha de nacimiento");
        userOption= sc.nextLine();
        
        switch (userOption){
            case "1":
                col = FileManager.ColumnNames.NAME;
                break;
            case "2":
                col = FileManager.ColumnNames.MARK_1;
                break;
            case "3":
                col = FileManager.ColumnNames.MARK_2;
                break;
            case "4":
                col = FileManager.ColumnNames.MARK_FINAL;
                break;
            case "5":
                col = FileManager.ColumnNames.MARK_EXTRA;
                break;
            case "6":
                col = FileManager.ColumnNames.BIRTH_D;
                break;
            default:
                System.out.println("Valor no valido");
        }
        return col;
    }
    
    
    
    
    
    
    
    
    
    /**
     * Funcion que pide un id al usuario y lugo intenta leer el alumno asociado 
     * en el fichero
     * @return el alumno leido en el fichero o null si no lo ha encontrado
     */
    public static Alumn read() {
        System.out.println("Introduce el id del alumno: ");
        int alumnId = askForInt("Id: ");
        
        Alumn alumn = FILE_MAN.readAlumn(alumnId);
        
        if (alumn!=null) {
            System.out.println(alumn.toString());
        }else{
            System.out.println("Alumno no encontrado");
        }
        
        return alumn;
    }
    
    /**
     * Funcion que pide los datosdatos de un alumno al usuario y luego intenta 
     * escribirlo en un fichero
     */
    public static void add() {
        System.out.println("Introduce los datos del alumno: ");
        Alumn alumn = askForAlumn();
        saveAlumn(alumn);
    }
    
    /**
     * Funcion que pide un id de alumno, pregunta por el dato a editar y lo 
     * intenta editar en el fichero
     */
    public static void update() {
        float auxFloat=Integer.MIN_VALUE;
        String auxStr = "";
        Date auxDate = null;
        
        Alumn alu = read();
        if (alu!=null) {
            do{
                System.out.println("¿Que dato quieres modificar?\n" +
                    "        1 -> nombre\n" +
                    "        2 -> Nota 1\n" +
                    "        3 -> Nota 2\n" +
                    "        4 -> Nota Final\n" +
                    "        5 -> Nota Extra\n" +
                    "        6 -> Fecha de nacimiento\n" +
                    "        7 -> Terminar modificacion");
                userOption= sc.nextLine();

                switch (userOption){
                    case "1":
                        auxStr=askForString("Nuevo valor: ");
                        if (auxStr!="") {
                            alu.setName(auxStr);
                        }
                        break;
                    case "2":
                        auxFloat=askForFloat("Nuevo valor: ");
                        if (auxFloat!=Integer.MIN_VALUE) {
                            alu.setMark1Ev(auxFloat);
                        }
                        break;
                    case "3":
                        auxFloat=askForFloat("Nuevo valor: ");
                        if (auxFloat!=Integer.MIN_VALUE) {
                            alu.setMark2Ev(auxFloat);
                        }
                        break;
                    case "4":
                        auxFloat=askForFloat("Nuevo valor: ");
                        if (auxFloat!=Integer.MIN_VALUE) {
                            alu.setMarkFinal(auxFloat);
                        }
                        break;
                    case "5":
                        auxFloat=askForFloat("Nuevo valor: ");
                        if (auxFloat!=Integer.MIN_VALUE) {
                            alu.setMarkExtra(auxFloat);
                        }
                        break;
                    case "6":
                        auxDate=askForDate("Nuevo valor: ");
                        if (auxDate!=null) {
                            alu.setBirthD(auxDate);
                        }
                        break;
                    case "7":
                        saveAlumn(alu);
                        return;
                    default:
                        System.out.println("Valor no valido");
                }
            }while(userOption!="7");
        }
    }
    
    /**
     * funcion que pide un entero y comprueba que sea un entero. Muestra por 
     * pantalla si es un valor invalido
     * @param msg mensaje de la peticion
     * @return el entero introducido o <code> Integer.MIN_VALUE</code> en caso 
     * de valor introducido invalido
     */
    private static int askForInt(String msg){
        int res = Integer.MIN_VALUE;
        String aux = "";
        System.out.print(msg);
        aux = sc.nextLine();
        try{
           res= Integer.parseInt(aux);
        }catch(IllegalArgumentException ex){
           res = Integer.MIN_VALUE; 
            System.out.println("Valor introducido no valido");
        }
        return res;
    }
    
    /**
     * funcion que pide un texto y comprueba que sea de menos de 50 caracteres. 
     * Muestra por pantalla si es un valor invalido
     * @param msg mensaje de la peticion
     * @return el texto introducido o "" en caso de valor introducido invalido
     */
    private static String askForString(String msg){
        String res = "";
        System.out.print(msg);
        res = sc.nextLine();
        if (res.length()>50) {
           res = "";
           System.out.println("Valor introducido no valido");
        }
        return res;
    }
    
    /**
     * funcion que pide un decimal y comprueba que sea un decimal. Muestra por 
     * pantalla si es un valor invalido
     * @param msg mensaje de la peticion
     * @return el decimal introducido o <code> Integer.MIN_VALUE</code> en caso 
     * de valor introducido invalido
     */
    private static float askForFloat(String msg){
        float res = Integer.MIN_VALUE;
        String aux = "";
        System.out.print(msg);
        aux = sc.nextLine();
        try{
           res= Float.parseFloat(aux);
        }catch(IllegalArgumentException ex){
           res = Integer.MIN_VALUE; 
            System.out.println("Valor introducido no valido");
        }
        return res;
    }
    
    /**
     * funcion que pide una fecha y comprueba que sea una fecha. Muestra por 
     * pantalla si es un valor invalido
     * @param msg mensaje de la peticion
     * @return fecha introducida o null en caso de valor introducido invalido
     */
    private static Date askForDate(String msg){
        Date res = null;
        String aux = "";
        System.out.print(msg);
        aux = sc.nextLine();
        try{
           res= new Date(aux);
        }catch(IllegalArgumentException ex){
           res = null; 
            System.out.println("Valor introducido no valido");
        }
        return res;
    }
    
    /**
     * Funcion que pide poco a poco los datos de un alumno. Si se introduce un 
     * dato invalido da mensaje de eeror y deja de pedir datos
     * @return el alumno introducido o null en caso de que alguno de los 
     * valores no sean validos
     */
    private static Alumn askForAlumn(){
        int id=Integer.MIN_VALUE;
        String name = "";
        float nota1 = Integer.MIN_VALUE;
        float nota2 = Integer.MIN_VALUE;
        float notaF = Integer.MIN_VALUE;
        float notaE = Integer.MIN_VALUE;
        Date date = null;
        Alumn alumn = null;
        
        id = askForInt("Id: ");
        if (id!=Integer.MIN_VALUE) {
            name = askForString("Nombre: ");
            if (name!="") {
                nota1 = askForFloat("Nota1: ");
                if (nota1!=Integer.MIN_VALUE) {
                    nota2 = askForFloat("Nota2: ");
                    if (nota2!=Integer.MIN_VALUE) {
                        notaF = askForFloat("Nota Final: ");
                        if (notaF!=Integer.MIN_VALUE) {
                            notaE = askForFloat("Nota Extra: ");
                            if (notaE!=Integer.MIN_VALUE) {
                                date = askForDate("Fecha de nacimiento: ");
                                if (date!=null) {
                                    alumn = new Alumn(id, name, nota1, nota2,
                                            notaF, notaE, date);
                                }
                            }
                        }
                    }
                }
            }
        }
        
        return alumn;
    }
    
    /**
     * funcion que guarda en el fichero un alumno pasado por parametro y 
     * muestra por pantalla si se ha guardado correctmente
     * @param alumn 
     */
    private static void saveAlumn(Alumn alumn){
        if (alumn!=null && FILE_MAN.writeAlumn(alumn)) {
            System.out.println("Alumno guardado");
        }else{
            System.out.println("Error de escritura");
        }
    }
}
