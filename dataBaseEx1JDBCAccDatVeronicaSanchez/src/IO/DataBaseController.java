public class DbManager {
    //devuelve el campo correspondiente a la columna de nombre nomColumna del registro indicado en numRegistro.
    public String selectCampo( int numRegistro, string nomColumna){

    }
    
    
2) Método List<String> selectColumna( string nomColumna ) Devuelve una lista con TODOS los valores del campo buscado. (SELECT columna FROM fichero).

3) Método List<String> selectRowList( in numRegistro ) Devuelve una lista con los datos del registro de la posición numRegistro. (SELECT CAMPOS FROM fichero WHERE ... )

4) Método Map selectRowMap( in numRegistro ) Igual resultado que el anterior pero en una clase HashMap.

5.1) Método update( int row, Map ) Modifica en el fichero todos los campos para el registro indicado en row que se reciben en un Map<string campo,string valor>

5.2) Método update( int row, string campo, string valor ) Modifica SOLO el valor del campo "campo" para el registro indicado en row.

6) Método delete( int row ) limpia los datos del registro indicado.
}
