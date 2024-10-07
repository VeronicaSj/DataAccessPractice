/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.MainController;
import IO.Test;
import Model.Alumn;
import Model.AlumnArray;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author veron
 */
public class MainView extends javax.swing.JFrame {
    
    //atribujos de lenguaje
    private static final LanguajeManager LANG_MAN= new LanguajeManager();
    
    //elementos visuales
    private final ViewElements VIEW_E = new ViewElements(this);
    
    //atributos de visualización de los alumnos
    private static boolean isOtherOrderOn = false;
    private static boolean isFilterOn = false;
    
    //para controlar la tabla
    private DefaultTableModel tModel;
    
    //para controlar la aplicación
    private MainController mCont;
    
    /**
     * Creates new form MainFrame
     */
    public MainView() {
        new Test();
        
        //vista
        initComponents();
        setTitle(LANG_MAN.getMsg(LanguageManagerConstants.MAIN_FRAME,
                LanguageManagerConstants.ID_MAIN_FRAME_TITLE));
        setLocationRelativeTo(null);
        
        //controlador
        mCont =  new MainController();
        
        //tabla
        tModel = (DefaultTableModel) table.getModel();
        ArrayList auxlist = mCont.getAlumnList();
        
        if (auxlist==null) {
            VIEW_E.msgError(LANG_MAN.getMsg(LanguageManagerConstants.NOTIFICATIONS, 
                    LanguageManagerConstants.ID_NOTIFICATIONS_MSG_ERROR_READ_ERROR));
           System.exit(0);
        }
        
        loadTableInfo(mCont.getAlumnList());
        
    }
    
    /**
     * funcion que acomoda una lista de alumnos en una tabla
     * @param list lista de alumnos a colocar en la tabla
     */
    private void loadTableInfo(AlumnArray list){
        cleanTable();//limpiamos la tabla
        Alumn alu = null;
        
        //recorremos la lista y la vamos colocando en la tabla
        for (int i = 0; i < list.size() ; i++) {
            alu= list.get(i);
            myAddRow(alu);
        }
    }
    
    /**
     * funcion para añadir un registro a la tabla
     * @param alu registro que queremos añadir
     */
    private void myAddRow(Alumn alu){
        tModel.addRow(new Object[]{alu.getNMatricula(), alu.getNombre(),
                alu.getNot1Ev(), alu.getNota2Ev(), alu.getNotaFinal(), 
                alu.getNotaExtra(), alu.getbirthD()});
    }
    
    /**
     * función para vaciar la tabla
     */
    private void cleanTable(){
        //si la tabla no tien contenido no hay que limpiar
        if (table.getRowCount()>0) { 
            //la recorremos y la limpiamos
            for (int i = 0; i < tModel.getRowCount(); i++) {
                tModel.removeRow(0);
            }
        }
    }
    
    /**
     * función para recoger el alumno que el usuario ha seleccionado de la tabla
     * @return el alumno seleccionado por el usuario o null si no hay selección
     */
    private Alumn getSelectedAlumn(){
        Alumn res = null;
        
        if (table.getSelectedRowCount() > 0) {//si no hay nada seleccionado nos salimos directamente
            int SelectedRow = table.getSelectedRow();//el registro seleccionado
            
            /*Para recoger la info de la tabla tenemos que acudir a tModel. El 
            usuario puede mover las columnas. tModel no se ve afectado por el 
            orden de las columnas.*/
            int matricula = (int) tModel.getValueAt(SelectedRow, 0);
            String nom = (String) tModel.getValueAt(SelectedRow, 1);
            float nota1 = (float) tModel.getValueAt(SelectedRow, 2);
            float nota2 = (float) tModel.getValueAt(SelectedRow, 3);
            float notaFinal = (float) tModel.getValueAt(SelectedRow, 4);
            float notaExtra = (float) tModel.getValueAt(SelectedRow, 5);
            String birthDateStr = (String) tModel.getValueAt(SelectedRow, 6);
            
            Date birthDate = new Date(birthDateStr);

            res = new Alumn(matricula,nom,nota1,nota2,notaFinal,notaExtra,birthDate);
        }
        return res;
    }
    
