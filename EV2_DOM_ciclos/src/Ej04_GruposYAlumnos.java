import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
 *(GruposYAlumnos) De cada grupo mostrar nombre y número de alumnos 
 *que tiene (fichero grupos.xml).
 *
 */

public class Ej04_GruposYAlumnos {
	public static void main(String[] args) {
		
		DocumentBuilderFactory factory = null;
		DocumentBuilder builder = null;
		//para distinguir los tipos usados usaremos los siguientes prefijos
		//e -> elemento
		//n -> nodo
		//nl -> nodelist	
		
		try {
			//Cargamos el .xml en el Doc
			factory = DocumentBuilderFactory.newInstance();
			factory.setIgnoringElementContentWhitespace(true);
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse("grupos.xml");
			doc.normalize();

			// Puntero al Elemento raiz
			Element raiz = doc.getDocumentElement();
			System.out.println("Elemento raiz: " + raiz.getNodeName());			
			//información sacada de la cabecera.
			System.out.println("Tipo de documento: " + doc.getDoctype().getName());
			System.out.println("Encoding: " + doc.getXmlEncoding());
			System.out.println("Versión de xml: " + doc.getXmlVersion());
			System.out.println();
			
			NodeList nlGrupo = doc.getElementsByTagName("grupo");
			for (int i = 0; i < nlGrupo.getLength(); i++) {				
				String nombreGrupo = nlGrupo.item(i).getFirstChild().getTextContent();
				System.out.println("Grupo: " + nombreGrupo);
				Element nGrupo = (Element)nlGrupo.item(i);
				
				NodeList nlAlumnos = nGrupo.getElementsByTagName("alumno");
				System.out.println("Numero del Alumnos: "+nlAlumnos.getLength());

				
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
