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
 * AA13
ACTIVIDAD INTEGRADORA DE JAVA - Github - Jenkins

Nos contrataron de una empresa que desea implementar 
la metodologia de Integración Continua. Para esto, nos piden que desarrollemos un 
programa en Java que implemente un jenkinsfile
Ademas de eso, quieren que contabilicemos el TOP 20 de peliculas 
con mayor recaudación de LA HISTORIA. Para esto, nos dejaron un archivo con las 10 
peliculas siguientes al top 10 con mayor recaudación en la historia. 


PELICULA A 1
PELICULA B 2
PELICULA C 3
PELICULA D 4
PELICULA E 5
PELICULA F 6
PELICULA G 7
PELICULA H 8
PELICULA I 9
PELICULA J 10
PELICULA A 11
PELICULA B 12
PELICULA C 13
PELICULA D 14
PELICULA E 15
PELICULA F 16
PELICULA G 17
PELICULA H 18
PELICULA I 19
PELICULA J 20
El archivo debe estar ordenado por recaudación (de mayor recaudación a menor recaudación) 

En GitHub:
Subir todo el proyecto de Java, una vez generados los TXTS. 

En Jenkins:
(SOBRE LA RAMA PRINCIPAL) 
El jenkinsfile debe ser tomado del repositorio en Github. El contenido del mismo debe mostrar por consola (Consola de Jenkins) el siguiente mensaje:
"Hola Mundo! EL día de hoy es elDia (variable que almacene la fecha).
 Este curso me hizo programar mas de lo que me hubiese gustado" 

¿COMO FUNCIONA ESTO? 
1) Crear un nuevo proyecto en Eclipse "AA13 - Java integrado a Jenkins y Github"
2) Desarrollar la estructura del programa. 
3) Desarrollar el jenkinsfile. 
4) Actualizar el repo desde Eclipse a Github
5) Una vez actualizado el repo, configurar en jenkins la busqueda del jenkinsfile generado por el proyecto. 

ENTREGABLES: 
- Codigo del proyecto de Java. 
- Captura de la pantalla de Java mostrando las 20 mejores peliculas
- Captura de la pantalla de Jenkins
- TXT de Salida de peliculas (Llamarlo "top20_mejores_peliculas.txt")

¿COMO GENERAR EL JENKINSFILE? 
Un jenkinsfile es un simple TXT. GENERAMOS UN TXT Y LE INSERTAMOS EL CODIGO QUE ESPERA JENKINS. 
 */

public class Principal {

	public static void main(String[] args) {
		List<Pelicula> pelis = new ArrayList<>();
		List<File> ficherosPelis = new ArrayList<>();
		int numeroFicherosALeer = 2;
		ficherosPelis.add(new File("Peliculas_1_10.txt"));
		ficherosPelis.add(new File("Peliculas_11_20.txt"));
		try {
			for (int i = 0; i < (numeroFicherosALeer); i++) {// Repito para todos los archivos TXT de peliculas que
																// tenga en este caso solo 2
				Scanner sc = new Scanner(ficherosPelis.get(i));
				sc.nextLine();// Salto cabecera
				while (sc.hasNext()) {// Repito hasta leer todas las lineas -> peliculas
					String linea = sc.nextLine();
					linea = linea.replaceFirst("		", ";");// delimito pelis de recaudación con ;
					String[] partes = linea.split(";");// divido en partes usando el ;
					partes[0] = partes[0].trim();
					partes[1] = partes[1].trim(); // elimino espacios del principio y del final innecesarios
					partes[1] = partes[1].replaceAll(",", ""); // a los valores monetarios elimino comas y simbolo de
																// dolar para hacer el cast a DOUBLE
					partes[1] = partes[1].replace("$", "");
					pelis.add(new Pelicula(partes[0], Long.parseLong(partes[1]))); // añado a la lista de pelis las
																						// peliculas
				}
				sc.close();
			}
			
			// Aunque en un principio las pelis están ordenadas porque las he leido en
			// orden, creo un proceso para odenarlas por el valor recaudado y lo aplico.
			List<Pelicula> pelisOrdenadas = new ArrayList<>();
			int mayor = 0;
			int primerbucle = pelis.size();
			for (int i = 0; i < primerbucle; i++) {
				for (int j = 0; j < pelis.size(); j++) {
					if (pelis.get(mayor).getRecaudacion() < pelis.get(j).getRecaudacion()) {
						mayor = j;
					}
					pelisOrdenadas.add(pelis.get(mayor));
					pelis.remove(mayor);
					mayor = 0;
				}
			}
			System.out.println(pelisOrdenadas); //Esta sería le lista de peliculas ordenadas por el monto de recaudación, la escribo en un fichero.
			List<String> pelisString = new ArrayList<>();
			for(Pelicula iter:pelisOrdenadas) {
				pelisString.add(iter.getNombre() + "		$" + iter.getRecaudacion());
			}
			Path file = Paths.get("20pelisOrdenadas.txt");
			try {
				Files.write(file, pelisString, StandardCharsets.UTF_8);
			} catch (IOException e) {
				System.out.println("Excepción en el write().");
				e.printStackTrace();
			}
			
			//Por último genero el Jenkinsfile. He creado una función para ello.
			creaJenkins();

		} catch (FileNotFoundException e) {
			System.out.println("Excepcion en listapelis");
			e.printStackTrace();
		}
	}
	public static void creaJenkins() {
		List<String> jenk = new ArrayList<>();
		jenk.add("import java.time.LocalDate");
		jenk.add("pipeline{");//{
		jenk.add("	agent any");
		jenk.add("	stages{");//{
		jenk.add("		stage(\"holaMundo\"){");//{
		jenk.add("			steps{");//{
		jenk.add("				script{");//{
		jenk.add("					println \"¡Hola mundo! El día de hoy es \" + LocalDate.now().getDayOfWeek()");
		jenk.add("					println \"Este curso me hizo programar mucho en JAVA\"");
		jenk.add("				}");
		jenk.add("			}");
		jenk.add("		}");
		jenk.add("	}");
		jenk.add("}");
		Path file = Paths.get("Jenkinsfile");
		try {
			Files.write(file, jenk, StandardCharsets.UTF_8);
		} catch (IOException e) {
			System.out.println("Excepción en el write().");
			e.printStackTrace();
		}
	}
}
