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
 * (Alumnos) Mostrar número de expediente, nombre y fecha de ingreso 
 * de todos los alumnos (fichero grupos.xml).
 * @author alumno
 *
 */

public class Ej01_Alumnos {
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
			//Buscamos elementos ciclo y los guardamos en una lista de nodos
			NodeList nlAlumnos = doc.getElementsByTagName("alumno");
			for (int i = 0; i < nlAlumnos.getLength(); i++) {
				Element eAlumno = (Element) nlAlumnos.item(i);
				System.out.println("Expediente: "+eAlumno.getElementsByTagName("expediente").item(0).getTextContent());
				System.out.println("Nombre del Alumno: "+eAlumno.getElementsByTagName("nombrealu").item(0).getTextContent());
				System.out.println("Fecha de ingreso: "+eAlumno.getElementsByTagName("fechaingreso").item(0).getTextContent());
				System.out.println("--------------");
			}
			
			System.out.println("=============== OTRA FORMA (Navegando)===============");
			for (int j = 0; j < nlAlumnos.getLength(); j++) {
				Node nAlumno = nlAlumnos.item(j);
				Node nexpediente = nAlumno.getFirstChild();
				String s_expediente = nexpediente.getTextContent();
				Node n_nombre = nexpediente.getNextSibling();
				String s_nombre = n_nombre.getTextContent();
				Node n_fecha= n_nombre.getNextSibling();
				String s_fecha = n_fecha.getTextContent();

				System.out.println("Expediente: "+s_expediente);
				System.out.println("Nombre del Alumno: "+s_nombre);
				System.out.println("Fecha de ingreso: "+s_fecha);
				System.out.println("--------------");
				
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
