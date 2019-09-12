import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
 *(PlazasLibres) De cada grupo mostrar nombre, c�digo y n� de plazas
 *libres que tiene. (fichero grupos.xml).
 * @author alumno
 *
 */

public class Ej08_PlazasLibres {
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
			//informaci�n sacada de la cabecera.
			System.out.println("Tipo de documento: " + doc.getDoctype().getName());
			System.out.println("Encoding: " + doc.getXmlEncoding());
			System.out.println("Versi�n de xml: " + doc.getXmlVersion());
			System.out.println();
			
			NodeList nlGrupo = doc.getElementsByTagName("grupo");
			for (int i = 0; i < nlGrupo.getLength(); i++) {				
				String nombreGrupo = nlGrupo.item(i).getFirstChild().getTextContent();
				System.out.println( nombreGrupo );
				
				Element eGrupo = (Element)nlGrupo.item(i);
				//Sacamos el atributo maximos alumnos
				int alumnos = Integer.parseInt(eGrupo.getAttribute("maxalumnos"));
				//Sacamos la lista de alumnos del grupo
				NodeList nlAlumnos = eGrupo.getElementsByTagName("alumno");
				System.out.println("Plazas libres: " + (alumnos - nlAlumnos.getLength()));			
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