    /**
     * Funcion que comprueba si el usuario ha seleccionado adecuadamente un 
     * registro de la tabla y le da avisos si no lo ha hecho bien
     * @return devuelve true si el usuario ha hecho bien la selección y false 
     * en caso contrario
     */
    private boolean checkTableUserSelection(){
        boolean res = false;
        if (table.getSelectedRowCount() == 1) {//opción 1: todo bien
            res = true;
        }else if (table.getSelectedRowCount() > 1) {//opción 2: hay muchos registros seleccionados
            VIEW_E.msgError(LANG_MAN.getMsg(LanguageManagerConstants.NOTIFICATIONS, 
                    LanguageManagerConstants.ID_NOTIFICATIONS_MSG_ERROR_TOO_MUCH_SELECTED));
        } else{//opción 3: no hay nada seleccionado
            VIEW_E.msgError(LANG_MAN.getMsg(LanguageManagerConstants.NOTIFICATIONS, 
                    LanguageManagerConstants.ID_NOTIFICATIONS_MSG_ERROR_NOTHING_SELECTED));
        }
        return res;
    }
    
    /**
     * funcion que vacía los textFields relacionados con la información de 
     * alumnos
     */
    private void emptyAlumnTFields(){
        tFieldNMatricula.setText("");
        tFieldNom.setText("");
        tFieldNota1.setText("");
        tFieldNota2.setText("");
        tFieldNotaF.setText("");
        tFieldNotaE.setText("");
        tFieldDate.setText("");
    }
    
    /**
     * funcion que rellena los los textFields relacionados con la información de 
     * alumnos.
     * @param alumn alumno del que queremos sacar la info para rellenar los 
     * textFields
     */
    private void fillAlumnTFields(Alumn alumn){
        if (alumn != null) { //si el alumno es null, no modemos sacarle info
            tFieldNMatricula.setText(alumn.getNMatricula()+"");
            tFieldNom.setText(alumn.getNombre());
            tFieldNota1.setText(alumn.getNot1Ev()+"");
            tFieldNota2.setText(alumn.getNota2Ev()+"");
            tFieldNotaF.setText(alumn.getNotaFinal()+"");
            tFieldNotaE.setText(alumn.getNotaExtra()+"");   
            tFieldDate.setText(alumn.getbirthD().toString());
        }
    }
    
    /**
     * funcion para modificar la información de un alumno
     * @param oldAlu 
     * @param newAlu 
     */
    private void updateAlumn(Alumn oldAlu, Alumn newAlu){
        //hacemos el intercambio de alumno
        mCont.updateAlumn(oldAlu,newAlu);
        //volvemos a cargar la lista en la tabla
        loadTableInfo(mCont.getAlumnList());
    }
    
    /**
     * función para recoget el alumno introducido por el usuario a través de los
     * textFields
     * @return alumno introducido a través de los textFields
     */
    public Alumn getUserAlumn(){
        Alumn alu = null;
        int nMatricula = 0;
        String nombre = "";
        float not1Ev = 0;
        float nota2Ev = 0;
        float notaFinal = 0;
        float notaExtra = 0;
        String birthDStr="";
        
        /*aquí hay un conjunto de conmprobaciones escalonadas porque si no 
        pasamos alguno de los escalones no es necesario seguir con el siguiente*/
        try{//comprobacion1: la matricula es un numero
            nMatricula = Integer.parseInt(tFieldNMatricula.getText());
            nombre = tFieldNom.getText();
            birthDStr=tFieldDate.getText();
            //comp2: hay algún nombre escrito y alguna fecha
            if(nombre.length()>0){
                if (birthDStr.length()>0) {
                    try{//comprobacion3: las notas tienen que ser numeros decimales
                        not1Ev = Float.parseFloat(tFieldNota1.getText());
                        nota2Ev = Float.parseFloat(tFieldNota2.getText());
                        notaFinal = Float.parseFloat(tFieldNotaF.getText());
                        notaExtra = Float.parseFloat(tFieldNotaE.getText());


                        /*si alguno de estas comprobaciones falla, avisamos al 
                        usuario y nos salimos del recorrido*/
                    }catch(Exception e){
                        VIEW_E.msgError(LANG_MAN.getMsg(LanguageManagerConstants.NOTIFICATIONS, 
                    LanguageManagerConstants.ID_NOTIFICATIONS_MSG_ERROR_NOT_VALID_INPUT));
                        return null;
                    }
                }
            }else{
                VIEW_E.msgError(LANG_MAN.getMsg(LanguageManagerConstants.NOTIFICATIONS, 
                    LanguageManagerConstants.ID_NOTIFICATIONS_MSG_ERROR_NOT_VALID_INPUT));
                return null;
            }
        }catch(Exception e){
            VIEW_E.msgError(LANG_MAN.getMsg(LanguageManagerConstants.NOTIFICATIONS, 
                    LanguageManagerConstants.ID_NOTIFICATIONS_MSG_ERROR_NOT_VALID_INPUT));
            return null;
        }
        
        //TODO
        Date birthD = new Date(birthDStr);
        
        //si todo va bien devolvemod el alumno formado
        alu = new Alumn(nMatricula, nombre, not1Ev, nota2Ev, notaFinal, 
                notaExtra, birthD);
        return alu;
    }
    
