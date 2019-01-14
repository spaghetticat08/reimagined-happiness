package src;

import java.io.File;
import java.io.FileOutputStream;

import java.io.IOException;


import src.Stichting;

import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class StichtingGegevens {
	
	/* Produces an XML to store the name of Stichting and the balance.
	 * When the program is started, the XML file is being read and the balance is put in the orderscreen
	 * when adding new orders, the balance will be updated and when closing the application the new balance
	 * is overwritten in the xml file
	 */
	public StichtingGegevens() {
	}
	
	
	public Stichting readXML(String xml) throws ParserConfigurationException, SAXException, IOException {
		try {
			File xmlFile = new File(xml);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("StichtingNaam");

        Node nNode = nList.item(0);
        Element elemNaam = (Element) nNode;
        System.out.println(elemNaam.getTextContent());
        String Naam = elemNaam.getTextContent();
        
        NodeList mList = doc.getElementsByTagName("Balans");
        Node mNode = mList.item(0);
        Element elemBalans = (Element) mNode;
        System.out.println(elemBalans.getTextContent());
        Double Balans = Double.parseDouble(elemBalans.getTextContent());
       
        Stichting newStichting = new Stichting(Naam, Balans);
        return newStichting;
		} catch (Exception e) {
        e.printStackTrace();
     }
		return null;
	}
	
	public boolean writeXML(String xml, String stichtingNaam, String balance)  {
		try {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.newDocument();
		
		File xmlLocation = new File(xml);
		xmlLocation.delete();
		
		 // Define rootelement
        Element rootElement = doc.createElement("gegevens");
        doc.appendChild(rootElement);

        // Define stichtinggegevens element
        Element superelem = doc.createElement("stichtingGegevens");
        rootElement.appendChild(superelem);

        Element naamelem = doc.createElement("StichtingNaam");

        naamelem.appendChild(doc.createTextNode(stichtingNaam));
        superelem.appendChild(naamelem);

        Element balanselem = doc.createElement("Balans");
        balanselem.appendChild(doc.createTextNode(balance));
        superelem.appendChild(balanselem);
		
		//write to xml file
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer trans = tf.newTransformer();
		
		trans.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		trans.setOutputProperty(OutputKeys.INDENT, "yes");
		trans.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		
		DOMSource dom = new DOMSource(doc);
		StreamResult result = new StreamResult(xmlLocation);

		trans.transform(dom, result);
		
		return true;
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		   } catch (TransformerException tfe) {
			tfe.printStackTrace();
		   }
		return false;
	}
	
	
	
	
	
}
	
	/*public Stichting readXML(String xml) throws IOException, ParserConfigurationException, SAXException{
		
		File xmlLocation = new File(xml);
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(xmlLocation);
		
		doc.getDocumentElement().normalize();
		
		System.out.println(doc.getDocumentElement().getNodeName());
		
		NodeList nList = doc.getElementsByTagName("roles");
		
		if (nList.getLength()>0) {
			Element elem = (Element) nList.item(0);
			Node naam = (Node) elem.getChildNodes().item(0);
			System.out.println(naam.getNodeValue());
		}
		/*
		Node nNode = nList.item(0);
		if(nNode.getNodeType()== Node.ELEMENT_NODE) {
			Element elem = (Element) nNode;
			
			Node stichtingNaam = (Node) elem.getElementsByTagName("StichtingNaam");
			
			Node balans = (Node) elem.getElementsByTagName("Balans");
			
		*/	
			
		//Stichting changedStichting = new Stichting(stichtingNaam, Double.parseDouble(balans.getNodeValue()));
		

	
	
	/*public Stichting readXML(String xml) {
        String stichtingNaam = "Yoga B.V.";
        String balance = "25.10";
        ArrayList<String> rolev;
        rolev = new ArrayList<String>();
        Document dom;
        // Make an  instance of the DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            // use the factory to take an instance of the document builder
            DocumentBuilder db = dbf.newDocumentBuilder();
            // parse using the builder to get the DOM mapping of the    
            // XML file
            dom = db.parse(xml);

            Element doc = dom.getDocumentElement();
            

            stichtingNaam = getTextValue(stichtingNaam, doc, "stichtingNaam");
            if (stichtingNaam != null) {
                if (!stichtingNaam.isEmpty())
                    rolev.add(stichtingNaam);
            }
            balance = getTextValue(balance, doc, "balance");
            if (balance != null) {
                if (!balance.isEmpty())
                    rolev.add(balance);
            }
            
           Stichting newStichting = new Stichting(stichtingNaam, Double.parseDouble(balance));
           
           return newStichting;

        } catch (ParserConfigurationException pce) {
            System.out.println(pce.getMessage());
        } catch (SAXException se) {
            System.out.println(se.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
		return null;

       
    }*/
	/*
	private String getTextValue(String def, Element doc, String tag) {
	    String value = def;
	    NodeList nl;
	    nl = doc.getElementsByTagName(tag);
	    if (nl.getLength() > 0 && nl.item(0).hasChildNodes()) {
	        value = nl.item(0).getFirstChild().getNodeValue();
	    }
	    return value;
	}*/
	
	/*

	public boolean saveToXML(String xml, String stichtingNaam, String balance) {
	    Document dom;
	    Element e = null;

	    // instance of a DocumentBuilderFactory
	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    try {
	        // use factory to get an instance of document builder
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        // create instance of DOM
	        dom = db.newDocument();

	        // create the root element
	        Element rootEle = dom.createElement("roles");

	        // create data elements and place them under root
	        e = dom.createElement("StichtingNaam");
	        e.appendChild(dom.createTextNode(stichtingNaam));
	        rootEle.appendChild(e);

	        e = dom.createElement("Balans");
	        e.appendChild(dom.createTextNode(balance));
	        rootEle.appendChild(e);

	        dom.appendChild(rootEle);

	        try {
	            Transformer tr = TransformerFactory.newInstance().newTransformer();
	            tr.setOutputProperty(OutputKeys.INDENT, "yes");
	            tr.setOutputProperty(OutputKeys.METHOD, "xml");
	            tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	            //tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "roles.dtd");
	            tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

	            // send DOM to file
	            tr.transform(new DOMSource(dom), 
	                                 new StreamResult(new FileOutputStream(xml)));

	        } catch (TransformerException te) {
	            System.out.println(te.getMessage());
	            return false;
	        } catch (IOException ioe) {
	            System.out.println(ioe.getMessage());
	            return false;
	        }
	    } catch (ParserConfigurationException pce) {
	        System.out.println("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
	        return false;
	    }
		return true;
	}
}
	


*/