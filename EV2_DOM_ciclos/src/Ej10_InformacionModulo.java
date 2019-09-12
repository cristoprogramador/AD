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
 * (InformacionModulo) Dado el nombre de un m�dulo que introduce el usuario mostrar
 * familia, t�tulo y curso al que pertenece (fichero ciclos.xml).
 * @author alumno
 *
 */
public class Ej10_InformacionModulo {

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
			//informaci�n sacada de la cabecera.
			System.out.println("Tipo de documento: " + doc.getDoctype().getName());
			System.out.println("Encoding: " + doc.getXmlEncoding());
			System.out.println("Versi�n de xml: " + doc.getXmlVersion());
			System.out.println();
			
			NodeList nlModulos = doc.getElementsByTagName("modulo");		
			boolean encontrado = false;
			for (int i = 0; i < nlModulos.getLength() && !encontrado; i++) {				
				Element eModulo = (Element)nlModulos.item(i);				
				if(eModulo.getTextContent().equals(nomModulo)){
					System.out.println("Modulo: " + nomModulo);
					System.out.println("Familia: " + 
							eModulo.getParentNode().getParentNode().getParentNode().getFirstChild().getTextContent());
					System.out.println("Tigulo: " + 
							eModulo.getParentNode().getParentNode().getFirstChild().getTextContent()); 
					System.out.println("Curso numero: " + 
							((Element)eModulo.getParentNode()).getAttribute("numero"));
					encontrado = true;							
				}			
			}
			if(!encontrado){
				System.out.println("Nombre de modulo no encontrado");
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