    /**
     * finción que ordena el contenido del array y lo muestra. Es arrray 
     * puede estar filtrado o no estar filtrado
     */
    private void sortTable(){
        if (isFilterOn) {//opcion 1: fltro activado
            AlumnArray filteredArray = mCont.getfilteredList();
            filteredArray.sort(jComboBox1.getSelectedIndex(), isOtherOrderOn);
            loadTableInfo(filteredArray);
        }else{//opcion 2: fltro desactivado
            AlumnArray array = mCont.getAlumnList();
            array.sort(jComboBox1.getSelectedIndex(), isOtherOrderOn);
            loadTableInfo(array);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tFieldNom = new javax.swing.JTextField();
        tFieldNota1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        tFieldNota2 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        tFieldNotaF = new javax.swing.JTextField();
        tFieldFilter = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnFilter = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnQuitFilter = new javax.swing.JButton();
        tFieldNotaE = new javax.swing.JTextField();
        jToolBar1 = new javax.swing.JToolBar();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tFieldNMatricula = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tFieldDate = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nº Matricula", "Nombre", "Nota 1º Ev", "Nota 2º Ev", "Nota Final", "Nota Extra" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel6.setText(LANG_MAN.getMsg(
            LanguageManagerConstants.MAIN_FRAME,
            LanguageManagerConstants.ID_MAIN_FRAME_LABEL_MARK_EXTRA
        ));

        jToggleButton1.setText(LANG_MAN.getMsg(
            LanguageManagerConstants.MAIN_FRAME,
            LanguageManagerConstants.ID_MAIN_FRAME_BTN_CANGE_ORDER
        ));
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jLabel8.setText(LANG_MAN.getMsg(
            LanguageManagerConstants.MAIN_FRAME,
            LanguageManagerConstants.ID_MAIN_FRAME_LABEL_FILTER_BY
        ));

        jLabel2.setText(LANG_MAN.getMsg(
            LanguageManagerConstants.MAIN_FRAME,
            LanguageManagerConstants.ID_MAIN_FRAME_LABEL_NAME));

    jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nº Matricula", "Nombre", "Nota 1º Ev", "Nota 2º Ev", "Nota Final", "Nota Extra" }));

    jLabel3.setText(LANG_MAN.getMsg(
        LanguageManagerConstants.MAIN_FRAME,
        LanguageManagerConstants.ID_MAIN_FRAME_LABEL_MARK_1
    ));

    btnFilter.setText(LANG_MAN.getMsg(
        LanguageManagerConstants.MAIN_FRAME,
        LanguageManagerConstants.ID_MAIN_FRAME_BTN_FILTER_ON
    ));
    btnFilter.setFocusable(false);
    btnFilter.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    btnFilter.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    btnFilter.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnFilterActionPerformed(evt);
        }
    });

    jLabel1.setText(LANG_MAN.getMsg(
        LanguageManagerConstants.MAIN_FRAME,
        LanguageManagerConstants.ID_MAIN_FRAME_LABEL_ENROLMENT_ID));

btnQuitFilter.setText(LANG_MAN.getMsg(
    LanguageManagerConstants.MAIN_FRAME,
    LanguageManagerConstants.ID_MAIN_FRAME_BTN_FILTER_OFF
    ));
    btnQuitFilter.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnQuitFilterActionPerformed(evt);
        }
    });

    jToolBar1.setRollover(true);

    btnAdd.setText(LANG_MAN.getMsg(
        LanguageManagerConstants.MAIN_FRAME,
        LanguageManagerConstants.ID_MAIN_FRAME_BTN_ADD));
btnAdd.setFocusable(false);
btnAdd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
btnAdd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
btnAdd.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnAddActionPerformed(evt);
    }
    });
    jToolBar1.add(btnAdd);

    btnDelete.setText(LANG_MAN.getMsg(
        LanguageManagerConstants.MAIN_FRAME,
        LanguageManagerConstants.ID_MAIN_FRAME_BTN_DELETE));
