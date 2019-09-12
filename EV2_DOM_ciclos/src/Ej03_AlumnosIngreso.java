import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
 *(AlumnosIngreso) Mostrar número de expediente, nombre y 
 *fecha de ingreso de todos los alumnos que hay ingresado en el año actual.
 * @author alumno
 *
 */

public class Ej03_AlumnosIngreso {
	public static void main(String[] args) {
		Scanner tec = new Scanner(System.in);
		System.out.println("Introduzca año de ingreso: ");
		String anyo = tec.nextLine();
		
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
			/*
			//SACAR EL AÑO ACTUAL
			int anoyoActual = Calendar.getInstance().get(Calendar.YEAR);
			//PASAMOS A DATE LA FECHA EN STRING
			Date fechaa = new SimpleDateFormat("dd/MM/yyyy").parse(sFecha);
			Calendar c = Calendar.getInstance();
			c.setTime(fechaa);
			int anyo = c.get(Calendar.YEAR);
			*/			
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
				String fecha = eAlumno.getElementsByTagName("fechaingreso").item(0).getTextContent();
				if(fecha.contains(anyo)){
					System.out.println("Expediente: "+eAlumno.getElementsByTagName("expediente").item(0).getTextContent());
					System.out.println("Nombre del Alumno: "+eAlumno.getElementsByTagName("nombrealu").item(0).getTextContent());
					System.out.println("Fecha de ingreso: " + fecha);
					System.out.println("--------------");
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
