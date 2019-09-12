package dom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Ejercicio1b {

	public static void main(String[] args) {
		DocumentBuilderFactory factory = null;
		DocumentBuilder builder = null;
		CD cd = new CD();
		
		try {
			
			factory = DocumentBuilderFactory.newInstance();
			factory.setIgnoringElementContentWhitespace(true);
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse("cdinfo.xml");
			doc.normalize();
			
			//Titulo del cd
			cd.setTitulo_cd(doc.getLastChild().getFirstChild().getNextSibling().getTextContent());
			
			//Numero de canciones
			NodeList nlCanciones = doc.getElementsByTagName("cancion");
			int count = 0;
			for (int i = 0; i < nlCanciones.getLength(); i++) {
				count++;
			}
			cd.setNum_canciones(count);

			//Lista de Generos
			List<String> listaGeneros = new ArrayList<>();
			NodeList nlGeneros = doc.getElementsByTagName("genero");
			for (int i = 0; i < nlGeneros.getLength(); i++) {
				listaGeneros.add(nlGeneros.item(i).getTextContent());
			}
			cd.setGeneros(listaGeneros);

			//Numero de discos
			NodeList nlContenido = doc.getElementsByTagName("contenido_cd");
			int countDisco = 0;
			for (int i = 0; i < nlContenido.getLength(); i++) {
				countDisco++;
			}
			cd.setNum_disco(countDisco);

			//Mostramos el contenido del objeto cd por pantalla
			System.out.println("Titulo de diso: " + cd.getTitulo_cd());
			System.out.println("Numero de discos: " + cd.getNum_disco());
			System.out.println("Numero de canciones: " + cd.getNum_canciones());
			System.out.println("Generos:");
			for (String string : cd.getGeneros()) {
				System.out.println(" - " + string);
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
