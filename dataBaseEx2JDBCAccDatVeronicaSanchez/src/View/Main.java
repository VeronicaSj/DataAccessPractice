package View;

import IO.DataBaseConstants;
import IO.DataBaseController;
import Model.Alumn;
import Model.Licence;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        DataBaseController dbc= new DataBaseController();
        System.out.println("CONNECTING DB");
        System.out.println(dbc.createConnection().toString());

        System.out.println();
        System.out.println("TEST1: selectCampo(0, DataBaseConstants.T_ALUMN_C_NAME_ENRROLMENT_PK)");
        System.out.println(dbc.selectCampo(0, DataBaseConstants.T_ALUMN_C_NAME_ENRROLMENT_PK));

        System.out.println();
        System.out.println("TEST2: selectColumna(DataBaseConstants.T_ALUMN_C_NAME_NAME)");
        for (String string : dbc.selectColumna(DataBaseConstants.T_ALUMN_C_NAME_NAME)) {
            System.out.println(string);
        }

        System.out.println();
        System.out.println("TEST3: selectRowList(1)");
        for (String string : dbc.selectRowList(1)) {
            System.out.println(string);
        }

        System.out.println();
        System.out.println("TEST4: selectRowMap(1)");
        HashMap<String,String>  hMap = (HashMap) dbc.selectRowMap(1);
        System.out.println(hMap.get(DataBaseConstants.T_ALUMN_C_NAME_ENRROLMENT_PK));
        System.out.println(hMap.get(DataBaseConstants.T_ALUMN_C_NAME_NAME));
        System.out.println(hMap.get(DataBaseConstants.T_ALUMN_C_NAME_MARK1EV));
        System.out.println(hMap.get(DataBaseConstants.T_ALUMN_C_NAME_MARK2EV));

        System.out.println();
        System.out.println("TEST5: update(2, hMap)");
        hMap.put(DataBaseConstants.T_ALUMN_C_NAME_MARK2EV, "10");
        dbc.update(2, hMap);
        for (String string : dbc.selectRowList(2)) {
            System.out.println(string);
        }

        System.out.println();
        System.out.println("TEST6: update(2, DataBaseConstants.T_ALUMN_C_NAME_NAME, \"nombre2\")");
        dbc.update(2, DataBaseConstants.T_ALUMN_C_NAME_NAME, "nombre2");
        dbc.update(2, DataBaseConstants.T_ALUMN_C_NAME_MARK2EV, "2");
        for (String string : dbc.selectRowList(2)) {
            System.out.println(string);
        }

        System.out.println();
        System.out.println("TEST7: delete(4)");
        if (dbc.delete(4)==1){
            System.out.println("Row correctly deleted");
        } else {
            System.out.println("nothing deleted");
        }

        System.out.println();
        System.out.println("TEST8: dbc.insertLicencias(Alumn, ArrayList<Licence>);");
        ArrayList<Licence> licenceList = new ArrayList<Licence>();
        licenceList.add(new Licence(4, "b1", new Date(9,9,9), new Date(14,4,24)));
        licenceList.add(new Licence(4, "b2", new Date(8,8,8), new Date(67,5,8)));
        if(dbc.insertLicencias(new Alumn(4, "name4", 12, 13), licenceList)){
            System.out.println("alumn and licences created");
        } else {
            System.out.println("alumn and licences NOT created");
        }

        System.out.println();
        System.out.println("TEST9: dbc.eliminarLicencias(4);");
        if(dbc.eliminarLicencias(4)){
            System.out.println("id 4 licences deleted");
        } else {
            System.out.println("id 4 licences NOT deleted");
        }
    }
}