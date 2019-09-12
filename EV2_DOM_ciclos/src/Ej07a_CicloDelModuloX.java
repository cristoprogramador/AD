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
 * (CicloDeModulo) Mostrar el ciclo (titulo) al que al que pertenece
 * el módulo cuyo nombre introduce el usuario. (fichero ciclos.xml).
 * @author alumno
 *
 */
public class Ej07a_CicloDelModuloX {

	public static void main(String[] args) {
		Scanner tec = new Scanner(System.in);
		System.out.println("Introduzca nombre del mudulo: ");
		String nomModulo = tec.nextLine();
		
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
						
			NodeList nlModulo = doc.getElementsByTagName("modulo");
			Node nModulo = null;
			
			for (int i = 0; i < nlModulo.getLength(); i++) {
				if (nlModulo.item(i).getTextContent().equals(nomModulo))
					nModulo = nlModulo.item(i);
			}
			
			//recorremos la lista de nodos
			if (nModulo == null){
				System.out.println("El Modulo no se ha encontrado");				
			}else {
				String nombreCiclo = nModulo.getParentNode().getPreviousSibling().getTextContent();
				System.out.println("El modulo " + nomModulo + " pertenece al ciclo: " + nombreCiclo);
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
