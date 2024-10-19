/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package xmlex1lectura.xmlaccdatveronicasanchez;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author veron
 */
public class XMLEx1LecturaXMLAccDatVeronicaSanchez {

    private static final String INDENT_CHAR = " ";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String nomFich;
        try{
            nomFich = args[0];
        }catch(ArrayIndexOutOfBoundsException ex){
            nomFich="books.xml"; 
        }

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setIgnoringComments(true);
        dbf.setIgnoringElementContentWhitespace(true);
        
        try{
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse( new File(nomFich) );
            muestraNodo( doc, 0, System.out );
        }catch( FileNotFoundException | ParserConfigurationException | 
                SAXException ex ){
            System.out.println(ex.getMessage() );
        }
        catch( Exception ex ){
            ex.printStackTrace();
        }
    }
    
    public static void muestraNodo ( Node nodo, int level, PrintStream ps ){
        String index ="";
        short nodeType =nodo.getNodeType();
        
        //indexing
        for (int i = 0; i < level; i++) {
            index= index+INDENT_CHAR;
        }
        
        //El nodo document, (el nivel 0) es especial
        //contiene el prologo y el nodo padre
        if (nodeType==Node.DOCUMENT_NODE) {
            ps.print(auxGetProlog(nodo)); //mostramos el prologo
            nodo= nodo.getFirstChild();//cogemos el primer nivel de la anidacion
            nodeType =nodo.getNodeType();//ajustamos metadatos
        }
            
        if (nodeType==Node.ELEMENT_NODE) {//leemos cada nodo
            String nodeName = nodo.getNodeName();
            //mostramos la etiqueta de cada nodo junto a sus atributos
            ps.println(index+"<"+nodeName+ auxGetNodeAtributesStr(nodo)+">");
            
            //cogemos los elementos de cada nodo
            NodeList nList = nodo.getChildNodes();
            for (int i = 0; i < nList.getLength(); i++) {
                muestraNodo ( nList.item(i), level+1, ps );
            }
            
            ps.println(index+"</"+nodeName+">");
        }else if(nodeType==Node.TEXT_NODE){//lectura de nodos de texto
            /*
            No voy a controlar los espacios en blanco ni los saltos de linea 
            entre nodo padre y nodo hijo (serían nodos de texto) porque eso 
            sería "inventarme" el contenido del XML, los voy a tratar como nodos
            de texto normales
            */
            ps.println(index+nodo.getNodeValue());
        }else {
            ps.println(index+"\t"+"ERROR unreadable node");
        }
        
    }
    
    /**
     * Funcion auxiliar para obtener el prologo de un nodo Documento
     * @param nodo debe ser Documento
     * @return prologo del XML asociado a nodo o "" si no se ha encontrado el 
     * prologo
     */
    public static String auxGetProlog( Node nodo){
        String prolog= "";
        
        String version = ((Document)nodo).getXmlVersion();
        String encoding = ((Document)nodo).getXmlEncoding();
        
        if (version!= null) {
            prolog = prolog + " version=\""+version+"\"";
        }
        if (encoding!= null) {
            prolog = prolog + " encoding=\""+encoding+"\"";
        }
        if (version!= null || encoding!= null) {
            prolog= "<?xml " + prolog + " ?>\n";      
        }
        
        return prolog;
    }
    
    /**
     * funcion auxiliar que devuelve un String con la lista de los atributos de un nodo.
     * @param nodo 
     * @return String con la lista de los atributos de un nodo utilizando el 
     * formato nombre="valor" o "" si no hay atributos
     */
    public static String auxGetNodeAtributesStr( Node nodo){
        String res= "";
        //recorremos la lista de atributos
        NamedNodeMap atributeMap = nodo.getAttributes();
        if (atributeMap!=null) {
            for (int i = 0; i<atributeMap.getLength(); i++) {
                //colocamos en el resultado el nombre/valor con el formato 
                // nombre="valor"
                Node attribute = atributeMap.item(i);
                String key = attribute.getNodeName(); 
                String value = attribute.getNodeValue();
                res= res+ " "+key+"=\""+value+"\"";
            }
        }
        return res;
    }
}
