/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

/**
 * clase que da dos opciones de idioma al usuario, inglés y español
 * @author veron
 */
public class LanguajeManager {
    private String currentLang;//idioma que vamos a utilizar
    
    
    public static final String LANG_ESPANIOL = "español";
    public static final String LANG_ENGLISH = "English";
    

    /**
     * constructor de el gestor de idiomas. Detecta el idioma del ordenador y 
     * hace la traduccion de los Strings usados en la aplicacion
     */
    public LanguajeManager() {
        //obtenemos el idioma del ordenador
        currentLang = Locale.getDefault().getDisplayLanguage();
    }

    /**
     * constructor del gestor de idiomas a partir de un idioma pasado por parametro
     * @param lang 
     */
    public LanguajeManager(String lang) {
        currentLang= lang;
    }
    
    /**
     * funcion auxiliar para obtener la lista de mensajes en español segun la 
     * pantalla que utilizamos. Esta funcion optimiza la busqueda de mensajes 
     * cuando tenemos más de una pantalla.
     * @return lista de mensajes en español
     */
    private String getSpanishMsg(int frameId, int id){
        String res = "Msg not found";
        
        if(LanguageManagerConstants.MAIN_FRAME == frameId){
            res = getSpanishMainMsg(id);
        }else if (LanguageManagerConstants.NOTIFICATIONS == frameId) {
            res = getSpanishNotificationMsg(id);
        } else{
            res = "Msg not found";
        }
        
        return res;
    }
    
    /**
     * funcion auxiliar para obtener la lista de mensajes en español de la 
     * pantalla principal
     * @param id del mensaje
     * @return mensaje correspondiente al id
     */
    private String getSpanishMainMsg(int id){
        String res = "Msg not found";
        
        //I can´t use switch because I'm using constants
        if(LanguageManagerConstants.ID_MAIN_FRAME_TITLE == id){
            res = "ClassFileEj8FicherosAccesoAleatorioAccDatVeronicaSanchezNB";
        } else if(LanguageManagerConstants.ID_MAIN_FRAME_BTN_ADD == id){
            res = "Añadir";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_BTN_DELETE == id){
            res = "Borrar";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_BTN_UPDATE == id){
            res = "Actualizar";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_TABLE_COLUMN_TITLE_ENROLMENT_ID == id){
            res = "Id Matricula";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_TABLE_COLUMN_TITLE_NAME == id){
            res = "Nombre";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_TABLE_COLUMN_TITLE_MARK_1 == id){
            res = "Nota 1";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_TABLE_COLUMN_TITLE_MARK_2 == id){
            res = "Nota 2";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_TABLE_COLUMN_TITLE_MARK_FINAL == id){
            res = "Nota Final";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_TABLE_COLUMN_TITLE_MARK_EXTRA == id){
            res = "Nota Extra";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_TABLE_COLUMN_TITLE_BIRTH == id){
            res = "Fecha Nacimiento";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_LABEL_ENROLMENT_ID == id){
            res = "Id Matricula: ";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_LABEL_NAME == id){
            res = "Nombre: ";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_LABEL_MARK_1 == id){
            res = "Nota 1: ";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_LABEL_MARK_2 == id){
            res = "Nota 2: ";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_LABEL_MARK_FINAL == id){
            res = "Nota Final: ";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_LABEL_MARK_EXTRA == id){
            res = "Nota Extra: ";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_LABEL_BIRTH == id){
            res = "Fecha Nacimiento: ";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_LABEL_ORDER_BY == id){
            res = "Ordenar por: ";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_LABEL_FILTER_BY == id){
            res = "Filtrar por: ";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_LABEL_FILTER_TEXT == id){
            res = "Texto: ";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_BTN_CANGE_ORDER == id){
            res = "Invertir Orden";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_BTN_FILTER_ON == id){
            res = "Filtrar";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_BTN_FILTER_OFF == id){
            res = "Quitar Filtro";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_COM_BOX_ENROLMENT_ID == id){
            res = "Id Matricula";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_COM_BOX_NAME == id){
            res = "Nombre";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_COM_BOX_TITLE_MARK_1 == id){
            res = "Nota 1";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_COM_BOX_TITLE_MARK_2 == id){
            res = "Nota 2";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_COM_BOX_TITLE_MARK_FINAL == id){
            res = "Nota Final";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_COM_BOX_TITLE_MARK_EXTRA == id){
            res = "Nota Extra";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_COM_BOX_BIRTH == id){
            res = "Fecha Nacimiento";
        }
        
        return res;
    }
    
