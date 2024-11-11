package IO;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import Model.Alumn;
import Model.Licence;

import java.util.HashMap;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DataBaseController {
    private Connection con;

    /**********************************************************
     * ****EXERCISE 2 NEW FUNCTIONS
     * ********************************************************
     * */

     /**
      * insert an alumn and his licences all at once
      * @param alu
      * @param licencias
      * @return true if something inserted, false if not
      */
     public boolean insertLicencias( Alumn alu, ArrayList<Licence> licencias ){
        boolean res=false; //default value-> nothing changed
        if (con!=null && alu!=null && licencias!=null) { //check connection and correct parameters
            String qInserAlu= "INSERT INTO "+DataBaseConstants.TABLE_ALUMN+" VALUES (?,?,?,?);";
            String qInserLic= "INSERT INTO "+DataBaseConstants.TABLE_LICENCE+" VALUES (?,?,?,?);";
            try{
                
                //alumn insertion
                PreparedStatement prepStat = con.prepareStatement(qInserAlu, 
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                prepStat.setInt(1, alu.getEnrollment());
                prepStat.setString(2, alu.getName());
                prepStat.setFloat(3, alu.getMark1Ev());
                prepStat.setFloat(4, alu.getMark2Ev());
                prepStat.executeUpdate();
                
                //each alumn licence insertion
                prepStat = con.prepareStatement(qInserLic, 
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                for (Licence licence : licencias) {
                    prepStat.setInt(1, licence.getId());
                    prepStat.setString(2, licence.getlType());
                    prepStat.setDate(3, licence.getExpedition());
                    prepStat.setDate(4, licence.getExpiration());
                    prepStat.executeUpdate();
                }

                res=true;
                con.commit();
            }catch(SQLException ex){
                res=false;
                System.out.println(ex.getMessage());
                try { con.rollback(); } catch (SQLException e) {}
            }
        }
        return res;
     }

     /**
      * Delete every licence from an alumn enrolmentId
      * @param enrolmentId 
      * @return true if anything deleted.
      */
     public boolean eliminarLicencias( int enrolmentId ){
        boolean res=false; //default value-> nothing changed
        if (con!=null ) { //check connection and correct parameters
            String query= "DELETE FROM "+DataBaseConstants.TABLE_LICENCE+" " +
                "WHERE "+DataBaseConstants.T_LICENCIAS_C_NAME_ID+"=?" ;
            try{
                //prepared statement because there are parameters
                PreparedStatement prepStat = con.prepareStatement(query);
                prepStat.setInt(1, enrolmentId);
                if(prepStat.executeUpdate()>0){
                    res=true;
                }else{
                    res=false;
                }
                con.commit();
            }catch(SQLException ex){
                res=false;
                try { con.rollback(); } catch (SQLException e) {}
            }
        }
        return res;
     }










    /**********************************************************
     * ****EXERCISE 1 UPDATED
     * ********************************************************
     * */

    /**
     * Creates a database conection
     * @return the database connection or null if there's an exception
     */
    public Connection createConnection(){
        try{
            con= DriverManager.getConnection(
                DataBaseConstants.DATASOURCE, 
                DataBaseConstants.USER, 
                DataBaseConstants.PW);
            con.setAutoCommit( false );
        }catch(SQLException ex){
            con = null;
        }
        return con;
    }

    /**
     * update a row thougth a map
     * @param row row id to update
     * @param map column-value map to update
     * @return the number of row afected or -1 if errors
     */
    public int update( int row, Map<String,String> map){
        int res= 0;//0 is the default value-> nothing changed
        if (con!=null && map.size()==4) { //check connection and correct map size
            String query= "UPDATE "+DataBaseConstants.TABLE_ALUMN+" " +
                "SET "+ DataBaseConstants.T_ALUMN_C_NAME_NAME +"=? , "+
                DataBaseConstants.T_ALUMN_C_NAME_MARK1EV+"=? , "+
                DataBaseConstants.T_ALUMN_C_NAME_MARK2EV +"=?"+
                "WHERE "+DataBaseConstants.T_ALUMN_C_NAME_ENRROLMENT_PK+"=?" ;
            try{
                //prepared statement because there are parameters
                PreparedStatement prepStat = con.prepareStatement(query);
                try {
                    prepStat.setString(1, map.get(DataBaseConstants.T_ALUMN_C_NAME_NAME));
                    prepStat.setFloat(2, 
                    Float.parseFloat(map.get(DataBaseConstants.T_ALUMN_C_NAME_MARK1EV)));
                    prepStat.setFloat(3, 
                    Float.parseFloat(map.get(DataBaseConstants.T_ALUMN_C_NAME_MARK2EV)));
                    prepStat.setInt(4, row);
                } catch ( NumberFormatException ex) {
                    res=-1;
                    try { con.rollback(); } catch (SQLException e) {}
                } catch ( IllegalArgumentException ex) {
                    res=-1;
                    try { con.rollback(); } catch (SQLException e) {}
                } 
                res = prepStat.executeUpdate();
                con.commit();
            }catch(SQLException ex){
                res=-1;
                try { con.rollback(); } catch (SQLException e) {}
            }
        }
        return res;
    } 
    
    /**
     * update a row value 
     * @param row row id to update
     * @param campo column to update
     * @param valor new value
     * @return the number of row afected or -1 if errors
     */
    public int update( int row, String campo, String valor ){
        int res= 0; //0 is the default value-> nothing changed
        if (con!=null) { //check connection
            String query= "UPDATE "+DataBaseConstants.TABLE_ALUMN+" " +
                "SET "+ campo +"=?  "+
                "WHERE "+DataBaseConstants.T_ALUMN_C_NAME_ENRROLMENT_PK+"=?" ;
            try{
                //prepared statement because there are parameters
                PreparedStatement prepStat = con.prepareStatement(query);
                try {
                    if(campo.equals(DataBaseConstants.T_ALUMN_C_NAME_NAME)){
                        prepStat.setString(1, valor);
                    } else {
                        prepStat.setFloat(1, Float.parseFloat(valor));
                    }
                    prepStat.setInt(2, row);
                } catch ( NumberFormatException ex) {
                    res=-1;
                    try { con.rollback(); } catch (SQLException e) {}
                } catch ( IllegalArgumentException ex) {
                    res=-1;
                    try { con.rollback(); } catch (SQLException e) {}
                } 
                res = prepStat.executeUpdate();
                con.commit();
            }catch(SQLException ex){
                res=-1;
                try { con.rollback(); } catch (SQLException e) {}
            }
        }
        return res;
    } 

    /**
     * delete a row
     * @param row row id to delete
     * @return the number of row afected or -1 if errors
     */
    public int delete( int row ){
        int res= 0;//0 is the default value-> nothing changed
        if (con!=null) {//check connection
            String query= "DELETE FROM "+DataBaseConstants.TABLE_ALUMN+" " +
                "WHERE "+DataBaseConstants.T_ALUMN_C_NAME_ENRROLMENT_PK+"=?" ;
            try{
                //prepared statement because there are parameters
                PreparedStatement prepStat = con.prepareStatement(query);
                prepStat.setInt(1, row);
                res = prepStat.executeUpdate();
                con.commit();
            }catch(SQLException ex){
                res=-1;
                try { con.rollback(); } catch (SQLException e) {}
            }
        }
        return res;
    } 









    /**********************************************************
     * ****EXERCISE 1 COPIED
     * ********************************************************
     * */

    /**
     * return the corresponding column value especified as nomColumna from 
     * the row especified as numRegistro
     * @param numRegistro
     * @param nomColumna
     * @return row column value or null if errors
     */
    public String selectCampo( int numRegistro, String nomColumna){
        String res = null; //null is the error value
        /*this is a prepared statement to avoid sql injections
         the column name is not placeholded becouse in a real situation the 
        column would be selected with a combobox and it would be imposible to 
        insert anything but a valid value*/
        String query= "SELECT " +nomColumna+" FROM ALUMN WHERE enrollment=?"; 
        if (con!=null) { //check connection
            try{
                PreparedStatement prepStat = con.prepareStatement(query);
                prepStat.setInt(1, numRegistro);
                ResultSet resSet = prepStat.executeQuery();
                if (resSet.next() && resSet.isLast()) {
                    res=resSet.getString(nomColumna);
                }else{
                    res=null;
                }
            }catch(SQLException ex){
                res=null;
            }
        }
        return res;
    }
    
    /**
     * return a List containing every value from the column 
     * especified as nomColumna
     * @param nomColumna
     * @return every value from the column especified as 
     * nomColumna or null if there's an exception
     */
    public List<String> selectColumna( String nomColumna ){
        ArrayList<String> res = null;//null is the error value
        /*this is a statement because if the column name is selected in a 
        combobox it would be imposible to make a sqlinjection and there 
        aren´t any other parameter*/
        String query= "SELECT " +nomColumna+ " FROM ALUMN";
        if (con!=null) {//check connection
            try{
                Statement stat = con.createStatement();
                ResultSet resSet = stat.executeQuery(query);

                //set the result
                res = new ArrayList<String>();
                while (resSet.next()) {
                    res.add(resSet.getString(nomColumna));
                }

            } catch (SQLException ex){
                res=null;
            }
        }
        return res;
    } 

    /**
     * Return a list with a row value especified with numRegistro parameter
     * @param numRegistro row id
     * @return list with the row value or null if errors
     */
    public List<String> selectRowList( int numRegistro ){
        ArrayList<String> res = null;//null is the error value
        String query= "SELECT * FROM ALUMN WHERE enrollment=?"; 
        if (con!=null) {//check connection
            try{
                //prepared statement because there are parameters
                PreparedStatement prepStat = con.prepareStatement(query);
                prepStat.setInt(1, numRegistro);
                ResultSet resSet = prepStat.executeQuery();

                //set the result 
                res= new ArrayList<String> ();
                //if the first value is the last theres only one row returned
                if (resSet.next() && resSet.isLast()) {
                    res.add(resSet.getString(DataBaseConstants.T_ALUMN_C_NAME_ENRROLMENT_PK));
                    res.add(resSet.getString(DataBaseConstants.T_ALUMN_C_NAME_NAME));
                    res.add(resSet.getString(DataBaseConstants.T_ALUMN_C_NAME_MARK1EV));
                    res.add(resSet.getString(DataBaseConstants.T_ALUMN_C_NAME_MARK2EV));
                }else{
                    res=null;
                }

            }catch(SQLException ex){
                res=null;
            }
        }
        return res;
    } 

    /**
     * Return a map with a row value especified with numRegistro parameter
     * @param numRegistro row id
     * @return map with the row value or null if errors
     */
    public Map<String,String>  selectRowMap ( int numRegistro ){
        HashMap<String,String> res = null;//null is the error value
        String query= "SELECT * FROM ALUMN WHERE enrollment=?"; 
        if (con!=null) {//check connection
            try{
                //prepared statement because there are parameters
                PreparedStatement prepStat = con.prepareStatement(query);
                prepStat.setInt(1, numRegistro);
                ResultSet resSet = prepStat.executeQuery();

                //set the result
                res= new HashMap<String,String> ();
                //if the fist value is the last theres only one row returned
                if (resSet.next() && resSet.isLast()) {
                    res.put(DataBaseConstants.T_ALUMN_C_NAME_ENRROLMENT_PK,
                        resSet.getString(DataBaseConstants.T_ALUMN_C_NAME_ENRROLMENT_PK));
                    res.put(DataBaseConstants.T_ALUMN_C_NAME_NAME,
                        resSet.getString(DataBaseConstants.T_ALUMN_C_NAME_NAME));
                    res.put(DataBaseConstants.T_ALUMN_C_NAME_MARK1EV,
                            resSet.getString(DataBaseConstants.T_ALUMN_C_NAME_MARK1EV));
                    res.put(DataBaseConstants.T_ALUMN_C_NAME_MARK2EV,
                        resSet.getString(DataBaseConstants.T_ALUMN_C_NAME_MARK2EV));
                }else{
                    res=null;
                }

            }catch(SQLException ex){
                res=null;
            }
        }
        return res;
    } 

}
