/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

/**
 *
 * @author veron
 */
public class LanguageManagerConstants {
    
    //variable para darle in numero distinto a cada id
    private static int autoIncremental = -1;
    
    //SCREENS ID CONSTANTS 
    public static final int MAIN_FRAME = getId();
    public static final int NOTIFICATIONS = getId();
    
    //MSG ID CONSTANTS
    public static final int ID_NOTIFICATIONS_MSG_ERROR = getId();//PLACE HOLDER (TO DELETE)
    
    public static final int ID_NOTIFICATIONS_MSG_ERROR_TOO_MUCH_SELECTED = getId();
    public static final int ID_NOTIFICATIONS_MSG_ERROR_NOTHING_SELECTED = getId();
    public static final int ID_NOTIFICATIONS_MSG_ERROR_NOT_VALID_INPUT = getId();
    public static final int ID_NOTIFICATIONS_MSG_ASK_FOR_UPDATE = getId();
    public static final int ID_NOTIFICATIONS_MSG_ASK_FOR_DELETE = getId();
    public static final int ID_NOTIFICATIONS_MSG_ERROR_UPDATE_ID = getId();
    public static final int ID_NOTIFICATIONS_MSG_ERROR_READ_ERROR = getId();
    public static final int ID_NOTIFICATIONS_MSG_ASK_ERROR_SAVE = getId();
    
    //VISUAL COMPONENT TEXT ID CONSTANT
    public static final int ID_MAIN_FRAME_TITLE = getId();
    
    public static final int ID_MAIN_FRAME_BTN_ADD = getId();
    public static final int ID_MAIN_FRAME_BTN_DELETE = getId();
    public static final int ID_MAIN_FRAME_BTN_UPDATE = getId();
    
    public static final int ID_MAIN_FRAME_TABLE_COLUMN_TITLE_ENROLMENT_ID = getId();
    public static final int ID_MAIN_FRAME_TABLE_COLUMN_TITLE_NAME = getId();
    public static final int ID_MAIN_FRAME_TABLE_COLUMN_TITLE_MARK_1 = getId();
    public static final int ID_MAIN_FRAME_TABLE_COLUMN_TITLE_MARK_2 = getId();
    public static final int ID_MAIN_FRAME_TABLE_COLUMN_TITLE_MARK_FINAL = getId();
    public static final int ID_MAIN_FRAME_TABLE_COLUMN_TITLE_MARK_EXTRA = getId();
    public static final int ID_MAIN_FRAME_TABLE_COLUMN_TITLE_BIRTH = getId();
    
    public static final int ID_MAIN_FRAME_LABEL_ENROLMENT_ID = getId();
    public static final int ID_MAIN_FRAME_LABEL_NAME = getId();
    public static final int ID_MAIN_FRAME_LABEL_MARK_1 = getId();
    public static final int ID_MAIN_FRAME_LABEL_MARK_2 = getId();
    public static final int ID_MAIN_FRAME_LABEL_MARK_FINAL = getId();
    public static final int ID_MAIN_FRAME_LABEL_MARK_EXTRA = getId();
    public static final int ID_MAIN_FRAME_LABEL_BIRTH = getId();
    
    public static final int ID_MAIN_FRAME_LABEL_ORDER_BY = getId();
    public static final int ID_MAIN_FRAME_LABEL_FILTER_BY = getId();
    public static final int ID_MAIN_FRAME_LABEL_FILTER_TEXT = getId();
    
    public static final int ID_MAIN_FRAME_BTN_CANGE_ORDER = getId();
    public static final int ID_MAIN_FRAME_BTN_FILTER_ON = getId();
    public static final int ID_MAIN_FRAME_BTN_FILTER_OFF = getId();
    
    public static final int ID_MAIN_FRAME_COM_BOX_ENROLMENT_ID = getId();
    public static final int ID_MAIN_FRAME_COM_BOX_NAME = getId();
    public static final int ID_MAIN_FRAME_COM_BOX_TITLE_MARK_1 = getId();
    public static final int ID_MAIN_FRAME_COM_BOX_TITLE_MARK_2 = getId();
    public static final int ID_MAIN_FRAME_COM_BOX_TITLE_MARK_FINAL = getId();
    public static final int ID_MAIN_FRAME_COM_BOX_TITLE_MARK_EXTRA = getId();
    public static final int ID_MAIN_FRAME_COM_BOX_BIRTH = getId();
    
    
    
    /**
     * funcion auxiliar para darle un id diferente a cada identificador de 
     * String
     * @return un identificador de String
     */
    private static int getId() {
        autoIncremental++;
        return autoIncremental;
    }
}
