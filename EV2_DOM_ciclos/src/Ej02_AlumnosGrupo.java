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
 *(AlumnosGrupo) Mostrar número de expediente, nombre y fecha de ingreso 
 *de todos los alumnos que pertenecen al grupo cuyo nombre indique el usuario. 
 *(fichero grupos.xml).
 * @author alumno
 *
 */

public class Ej02_AlumnosGrupo {
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
				if (nombreGrupo.equals(nombre)){
					Element eGrupo = (Element) nlGrupo.item(i);
					//Buscamos elementos ciclo y los guardamos en una lista de nodos
					NodeList nlAlumnos = eGrupo.getElementsByTagName("alumno");
					for (int j = 0; j < nlAlumnos.getLength(); j++) {
						Element eAlumno = (Element) nlAlumnos.item(j);
						System.out.println("Expediente: "+eAlumno.getElementsByTagName("expediente").item(0).getTextContent());
						System.out.println("Nombre del Alumno: "+eAlumno.getElementsByTagName("nombrealu").item(0).getTextContent());
						System.out.println("Fecha de ingreso: "+eAlumno.getElementsByTagName("fechaingreso").item(0).getTextContent());
						System.out.println("--------------");
					}
				}
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
