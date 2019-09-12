import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CantidadDeModulosPorCicloYHorasTotales {

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
			//Buscamos elementos ciclo y los guardamos en una lista de nodos
			NodeList nlCiclos = doc.getElementsByTagName("ciclo");			
			//recorremos la lista de nodos ciclo		
			for(int i=0 ; i < nlCiclos.getLength() ; i++){
				//sacamos cada uno de los nodos ciclo
				Node nlCiclo = nlCiclos.item(i);
				//Guardamos el texto que contiene el primer hijo nombre
				String nombre = nlCiclo.getFirstChild().getTextContent();
				System.out.println("Titulo de Ciclo: " + nombre);				
				//Scamos la lista de nodos con el nombre curso
				NodeList nlmodulo = ((Element)nlCiclo).getElementsByTagName("modulo");
				System.out.print("Numero de modulos: " + nlmodulo.getLength());
				//recorremos todos lod modulos y sumamos sus horas por ciclo
				Integer horas = 0;
				for (int f = 0; f < nlmodulo.getLength(); f++) {						
					Node nModulo = nlmodulo.item(f);				
					//Sacamos de cada nodo modulo el atributo horas del curso
					horas += Integer.parseInt(((Element) nModulo).getAttribute("horas"));
				}
				System.out.println(", Horas: " + horas);
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
