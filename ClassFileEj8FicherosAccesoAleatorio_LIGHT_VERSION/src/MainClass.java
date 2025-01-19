
import java.util.Date;
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
                "        4 -> salir");
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
                    return;
            }
        } while (userOption !="4");
        
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
