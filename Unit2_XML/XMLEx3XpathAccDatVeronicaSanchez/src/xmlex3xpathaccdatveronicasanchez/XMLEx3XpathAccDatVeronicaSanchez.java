/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package xmlex3xpathaccdatveronicasanchez;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author veron
 */
public class XMLEx3XpathAccDatVeronicaSanchez {

    private static final File xml = new File("baile.xml");
    private static Document doc;
    private static XPath xpath;
    private static XPathExpression expr;
    
    private static Node nodeRes;
    private static NodeList nodeListRes;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true); // never forget this!
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            try {
                doc = builder.parse(xml);
                
                System.out.println("Nombre del fichero: "+xml.getName());
                
                //Create XPath
                XPathFactory xpathfactory = XPathFactory.newInstance();
                xpath = xpathfactory.newXPath();
                
                //show xPath expresions
                
                System.out.println();
                System.out.println("1) Obtener todos los nombres de bailes");
                nodeListRes = (NodeList) launchQuery(
                        "/bailes/baile/nombre/text()", 
                        XPathConstants.NODESET);
                for (int i = 0; i < nodeListRes.getLength(); i++) {
                    System.out.println(nodeListRes.item(i).getNodeValue());
                }
                
                System.out.println();
                System.out.println("2) Obtener el nombre de baile con id=2");
                nodeRes = (Node) launchQuery("//baile[@id=2]/nombre/text()", 
                        XPathConstants.NODE);
                System.out.println(nodeRes.getNodeValue());
                
                System.out.println();
                System.out.println("3) Obtener precio de la clase de pasodoble");
                nodeRes = (Node) launchQuery(
                        "//baile[nombre/text()=\"Pasodoble\"]/precio/text()",
                        XPathConstants.NODE);
                System.out.println(nodeRes.getNodeValue());
                
                System.out.println();
                System.out.println("4) Obtener todos los cursos de baile que "
                        + "se paguen trimestralmente");
                nodeListRes = (NodeList) launchQuery(
                        "//baile[precio/@cuota=\"trimestral\"]/nombre/text()",
                        XPathConstants.NODESET);
                for (int i = 0; i < nodeListRes.getLength(); i++) {
                    System.out.println(nodeListRes.item(i).getNodeValue());
                }
                
            } catch (SAXException ex) {
                System.out.println("ERROR. " + ex.getMessage());
            } catch (IOException ex) {
                System.out.println("ERROR. " + ex.getMessage());
            }
        } catch (ParserConfigurationException ex) {
            System.out.println("ERROR. " + ex.getMessage());
        }
    }
    
    private static Object launchQuery(String query, QName type) throws FileNotFoundException{
        Object res = null;
        
        try {
            expr = xpath.compile(query);
            res = expr.evaluate(doc, type);
        } catch (XPathExpressionException ex) {
            System.out.println("ERROR. " + ex.getMessage());
        }
        
        return res;
    }
}
