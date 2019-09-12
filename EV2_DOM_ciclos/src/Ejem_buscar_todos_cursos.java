import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Ejem_buscar_todos_cursos {
	public static void main(String[] args) {
		DocumentBuilderFactory factory = null;
		DocumentBuilder builder = null;
		
		try {
			//Cargamos el .xml en el Doc
			factory = DocumentBuilderFactory.newInstance();
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
			
			NodeList nlTitulos = doc.getElementsByTagName("titulo");			
			//recorremos la lista de nodos			
			for(int i=0 ; i < nlTitulos.getLength() ; i++){
				//sacamos cada uno de los nodos
				Node nTitulo = nlTitulos.item(i);
				//Guardamos el texto que contiene cada ciclo
				String titulo = nTitulo.getTextContent();
				System.out.println("Titulo de Ciclo"+titulo);
				
				//Volvemos al padre del titulo
				Node nCiclo = nTitulo.getParentNode();
				//Scamos la lista de nodos con el nombre curso
				NodeList nlCurso = ((Element)nCiclo).getElementsByTagName("curso");
				for (int j = 0; j < nlCurso.getLength(); j++) {
					Node nCurso = nlCurso.item(j);
					//Sacamos de cada curso el atributo numero de curso
					String curso = ((Element) nCurso).getAttribute("numero");
					System.out.println("Curso: " + curso);
					
					//Scamos la lista de nodos de los modulos de cada curso
					NodeList nlCmodulo = ((Element)nCurso).getElementsByTagName("modulo");
					for (int f = 0; f < nlCmodulo.getLength(); f++) {
						Node nModulo = nlCmodulo.item(f);
						//Sacamos de cada nodo modulo el atributo horas del curso
						String horasModulo = ((Element) nModulo).getAttribute("horas");
						//Sacamos el texto que contiene cada nodo modulo, que será el nombre
						String nombreModulo = nModulo.getTextContent();
						System.out.println("Modulo: " + nombreModulo + " Horas: " + horasModulo);
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
