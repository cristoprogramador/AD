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
 * Crea la clase CicloFormativo y realiza un programa que construya 
 * una lista de objetos CiclosFormativo a partir del contenido del 
 * fichero ciclos.xml. La clase CicloFormativo tiene los siguientes atributos:
 * o nombre (String)
 * o familia (String)
 * o grado (int) (1: medio, 2: superior)
 * o horas (int) total de horas del ciclo
 * @author alumno
 *
 */
public class Ej12_CrearClaseCicloFormativo {

	public static void main(String[] args) {
		
		DocumentBuilderFactory factory = null;
		DocumentBuilder builder = null;
		
		List<CicloFormativo> listaCiclosFormativos = new ArrayList<>();
		
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
						
			NodeList nlCiclos = doc.getElementsByTagName("ciclo");

			for (int i = 0; i < nlCiclos.getLength(); i++) {
				Element eCiclo = (Element)nlCiclos.item(i);
				Node nCiclo = nlCiclos.item(i);
				
				String nombre = eCiclo.getFirstChild().getTextContent();
				String familia = eCiclo.getParentNode().getFirstChild().getTextContent();
				
				String NombreGrado = eCiclo.getAttribute("grado");				
				int grado = 0;
				if (NombreGrado.equalsIgnoreCase("superior"))
					grado = 2;
				else if (NombreGrado.equalsIgnoreCase("medio"))
					grado = 1;
				
				int totalHoras = 0;
				NodeList nlModulos = eCiclo.getElementsByTagName("modulo");
				for (int j = 0; j < nlModulos.getLength(); j++) {
					int horas = Integer.parseInt(((Element)nlModulos.item(j)).getAttribute("horas"));
					totalHoras += horas;
				}
			
				
				listaCiclosFormativos.add(new CicloFormativo(nombre, familia, grado, totalHoras));
			}
			

			
			for (CicloFormativo cicloformativo : listaCiclosFormativos) {
				System.out.println("Familia: " + cicloformativo.getFamilia());
				System.out.println("Nombre del ciclo: " + cicloformativo.getNombre());
				System.out.println("Grado: " + cicloformativo.getGrado());
				System.out.println("Horas: " + cicloformativo.getHoras());
				System.out.println("===========================================");
			}
				
			System.out.println("Proceso terminado");
			System.out.println("Objetos Modulo creados: " + listaCiclosFormativos.size());
			
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
