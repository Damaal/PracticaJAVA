import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * AA9
RELEVAMIENTO:
Nos contratan de una empresa de videojuegos para poder ayudarlos a desarrollar una APP que almacene la información de los titulos mas importantes que tienen hoy en día
Para esto, debemos desarrollar un programa que informe a los empleados los titulos de videojuegos y sus respectivas ventas con el monto total que viene acumulando
dichas ventas. 

ESTRUCTURA DEL PROGRAMA:
- Contar con una interfaz que calcule el monto total de los videojuegos y su recaudación
- TXT de salida que informe titulos, cantidades de unidades vendidas y monto total.
- Contar con una clase llamada videojuego que tenga atributos pertinentes a los videojuegos.

ENTREGABLES: 
- El proyecto debe estar publicado en Github
- Enviar el link del repo
- Enviar el codigo
- Enviar el TXT de salida.  


VIDEOJUEGOS:			CANTIDAD DE REGISTROS		PRECIO UNITARIO
DARK SOULS 3 				10000					5
THE LAST OF US 				50000					10
FIFA 2022					60000					15
MARIO BROSS					45000					1
DOOM 2						100000					1
HORIZON						50000					5
 */
public class Principal {

	public static void main(String[] args) {
		//Para complicar un poco más el ejercicio el ejemplo de los cabecera-videojuegos-ventas-precio lo introduzco mediante un TXT (ventas_AA9.txt)
		File file = new File("ventas_AA9.txt");
		
		if(file.exists()) {
			try {
				Scanner sc = new Scanner(file);
				String linea = null;
				List<Videojuego> listaVideojuegos = new ArrayList<>();
				String titulo = null;
				int ventas = 0;
				double precio = 0.0;
				sc.nextLine(); //me salto la cabecera del TXT
				while(sc.hasNext()) {
					linea = sc.nextLine(); //Leo la proxima linea
					linea = linea.replaceAll("(				)", ";"); //Cambio donde haya más de 2 o 3 tabulaciones por ";"
																	  //Asi consigo dividir [ NombreVideojuego ; numVentas ; precioUnidad ]
					String [] partes = linea.split(";"); //Divido la linea por los ;
					for(int i = 0; i < 3; i++) {	//Recorro ahora todas las partes generadas, que serán 3.
						String dato = partes[i].trim(); //A cada parte generada le elimino los espacios en blanco del PRINCIPIO y del FINAL con String.trim();
						switch (i) {
						case 0://TITULO
							titulo = dato;
							break;
						case 1://NUMERO DE VENDIDOS
							ventas = Integer.parseInt(dato);
							break;
						case 2://PRECIO POR UNIDAD
							precio = Double.parseDouble(dato);
							//Cuando llegue al case 2 -> i = 2 tengo los datos completos de un videojuego, lo añado a mi lista de videojuegos.
							listaVideojuegos.add(new Videojuego(titulo, ventas, precio));
							break;
						default:
						//nada
						}	
					}
				}
				String salida = "recaudacion_ventas_por_titulo.txt";
				Path archivo = Paths.get(salida);
				List<String> listaVentas = new ArrayList<>();
				listaVentas.add("TITULO			$RECAUDADO			UNIDADES VENDIDAS");
				for(Videojuego iter : listaVideojuegos) {
					System.out.println(iter);
					listaVentas.add(iter.getTitulo() + "			" + iter.beneficioVenta(iter.getVentas(), iter.getPrecio()) + "			" + iter.getVentas());
				}
				Files.write(archivo, listaVentas, StandardCharsets.UTF_8);
				
			} catch (FileNotFoundException e) {
				System.out.println("Error creando el Scanner");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Error escribiendo en salida.");
				e.printStackTrace();
			}
		}else {
			System.out.println("Archivop de ventas no encontrado.");
		}
		

		

	}

}
