import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;


public class Chocolate extends Golosina implements Produccion {
	
	//Atributos clase
	private String tipo;
	private int cantidadProducida;
	
	//Getters
	public String getTipo() {
		return tipo;
	}
	public String getNombre() {
		return nombre;
	}
	public int getCantidadProducida() {
		return cantidadProducida;
	}


	//Setters
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setCantidadProducida(int cantidadProducida) {
		this.cantidadProducida = cantidadProducida;
	}
	
	//Constructor
	public Chocolate(String nombre, String tipo, int cantidadProducida){
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.cantidadProducida = cantidadProducida;
	}
	
	
	public boolean produccionActiva() {
		//La producción va en función de la temperatura máxima que va a hacer en Sevilla ese día
		try {
			URL url = new URL("https://www.el-tiempo.net/api/json/v2/provincias/41");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.connect();
			if(conn.getResponseCode() != 200) {
				throw new RuntimeException("HttpResponse" + conn.getResponseCode());
			}else {//La conexión ha sido exitosa
				String json = "";
				Scanner sc = new Scanner(url.openStream());
				while(sc.hasNextLine()) {
					json = json + sc.nextLine();
				}
				JSONObject objetoJSON = new JSONObject(json);
				//System.out.println( ((JSONObject)((JSONObject)((JSONArray)objetoJSON.get("ciudades")).get(3)).get("temperatures")).get("max") );
				if(Integer.parseInt(((JSONObject)((JSONObject)((JSONArray)objetoJSON.get("ciudades")).get(3)).get("temperatures")).get("max").toString()	) < 40) return true;
				else return false;
			}
		} catch (IOException e) {
			System.out.println("Excepcion en apertura de conexión");
			e.printStackTrace();
			return false;
		}
	}
}
