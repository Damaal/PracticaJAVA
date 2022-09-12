import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

/*
 * AA15
Este programa debe tener implementado lo siguiente:
- posición
- Interfaces
- Herencia
- Manejo de Ficheros
- Implementación de un archivo JSON a traves de una API
- Mensaje por consola de Jenkins
- Implementación del proyecto en la rama de Desarrollo con merge en la rama principial (main o master segun aplique)
https://public.opendatasoft./explore/dataset/provincias-espanolas/table/?sort=-provincia


PROBLEMATICA: 
Nos contrataron desde el gobierno de España para diseñarles un programa que les informe las Provincias y sus respectivas capitales, el programa debe tener la capacidad de generar un archivo TXT de salida informando por cada provincia
su capital. El txt de salida debe tener la fecha de cuando corrio el proceso dentro del archivo. 

ESTRUCTURA DEL PROGRAMA: 
- Contar con al menos 3 clases, una de Provincia, una de Capital de provincia y otra a definir por el programador. HECHO
- Contar con un metodo implementado en una interfaz que conste de generar el archivo de salida. HECHO
- Utilizar la posición para implementar la información del usuario que lo implemente. Ejemplo: ID_USUARIO: 1 Nombre del usuario: Vargas,Gustavo Fecha Login 12/09/2022 HECHO
(!) La información del usuario debe ir en el TXT implementada 	HECHO
- Mostrar la información por consola de Jenkins. Generando un jenkinsfile que implemente la salida HEHCO


ENTREGABLES:
- Codigo del proyecto
- Captura de consola de Jenkins 
- TXT de salida 
 */
public class Principal {

	public static void main(String[] args) {
		
	try {
		
		URL url = new URL("https://public.opendatasoft.com/api/records/1.0/search/?dataset=provincias-espanolas&q=&rows=0&sort=-provincia&facet=ccaa&facet=provincia\r\n"
				+ "");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.connect();
		if(conn.getResponseCode() != 200) {
			throw new RuntimeException("HttpResponse" + conn.getResponseCode());
		}
		else {
			Scanner sc = new Scanner(url.openStream());
			String json = "";
			while(sc.hasNextLine()) {
				json = json + sc.nextLine();
			}
			sc.close();
			JSONObject objetoJSON = new JSONObject(json);
			JSONArray arrayJSON = (JSONArray) objetoJSON.get("facet_groups");
			objetoJSON = (JSONObject) arrayJSON.get(1);
			arrayJSON = (JSONArray) objetoJSON.get("facets");
			//System.out.println(arrayJSON);
			List<Provincia> listaProvincia = new ArrayList<>();
			for(Object iter:arrayJSON) {
				listaProvincia.add(new Provincia(((JSONObject) iter).getString("name"), ((JSONObject) iter).getString("path")));
			}
			Provincia prov = new Provincia(null, null);
			Usuario usr = new Usuario("Danut Marian", "1", LocalDate.now());
			prov.generaSalida(listaProvincia, usr);
			File file = new File("APIcapitales.txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(usr.toString());
                writer.newLine();
                writer.write(listaProvincia.toString());
            }
		}
	} catch (MalformedURLException e) {
		System.out.println("Excepcion en URL.");
		e.printStackTrace();
	} catch (IOException e) {
		System.out.println("Excepción en openConection().");
		e.printStackTrace();
	}

	}


}
