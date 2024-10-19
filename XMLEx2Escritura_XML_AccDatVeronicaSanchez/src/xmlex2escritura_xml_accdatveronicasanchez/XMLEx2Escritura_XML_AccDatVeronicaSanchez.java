/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package xmlex2escritura_xml_accdatveronicasanchez;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.FileChooserUI;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.xml.sax.SAXException;

/**
 *
 * @author veron
 */
public class XMLEx2Escritura_XML_AccDatVeronicaSanchez {
    static XML_translator translator;
    static XmlManager xmlMan;
    static final ViewElements VIEW_E = new ViewElements(null);
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //ask input file (TO READ TITLES AND TRANLATE)
        File file = VIEW_E.chooseFileLocation(
                        JFileChooser.FILES_ONLY, "Introduce el archivo para leer", "xml");
        
        if (file!=null) {//validate selection
            if (getFileExt(file.getName()).equals(".xml")) {//validate selection
                Document doc =getParamDocXML(file);
                xmlMan = new XmlManager(doc);//fileManager

                //reading titles
                System.out.println("lEYENDO LISTA DE LIBROS: ");
                ArrayList<String> bookList = xmlMan.listBooks();
                for (String book : bookList) {
                    System.out.println(book);
                }
                
                //ask TRANSLATION output directori
                file=VIEW_E.chooseFileLocation(
                            JFileChooser.DIRECTORIES_ONLY, 
                        "Introduce la ruta del resultado de la traduccion", 
                        "xml");

                if (file!=null) {//validate selection
                    //ask output filename
                    String name = JOptionPane.showInputDialog(null, "");
                    if (name !=null) {//validate filename
                        if (name.length()>0 && getFileExt(name).equals(".xml")) {
                            //create translator manager
                            translator = new XML_translator(doc, 
                                    new File(file.getAbsolutePath()+"\\"+name));
                            //translate
                            System.out.println("TRADUCIENDO");
                            translator.translateDocument();
                        
                        }else{ //error control
                            VIEW_E.msgError("Nombre introducido no valido");
                        }
                    }
                }
                
            }else{ //error control
                VIEW_E.msgError("El archivo seleccionado debe ser XML"); 
            }
        }
    }
    
    /**
     * return a file extension from a filename
     * @param fileName 
     * @return file extension
     */
    private static String getFileExt(String fileName){
        String res="";
        int i = fileName.lastIndexOf('.');
        if (i>0){ res= fileName.substring(i);}
        return res;
    }
    
    /**
     * return the Document object of a XML file
     * @param file
     * @return corresponding Document object or null if 
     */
    private static Document getParamDocXML(File file){
        Document res = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setIgnoringComments(true);
        dbf.setIgnoringElementContentWhitespace(true);
        DocumentBuilder db;
        try {
            db = dbf.newDocumentBuilder();
            res = db.parse( file);
        } catch (ParserConfigurationException ex) {
            res=null;
        } catch (SAXException ex) {
            res=null;
        } catch (IOException ex) {
            res=null;
        }
        
        return res;
    }
    
    
}
