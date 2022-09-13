package spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



/*
 * EJERCICIO AA16
--------------- 	IMPLEMENTANDO AOP, INTERFACES, CLASES, HERENCIA, GITHUB ---------------
PROBLEMATICA: Nos contrataron de Spotify para desarrollar un programa que nos brinde informaci�n general del uso de la aplicaci�n
A d�a de hoy tienen 4 archivos planos donde tienen los registros de los ultimos a�os y les gustar�a unificarlos en uno solo. Los archivos son los siguientes:
TOP 10 CANCIONES DE 2021
TOP 10 CANCIONES DE 2020
TOP 10 ARTISTAS DE 2021
TOP 10 ARTISTAS DE 2020
Luego de haber informado esto, nos piden que informemos, segun el valor de cada canci�n su recaudaci�n global. teniendo en cuenta que por cada vez que se escucha una canci�n se le da 2 euros a cada artista
El formato del archivo de salida esperado es el siguiente: 
TOP 10 CANCIONES DE 2020 + Recaudaci�n de cada una
TOP 10 CANCIONES DE 2021 + Recaudaci�n de cada una
TOP 10 ARTISTAS DE 2020
TOP 10 ARTISTAS DE 2021

AOP: 
- Implementar AOP ANTES de ejecutar el metodo generaArchivo() informando que se esta por generar un archivo nuevo. 
- Implementar AOP ANTES de cargar los TXTS en el programa validando que sean formato .txt 
- Implementar AOP DESPUES de generar la salida informando que se genero la salida del programa.

INTERFACES: 
- Implementar un metodo que calcule la recaudaci�n

CLASES:
Declarar la clase Persona, Artista y Usuario informando atributos de cada una de las mismas. 

ENTREGABLE:
- Codigo del proyecto
- Archivo final de salida
- Subir el proyecto al repo de Github. 

 */

public class Main 
{
	public static void main (String [] args)
	{
		List<Artista> listaArtistas = new ArrayList<>();
		List<Cancion> listaCanciones = new ArrayList<>();
		List<String> ficherosCanciones = new ArrayList<>();
		List<String> ficherosArtistas = new ArrayList<>();
		ficherosCanciones.add("Canciones 2020.txt");
		ficherosCanciones.add("Canciones 2021.txt");
		ficherosArtistas.add("Artistas 2020.txt");
		ficherosArtistas.add("Artistas 2021.txt");
		
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ConfiguraSpring.class)) {
			Cancion cancion = ctx.getBean(Cancion.class);
			listaCanciones = cancion.leoArchivosC(ficherosCanciones);
			listaArtistas = cancion.leoArchivosA(ficherosArtistas);
			cancion.generaArchivo(listaCanciones, listaArtistas);
			
		} catch (BeansException e) {
			System.out.println("Excepcion en el main");
			e.printStackTrace();
		}

	}
	
}
