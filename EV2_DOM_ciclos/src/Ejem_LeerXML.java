import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class Ejem_LeerXML {

	public static void main(String[] args) {
		DocumentBuilderFactory factory = null;
		DocumentBuilder builder = null;
		
		try {
			//Cargamos el .xml en el Doc
			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
			
			Document doc = builder.parse("Ciclos.xml");
			doc.normalize(); //quita las lineas vacias
			
			// Puntero al Elemento raiz
			Element raiz = doc.getDocumentElement();
			System.out.println("Elemento raiz: " + raiz.getNodeName());
			
			//informaci�n sacada de la cabecera.
			System.out.println("Tipo de documento: " + doc.getDoctype().getName());
			System.out.println("Encoding: " + doc.getXmlEncoding());
			System.out.println("Versi�n de xml: " + doc.getXmlVersion());
			
			
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