    /**
     * funcion auxiliar para obtener la lista de mensajes en español de las
     * notificaciones
     * @param id del mensaje
     * @return mensaje correspondiente al id
     */
    private String getSpanishNotificationMsg(int id){
        String res = "Msg not found";
        
        if(LanguageManagerConstants.ID_NOTIFICATIONS_MSG_ERROR_TOO_MUCH_SELECTED == id){
            res = "Hay mas de un registro seleccionado en la tabla";
        }else if (LanguageManagerConstants.ID_NOTIFICATIONS_MSG_ERROR_NOTHING_SELECTED == id) {
            res = "Debe seleccionar un registro de la tabla";
        }else if (LanguageManagerConstants.ID_NOTIFICATIONS_MSG_ERROR_NOT_VALID_INPUT == id) {
            res = "Los datos del alumno introducidos no son validos";
        }else if (LanguageManagerConstants.ID_NOTIFICATIONS_MSG_ASK_FOR_UPDATE == id) {
            res = "El alumno ya existe ¿Quiere editarlo?";
        }else if (LanguageManagerConstants.ID_NOTIFICATIONS_MSG_ASK_FOR_DELETE == id) {
            res = "¿Seguro que quiere borrar al alumno seleccionado?";
        }else if (LanguageManagerConstants.ID_NOTIFICATIONS_MSG_ERROR_UPDATE_ID == id) {
            res = "No puedes editar la matricula";
        }else if (LanguageManagerConstants.ID_NOTIFICATIONS_MSG_ERROR_READ_ERROR == id) {
            res = "Error durante la carga de datos";
        }else if (LanguageManagerConstants.ID_NOTIFICATIONS_MSG_ASK_ERROR_SAVE== id) {
            res = "Error al guardar los datos¿Desea salir igualmente?";
        }
        
        return res;
    }
    
    /**
     * funcion auxiliar para obtener la lista de mensajes en inglés segun la 
     * pantalla que utilizamos. Esta funcion optimiza la busqueda de mensajes 
     * cuando tenemos más de una pantalla.
     * @return lista de mensajes en inglés
     */
    private String getEnglishMsg(int frameId, int id){
        String res = "Msg not found";
        if(LanguageManagerConstants.MAIN_FRAME == frameId){
            res = getEnglishMainMsg(id);
        }else if(LanguageManagerConstants.NOTIFICATIONS == frameId){
            res = getEnglishNotificationMsg(id);
        }else{
            res = "Msg not found";
        }
        return res;
    }
    
