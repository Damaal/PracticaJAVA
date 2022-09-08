import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

/*
 * GENERAR UN PROYECTO MAVEN QUE MUESTRE POR CONSOLA LA INFORMACION DEL CLIMA ACTUAL,
 *  ADEMAS DE MOSTRAR UN MENSAJE DE BIENVENIDA AL USUARIO, UNA VEZ INSERTADO
SU NOMBRE.
INTEGRAR EL NUEVO PROYECTO EN GITHUB BAJO EL NOMBRE DE EJERCICIO AA10
ENTREGABLE:
Codigo del proyecto
link del repositorio * 
 */
public class Principal {

	public static void main(String[] args) {
		try {
			URL url = new URL("https://www.el-tiempo.net/api/json/v2/provincias/21");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.connect();
			//Manejar código de respuesta
			if(conn.getResponseCode() != 200) {
				throw new RuntimeException("HttpResponse" + conn.getResponseCode());
			}
			else {
				Scanner sc = new Scanner(url.openStream());
				String linea = "";
				while(sc.hasNext()){
					linea = linea + sc.nextLine();
				}
				//System.out.println(linea);
				JSONObject objetoJSON = new JSONObject(linea);
	
				sc.close();
				sc = new Scanner(System.in);
				System.out.print("Introduce tu nombre: ");
				String nombre = sc.nextLine();
				System.out.println("Bienvenid@ " + nombre);
				System.out.println(objetoJSON.get("title")  + " para hoy: " + "\n" + ((JSONObject) objetoJSON.get("today")).get("p"));
				//System.out.println(	((JSONObject) objetoJSON.get("ciudades")).get("name") );
				JSONObject miobjeto = (JSONObject)((JSONArray)objetoJSON.get("ciudades")).get(3);
				System.out.println("Se esperan mínimas de " 
									+ ((JSONObject) miobjeto.get("temperatures")).get("min") 
									+ "º y máximas de " 
									+ ((JSONObject) miobjeto.get("temperatures")).get("max") 
									+ "º");
				System.out.println("¡Que tengas feliz día!");
			}
		} catch (MalformedURLException e) {
			System.out.println("Excepción con la URL");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Excepción abriendo la conexión");
			e.printStackTrace();
		}

	}

}
