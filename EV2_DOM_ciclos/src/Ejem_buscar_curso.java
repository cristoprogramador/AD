import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Ejem_buscar_curso {
	public static void main(String[] args) {
		DocumentBuilderFactory factory = null;
		DocumentBuilder builder = null;
		
		try {
			//Cargamos el .xml en el Doc
			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();			
			Document doc = builder.parse("Ciclos.xml");
			doc.normalize();
			//para distinguir los tipos usados usaremos los siguientes prefijos
			//e -> elemento
			//n -> nodo
			//nl -> nodelist
			
			// Puntero al Elemento raiz
			Element raiz = doc.getDocumentElement();
			System.out.println("Elemento raiz: " + raiz.getNodeName());
			
			//información sacada de la cabecera.
			System.out.println("Tipo de documento: " + doc.getDoctype().getName());
			System.out.println("Encoding: " + doc.getXmlEncoding());
			System.out.println("Versión de xml: " + doc.getXmlVersion());
			System.out.println();
			
			//Buscamos elementos por nombre
			//Sacamos una lista de nodos de ciclos
			System.out.println("Sacamos una lista de nodos de ciclos");
			NodeList nlCiclos = doc.getElementsByTagName("titulo");
			//Recorremos la lista de nodos
			for (int i = 0; i < nlCiclos.getLength(); i++) {
				//recuperar primer elemento
				Node nciclo = nlCiclos.item(i);
				//obtener el texto asociado al titulo del
				String ciclo = nciclo.getTextContent();				
				System.out.println("Ciclo: " + ciclo);
				
				Node ncurso = nciclo.getNextSibling().getNextSibling();
				System.out.println("tipo_nodo: " + ncurso.getNodeType());
				String numero = ((Element) ncurso).getAttribute("numero");
				System.out.println("Curso: "+ numero);
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
