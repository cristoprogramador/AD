package dom;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Ejercicio1a {

	public static void main(String[] args) {		
		DocumentBuilderFactory factory = null;
		DocumentBuilder builder = null;

		try {
			
			factory = DocumentBuilderFactory.newInstance();
			factory.setIgnoringElementContentWhitespace(true);
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse("cdinfo.xml");
			doc.normalize();
			
			NodeList nlMinutos = doc.getElementsByTagName("minutos");	
			for (int i = 0; i < nlMinutos.getLength(); i++) {				
				Element eMinutos = (Element)nlMinutos.item(i);
				int minutos = Integer.parseInt(eMinutos.getTextContent());
				if (minutos >= 3){
					System.out.print(eMinutos.getParentNode().getParentNode().getFirstChild().getTextContent());
					System.out.println( " - " + minutos +" minutos");
				}				
			}
		
		} catch (ParserConfigurationException e) {
			System.out.println("Problema al crear el DocumentBuilder");
			e.printStackTrace();
		} catch (SAXException e) {
			System.out.println("Error al parsear");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error de E/S");
			e.printStackTrace();
		}
	}

}
