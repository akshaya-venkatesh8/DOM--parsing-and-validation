
package com.github.akshayavenkatesh8;

import static com.sun.org.apache.xerces.internal.jaxp.JAXPConstants.JAXP_SCHEMA_LANGUAGE;
import static com.sun.org.apache.xerces.internal.jaxp.JAXPConstants.JAXP_SCHEMA_SOURCE;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author akshaya
 */
public class FileParse1 {

    /**
     * @param args the command line arguments
     * @throws org.xml.sax.SAXException
     * @throws javax.xml.parsers.ParserConfigurationException
     */
    public static void main(String[] args) throws Exception {

        try{
           
    File input= new File("address.xml");
   new FileParse1().fileParse(input);
        }catch(Exception e){
         System.out.println("ERROR"+e);
    }
}
public void fileParse(File f) throws SAXException, IOException, ParserConfigurationException
    {
    Address add=new Address();
    DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
    factory.setValidating(true);
    factory.setNamespaceAware(true);
    factory.setAttribute(JAXP_SCHEMA_LANGUAGE, "http://www.w3.org/2001/XMLSchema");
    factory.setAttribute(JAXP_SCHEMA_SOURCE,new File("address.xsd"));
    InvalidXMLErrorHandler ieh = new InvalidXMLErrorHandler();
    DocumentBuilder dbld= factory.newDocumentBuilder();
    dbld.setErrorHandler(ieh);
    Document doc = dbld.parse(f);
    doc.getDocumentElement().normalize();
    if(ieh.check == null){
           System.out.println("The document is valid!");
           Element rootE= doc.getDocumentElement();
           Node name= rootE.getFirstChild();
           name=getFirstElementSibling(name); 
           Node streetNumber=getFirstElementSibling(name);
           Node streetName=getFirstElementSibling(streetNumber);
           Node secondaryAddress=getFirstElementSibling(streetName);
           Node city=getFirstElementSibling(secondaryAddress);
           Node stateCode=getFirstElementSibling(city);
           Node zipCode=getFirstElementSibling(stateCode);
           add.name=name.getTextContent();
           add.streetNumber=streetNumber.getTextContent();
           add.streetName=streetName.getTextContent();
           add.secondaryAddress=secondaryAddress.getTextContent();
           add.city=city.getTextContent();
           add.stateCode=stateCode.getTextContent();
           add.zipCode=zipCode.getTextContent();
           System.out.println(add);
           }
    }
    public  Node getFirstElementSibling(Node node)
    {
        Node nextNode=null;
        for(nextNode=node.getNextSibling();nextNode!=null;nextNode=nextNode.getNextSibling())
        {
            if(nextNode.getNodeType() == Node.ELEMENT_NODE)
                return  nextNode;
        }
    return null;
    }
}
  