    /**
     * funcion auxiliar para obtener la lista de mensajes en inlges de la 
     * pantalla principal
     * @param id del mensaje
     * @return mensaje correspondiente al id
     */
    private String getEnglishMainMsg(int id){
        String res = "Msg not found";
        if(LanguageManagerConstants.ID_MAIN_FRAME_TITLE == id){
            res = "Veronica Sanchez Justicia";
        } else if(LanguageManagerConstants.ID_MAIN_FRAME_BTN_ADD == id){
            res = "Add";
        } else if(LanguageManagerConstants.ID_MAIN_FRAME_BTN_DELETE == id){
            res = "Delete";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_BTN_UPDATE == id){
            res = "Update";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_TABLE_COLUMN_TITLE_ENROLMENT_ID == id){
            res = "Enrolment id";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_TABLE_COLUMN_TITLE_NAME == id){
            res = "Name";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_TABLE_COLUMN_TITLE_MARK_1 == id){
            res = "1º Mark";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_TABLE_COLUMN_TITLE_MARK_2 == id){
            res = "2º Mark";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_TABLE_COLUMN_TITLE_MARK_FINAL == id){
            res = "Final Mark";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_TABLE_COLUMN_TITLE_MARK_EXTRA == id){
            res = "Extra Mark";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_TABLE_COLUMN_TITLE_BIRTH == id){
            res = "Birth Date";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_LABEL_ENROLMENT_ID == id){
            res = "Enrolment id: ";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_LABEL_NAME == id){
            res = "Name: ";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_LABEL_MARK_1 == id){
            res = "1º Mark: ";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_LABEL_MARK_2 == id){
            res = "2º Mark: ";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_LABEL_MARK_FINAL == id){
            res = "Final Mark: ";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_LABEL_MARK_EXTRA == id){
            res = "Extra Mark: ";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_LABEL_BIRTH == id){
            res = "Birth Date: ";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_LABEL_ORDER_BY == id){
            res = "Order by: ";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_LABEL_FILTER_BY == id){
            res = "Filter by: ";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_LABEL_FILTER_TEXT == id){
            res = "Text: ";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_BTN_CANGE_ORDER == id){
            res = "Invert Order";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_BTN_FILTER_ON == id){
            res = "Filter ON";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_BTN_FILTER_OFF == id){
            res = "Filter OFF";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_COM_BOX_ENROLMENT_ID == id){
            res = "Enrolment id";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_COM_BOX_NAME == id){
            res = "Name";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_COM_BOX_TITLE_MARK_1 == id){
            res = "1º Mark";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_COM_BOX_TITLE_MARK_2 == id){
            res = "2º Mark";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_COM_BOX_TITLE_MARK_FINAL == id){
            res = "Final Mark";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_COM_BOX_TITLE_MARK_EXTRA == id){
            res = "Extra Mark";
        }else if(LanguageManagerConstants.ID_MAIN_FRAME_COM_BOX_BIRTH == id){
            res = "Birth Date";
        }
        
        return res;
    }
    
    
    /**
     * funcion auxiliar para obtener la lista de mensajes en español de las
     * notificaciones
     * @param id del mensaje
     * @return mensaje correspondiente al id
     */
    private String getEnglishNotificationMsg(int id){
        String res = "Msg not found";
        
        if(LanguageManagerConstants.ID_NOTIFICATIONS_MSG_ERROR_TOO_MUCH_SELECTED == id){
            res = "There are more than one selected row at the table";
        }else if (LanguageManagerConstants.ID_NOTIFICATIONS_MSG_ERROR_NOTHING_SELECTED == id) {
            res = "You must select one row at the table";
        }else if (LanguageManagerConstants.ID_NOTIFICATIONS_MSG_ERROR_NOT_VALID_INPUT == id) {
            res = "The inserted alumn data are not valid";
        }else if (LanguageManagerConstants.ID_NOTIFICATIONS_MSG_ASK_FOR_UPDATE == id) {
            res = "Alumn already exists. Do you want to update?";
        }else if (LanguageManagerConstants.ID_NOTIFICATIONS_MSG_ASK_FOR_DELETE == id) {
            res = "Are you sure you want to delete the selected alumn?";
        }else if (LanguageManagerConstants.ID_NOTIFICATIONS_MSG_ERROR_UPDATE_ID == id) {
            res = "You cant update the enrolment id";
        }else if (LanguageManagerConstants.ID_NOTIFICATIONS_MSG_ERROR_READ_ERROR == id) {
            res = "Error while loading data";
        }else if (LanguageManagerConstants.ID_NOTIFICATIONS_MSG_ASK_ERROR_SAVE== id) {
            res = "Error  while saving data. Do you want to exit anyway?";
        }
        
        return res;
    }
    
    /**
     * Funcion que devuelve un mensaje según el identificador introducido por el usuario
     * @param frameId identificador de la pantalla
     * @param id el identificador del mensaje
     * @return Un String con el mensaje requerido o "Msg not found" si se a 
     * pedido un id que no existe.
     */
    public String getMsg(int frameId, int id){
        String res="Msg not found";
        //damos el ingles como opción por defecto
        switch(currentLang){
            case "español":
                res = getSpanishMsg(frameId, id);
                break;
            default:
                res = getEnglishMsg(frameId, id);
        }
        return res;
    }
    
}
