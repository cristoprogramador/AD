import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
 * Crea la clase Modulo y realiza un programa que construya una lista 
 * de objetos Mdulo a partir del contenido del fichero ciclos.xml. 
 * La clase Modulo tiene los siguientes atributos:
 * o nombre (String)
 * o horas (int)
 * o curso (int)
 * @author alumno
 *
 */
public class Ej11_CrearClaseModulo {

	public static void main(String[] args) {
		
		DocumentBuilderFactory factory = null;
		DocumentBuilder builder = null;
		
		List<Modulo> listaModulos = new ArrayList<>();
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

			for (int i = 0; i < nlModulo.getLength(); i++) {
				Element eModulo = (Element)nlModulo.item(i);
				
				String nombre = eModulo.getTextContent();
				int horas = Integer.parseInt(eModulo.getAttribute("horas"));
				int curso = Integer.parseInt(((Element)eModulo.getParentNode()).getAttribute("numero"));;
				
				listaModulos.add(new Modulo(nombre, horas, curso));
			}
			

			
			for (Modulo modulo : listaModulos) {
				System.out.println("Curso numero: " + modulo.getCurso());
				System.out.println("Nombre del modulo: " + modulo.getNombre());
				System.out.println("Horas: " + modulo.getHoras());
				System.out.println("===========================================");
			}
				
			System.out.println("Proceso terminado");
			System.out.println("Objetos Modulo creados: " + listaModulos.size());
			
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