btnDelete.setFocusable(false);
btnDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
btnDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
btnDelete.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnDeleteActionPerformed(evt);
    }
    });
    jToolBar1.add(btnDelete);

    btnUpdate.setText(LANG_MAN.getMsg(
        LanguageManagerConstants.MAIN_FRAME,
        LanguageManagerConstants.ID_MAIN_FRAME_BTN_UPDATE));
btnUpdate.setFocusable(false);
btnUpdate.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
btnUpdate.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
btnUpdate.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnUpdateActionPerformed(evt);
    }
    });
    jToolBar1.add(btnUpdate);

    table.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Nº Matricula", "Nombre", "Nota 1º Ev", "Nota 2º Ev", "Nota Final", "Nota Extra", "Fecha Nac"
        }
    ) {
        Class[] types = new Class [] {
            java.lang.Integer.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.String.class
        };
        boolean[] canEdit = new boolean [] {
            false, false, false, false, false, false, false
        };

        public Class getColumnClass(int columnIndex) {
            return types [columnIndex];
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    table.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mousePressed(java.awt.event.MouseEvent evt) {
            tableMousePressed(evt);
        }
    });
    jScrollPane1.setViewportView(table);

    jLabel5.setText(LANG_MAN.getMsg(
        LanguageManagerConstants.MAIN_FRAME,
        LanguageManagerConstants.ID_MAIN_FRAME_LABEL_MARK_FINAL
    ));

    jLabel9.setText(LANG_MAN.getMsg(
        LanguageManagerConstants.MAIN_FRAME,
        LanguageManagerConstants.ID_MAIN_FRAME_LABEL_FILTER_TEXT
    ));

    jLabel4.setText(LANG_MAN.getMsg(
        LanguageManagerConstants.MAIN_FRAME,
        LanguageManagerConstants.ID_MAIN_FRAME_LABEL_MARK_2
    ));

    jLabel7.setText(LANG_MAN.getMsg(
        LanguageManagerConstants.MAIN_FRAME,
        LanguageManagerConstants.ID_MAIN_FRAME_LABEL_ORDER_BY
    ));

    jLabel10.setText(LANG_MAN.getMsg(
        LanguageManagerConstants.MAIN_FRAME,
        LanguageManagerConstants.ID_MAIN_FRAME_LABEL_BIRTH
    ));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jToggleButton1)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(tFieldNom)
                                .addComponent(tFieldNota1)
                                .addComponent(tFieldNota2)
                                .addComponent(tFieldNotaF)
                                .addComponent(tFieldNotaE)
                                .addComponent(tFieldNMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tFieldDate)))
                    .addGap(45, 45, 45))
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnFilter)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnQuitFilter))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tFieldFilter))
                            .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.LEADING, 0, 332, Short.MAX_VALUE)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(tFieldNMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(tFieldNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(tFieldNota1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tFieldNota2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)))
                .addGroup(layout.createSequentialGroup()
                    .addGap(8, 8, 8)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(tFieldNotaF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(tFieldNotaE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jLabel7)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(tFieldDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jToggleButton1))
                    .addGap(18, 18, 18)
                    .addComponent(jLabel8)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tFieldFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9))))
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(btnFilter)
                .addComponent(btnQuitFilter))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        sortTable();//ordenamos la tabla
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        isOtherOrderOn = !isOtherOrderOn; //avisamos a toda la aplicación del camio de orden
        sortTable();//ordenamos la tabla
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void btnFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterActionPerformed
        AlumnArray array = mCont.getAlumnList();//obtenemos la lista de alumn
        String filText= tFieldFilter.getText();//obtenemos el motivo para filtrar
        AlumnArray filteredArray = new AlumnArray();//renovamos el array filtrado
        if (filText.length() > 0) {//si hay filtro filtramos, sino, no
            /*recorremos la lista de alumnos buscando los que coinciden con el
            filtro. El algoritmo de busqueda utilizado presupone que no se van
            a guardar muchos alumnos. Este algoritmo de busquedo no es el más
            rápido en caso de que la lista de alumnos sea demasiado larga */
            for (int i = 0; i < array.size(); i++) {
                Alumn alu = array.get(i);//alumno que toca comparar

                /*buscamos que campo ha seleccionado el usuario para hacer la
                busqueda*/
                switch(jComboBox2.getSelectedIndex()){
                    case 0://matricula
                    if ((alu.getNMatricula()+"").contains(filText)) {
                        filteredArray.add(alu);
                    }
                    break;
                    case 1://nombre
                    if (alu.getNombre().contains(filText)) {
                        filteredArray.add(alu);
                    }
                    break;
                    case 2://nota1
                    if ((alu.getNot1Ev()+"").contains(filText)) {
                        filteredArray.add(alu);
                    }
                    break;
                    case 3://nota2
                    if ((alu.getNota2Ev()+"").contains(filText)) {
                        filteredArray.add(alu);
                    }
                    break;
                    case 4://notaF
                    if ((alu.getNotaFinal()+"").contains(filText)) {
                        filteredArray.add(alu);
                    }
                    break;
                    case 5://notaE
                    if ((alu.getNotaExtra()+"").contains(filText)) {
                        filteredArray.add(alu);
                    }
                    break;
                }
            }

            loadTableInfo(filteredArray);//mosotramos los resultados
            isFilterOn = true;//cambiamos el estado del filtro a cativado
            mCont.setFilteredList(filteredArray);//pasamos los resultados al controlador
        }
    }//GEN-LAST:event_btnFilterActionPerformed

    private void btnQuitFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitFilterActionPerformed
        loadTableInfo(mCont.getAlumnList());//recargamos la tabla
        isFilterOn = false;//avisamos de que vamos sin filtros
    }//GEN-LAST:event_btnQuitFilterActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        Alumn alu = getUserAlumn();//obtenemos alumno del usuario
        AlumnArray list = mCont.getAlumnList();//obtenemos lista de alumnos
        if(alu != null){//si el alumn es null no hacemos nada
            if(!list.contains(alu)){//si la lista no contiene a ese alumno
                mCont.addAlumn(alu);//añadimos alumno a lalista
                myAddRow(alu);//añadimos alumno a la tabla
                emptyAlumnTFields();//vaciamos los textFields de alumno
            }else{//si la lista contiene al alumno
                //preguntamos si lo que en realidad se quiere hacer es modificar
                if (VIEW_E.askUserConfirmation(LANG_MAN.getMsg(LanguageManagerConstants.NOTIFICATIONS, 
                    LanguageManagerConstants.ID_NOTIFICATIONS_MSG_ASK_FOR_UPDATE)) == true) {
                    updateAlumn(list.get(list.indexOf(alu)), alu);//modificamos
                }
            }
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        //comprovamos que el user haya seleccionado un registro
        if (checkTableUserSelection()) {
            //le pedimos confirmación al usuario
            if (VIEW_E.askUserConfirmation(LANG_MAN.getMsg(LanguageManagerConstants.NOTIFICATIONS, 
                    LanguageManagerConstants.ID_NOTIFICATIONS_MSG_ASK_FOR_DELETE))==true) {
                mCont.removeAlumn(getSelectedAlumn());//borramos
                loadTableInfo(mCont.getAlumnList());//cargamos la tabla
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (checkTableUserSelection()) {//comprovamos seleccion del user
            Alumn oldAlu = getSelectedAlumn();//cojemos el viejo alumno
            Alumn newAlu = getUserAlumn();//cojemos el nuevo alumnos
            if (oldAlu.equals(newAlu)) {//con equals comprobamos que la matricula sea la misma
                updateAlumn(oldAlu, newAlu);//lo actualizamos
            }else{//si el usuario ha editado la matricula
                VIEW_E.msgError(LANG_MAN.getMsg(LanguageManagerConstants.NOTIFICATIONS, 
                    LanguageManagerConstants.ID_NOTIFICATIONS_MSG_ERROR_UPDATE_ID));
            }

        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void tableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMousePressed
        fillAlumnTFields(getSelectedAlumn());//rellenamos los textFields
    }//GEN-LAST:event_tableMousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainView().setVisible(true);
            }
        });
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFilter;
    private javax.swing.JButton btnQuitFilter;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTextField tFieldDate;
    private javax.swing.JTextField tFieldFilter;
    private javax.swing.JTextField tFieldNMatricula;
    private javax.swing.JTextField tFieldNom;
    private javax.swing.JTextField tFieldNota1;
    private javax.swing.JTextField tFieldNota2;
    private javax.swing.JTextField tFieldNotaE;
    private javax.swing.JTextField tFieldNotaF;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}