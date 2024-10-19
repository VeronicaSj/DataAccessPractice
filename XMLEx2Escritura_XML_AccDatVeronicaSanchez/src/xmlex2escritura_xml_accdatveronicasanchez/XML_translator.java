/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xmlex2escritura_xml_accdatveronicasanchez;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
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
public class XML_translator {
    private Document inputDoc;
    private Document outputDoc;
    
    private File outputFile;
    
    private static HashMap<String, String> dict = 
            new HashMap<String, String>();
    
    public XML_translator(Document inputDoc, File outputFile) {
        this.inputDoc=inputDoc;
        this.outputFile = outputFile;
        
        
        try {
            outputDoc=DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .newDocument();
            
            outputDoc.setXmlVersion("1.0");
            outputDoc.setXmlStandalone(true);
        } catch (ParserConfigurationException ex) {
            outputDoc=null;
        }
            
        //LOAD DICTIONARY
        dict.put("CATALOG", "Catalogo");
        dict.put("BOOK" ,"Libro");
        dict.put("TITLE" ,"Titulo");
        dict.put("GENRE","Genero");
        dict.put("PRICE","Precio");
        dict.put("PUBLIC_DATE","Fecha_de_publicación");
        dict.put("DESCRIPTION","Descripción");
    }
    
    /**
     * translate some nodes from a Document into a file 
     * @return true if correctly translated
     */
    public boolean translateDocument (){
        boolean res=false;
        if (outputDoc!=null) {
            try {
                //cogemos el primer nivel de la anidacion
                Node node= inputDoc.getFirstChild();
                outputDoc.appendChild(auxRecursiveTranslateNode(node));

                Transformer transf = TransformerFactory.newInstance().newTransformer();
                transf.transform(new DOMSource(outputDoc), 
                    new StreamResult(outputFile));
                 res=true;
            } catch (TransformerConfigurationException ex) { res=false;
            } catch (TransformerException ex) { res=false; }
        }else{
            res=false;
        }
        
        return res;
    }
    
    /**
     * Recursive function. return a node copy translating some elememt names if 
     * posible theres a translation
     * @param node to tranlate
     * @return translated node with tranlated child nodes
     */
    private Node auxRecursiveTranslateNode (Node node){
        Node res = auxCreateTranslatedCopyNode( node);

        //translate each element name
        NodeList nList = node.getChildNodes();
        for (int i = 0; i < nList.getLength(); i++) {
            //save into the parent
            res.appendChild(auxRecursiveTranslateNode(nList.item(i)));
        }
        
        return res;
    }
    
    /**
     * return a node copy translating some elememt names if 
     * posible theres a translation
     * @param node to copy
     * @return copy
     */
    private Node auxCreateTranslatedCopyNode( Node node){
        short nodeType =node.getNodeType();
        Node res = null;
        
        //create each node as the corresponding node type
        switch(nodeType){
            case Node.ELEMENT_NODE:
                res = outputDoc.createElement(auxTranslateString(node.getNodeName()));

                //copy the atribute nodes into the element node
                res= auxCopyNodeAtributes(node, res);
                break;
            case Node.TEXT_NODE:
                res=outputDoc.createTextNode(node.getNodeValue());
                break;
            case Node.CDATA_SECTION_NODE:
                res=outputDoc.createCDATASection(node.getNodeValue());
                break;
            case Node.COMMENT_NODE:
                res=outputDoc.createComment(node.getNodeValue());
                break;
            case Node.ENTITY_REFERENCE_NODE:
                res=outputDoc.createEntityReference(node.getNodeName());
                break;
            case Node.PROCESSING_INSTRUCTION_NODE:
                ProcessingInstruction prInst= (ProcessingInstruction) node;
                res=outputDoc.createProcessingInstruction(
                        prInst.getTarget(), prInst.getData());
                break;
        }
        return res;
    }
    
    /**
     * return a translation of an string or the same string if 
     * there are no translation
     * @param str text to translate
     * @return translated text
     */
    private static String auxTranslateString (String str){
        String res="";
        str = str.toUpperCase();
        res=dict.getOrDefault(str, str);
        return res;
    }
    
    /**
     * return a copi the atributes of an input node into an 
     * output node
     * @param input node we want to copy
     * @param output copied node
     * @return copied node
     */
    private Node auxCopyNodeAtributes( Node input, Node output){
        //read atributes
        NamedNodeMap inputAttMap = input.getAttributes();
        if (inputAttMap!=null) {//if read ok
            //copy each atribute
            for (int i = 0; i<inputAttMap.getLength(); i++) {
                Node inputAttribute = inputAttMap.item(i);
                ((Element)output).setAttribute(inputAttribute.getNodeName(), 
                        inputAttribute.getNodeValue());
            }
        }
        return output;
    }
}
