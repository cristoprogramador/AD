import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
 * (Familias) De cada familia mostrar nombre y nº de ciclos 
 * de que consta (fichero ciclos.xml)
 * @author alumno
 *
 */
public class Ej06_FamiliaNombreYNumCiclos {

	public static void main(String[] args) {
		
		DocumentBuilderFactory factory = null;
		DocumentBuilder builder = null;
		
		try {
			//Cargamos el .xml en el Doc
			factory = DocumentBuilderFactory.newInstance();
			factory.setIgnoringElementContentWhitespace(true);
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
			
			NodeList nlFamilias = doc.getElementsByTagName("familia");			
			//recorremos la lista de nodos			
			for(int i=0 ; i < nlFamilias.getLength() ; i++){
				//sacamos cada uno de los nodos
				Node nFamilia = nlFamilias.item(i);
				//Guardamos el texto que contiene cada ciclo
				String titulo = nFamilia.getFirstChild().getTextContent();
				System.out.println("Titulo de Ciclo"+titulo);
				
				//Scamos la lista de nodos con el nombre curso
				NodeList nlCiclo = ((Element)nFamilia).getElementsByTagName("ciclo");

					System.out.println("Numero de ciclos: " + nlCiclo.getLength());	
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
