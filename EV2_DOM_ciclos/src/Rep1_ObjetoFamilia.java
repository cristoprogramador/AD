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
 * (Ciclos.xml) Crea un objeto familia, que tenga 
 * el nombre de la familia y cuantos ciclos tiene cada familia.
 * @author elsal
 *
 */
public class Rep1_ObjetoFamilia {

	public static void main(String[] args) {
		Scanner tec = new Scanner(System.in);
		System.out.println("Introduzca nombre del grupo: ");
		String nombre = tec.nextLine();
		
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
			Document doc = builder.parse("Ciclos.xml");
			doc.normalize();
			
			// Puntero al Elemento raiz
			Element raiz = doc.getDocumentElement();
			System.out.println("Elemento raiz: " + raiz.getNodeName());			
			//información sacada de la cabecera.
			System.out.println("Tipo de documento: " + doc.getDoctype().getName());
			System.out.println("Encoding: " + doc.getXmlEncoding());
			System.out.println("Versión de xml: " + doc.getXmlVersion());
			System.out.println();
			
			//Buscamos elementos ciclo y los guardamos en una lista de nodos
			NodeList nlFamilias = doc.getElementsByTagName("familia");		
			for (int i = 0; i < nlFamilias.getLength(); i++) {
				
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
