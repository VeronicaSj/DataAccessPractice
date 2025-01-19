/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xmlex2escritura_xml_accdatveronicasanchez;

import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author veron
 */
public class XmlManager {
    private static Document doc;
    
    public XmlManager(Document doc) {
        this.doc=doc;
    }
    
    /**
     * return a book title list
     * @return book title list
     */
    public static ArrayList<String> listBooks (){
        Node node = doc.getFirstChild();
        return recursiveFindBooks(node);
    }
    
    /**
     * Recursive function. return node child book title list.
     * @param node where to look for titles
     * @return node child book title list.
     */
    public static ArrayList<String> recursiveFindBooks(Node node){
        ArrayList<String> list = new ArrayList<>();
        NodeList nList = node.getChildNodes();
        
        for (int i = 0; i < nList.getLength(); i++) {
            Node itm = nList.item(i);
            list.addAll(recursiveFindBooks(itm));
            if (itm.getNodeType()==Node.TEXT_NODE &&
                    node.getNodeName().toUpperCase().equals("TITLE")) {
                list.add(itm.getNodeValue());
            }
        }
        
        return list;
    }
}
