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
 * Crea la clase GrupoAlumnos para permitir almacenar los datos 
 * de un grupo de alumnos y una lista con los nombres de sus alumnos 
 * (solo los nombres). 
 * A continuación escribe un programa que construya 
 * un List<GrupoAlumnos> con los grupos existentes en el fichero
 * grupos.xml. La clase GrupoAlumnos tiene los siguientes atributos:
 * o codigo (String)
 * o nombre(String)
 * o maxAlumnos(int)
 * o nombreAlumnos (List<String>)
 * @author alumno
 *
 */
public class Ej13_CrearClaseGrupoAlumnos {

	public static void main(String[] args) {
		
		DocumentBuilderFactory factory = null;
		DocumentBuilder builder = null;
		
		List<GrupoAlumnos> listaGrupoAlumnos = new ArrayList<>();
		try {
			//Cargamos el .xml en el Doc
			factory = DocumentBuilderFactory.newInstance();
			factory.setIgnoringElementContentWhitespace(true);
			builder = factory.newDocumentBuilder();			
			Document doc = builder.parse("grupos.xml");
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
						
			NodeList nlGrupos = doc.getElementsByTagName("grupo");

			for (int i = 0; i < nlGrupos.getLength(); i++) {
				Element eGrupo = (Element)nlGrupos.item(i);
				
				String codigo = eGrupo.getAttribute("codigo");
				String nombre = eGrupo.getFirstChild().getTextContent();
				int maxAlumnos = Integer.parseInt(eGrupo.getAttribute("maxalumnos"));
				
				List<String> nombreAlumnos = new ArrayList<>();
				NodeList lnAlumnos = eGrupo.getElementsByTagName("nombrealu");
				for (int j = 0; j < lnAlumnos.getLength(); j++) {
					nombreAlumnos.add(lnAlumnos.item(j).getTextContent());
				}
				
				listaGrupoAlumnos.add(new GrupoAlumnos(codigo, nombre, maxAlumnos, nombreAlumnos));
			}
						
			for (GrupoAlumnos grupoAlumnos : listaGrupoAlumnos) {
				System.out.println("Nombre del Grupo: " + grupoAlumnos.getNombre());
				System.out.println("Codigo del Curos: " + grupoAlumnos.getCodigo());
				System.out.println("Numero maximo de alumnos: " + grupoAlumnos.getMaxAlumnos());
				for (String nombre: grupoAlumnos.getNombreAlumnos()){
					System.out.println("Alumno: " + nombre);
				}
				System.out.println("===========================================");
			}
				
			System.out.println("Proceso terminado");
			System.out.println("Objetos Modulo creados: " + listaGrupoAlumnos.size());
			
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
