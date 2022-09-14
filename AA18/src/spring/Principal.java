package spring;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.BeansException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*
 * EJERCICIO AA18
Nos contrataron de las naciones unidas para diversificar información que hoy se tiene de distintos paises y entregar
la misma a los distintos responsables de los paises para que la tengan a disposición y mas clara. Para esto
Se debe agarrar una base de datos vieja que posee las Naciones Unidas con información relevante para los distintos
paises.

ESTRUCTURA DEL EJERCICIO:
- 2 Interfaces, 1 debe implementar una función lambda
- 1 Función Lambda
- 3 clases (como minimo)
  - Clase de usuario
  - Clase de Pais
  - Clase Continente
- Generacioón de N archivos (1 por cada Pais con cada información pertinente a cada pais)
- AOP que analice la función que genera archivos e informe cuando finalizo la misma y tambien la información
del usuario que corrio el proceso de generación de archivos

EXTRA: Agregar a la salida el PBI de cada país, se calcula en base a CANTIDAD DE HABITANTES * Salario minimo

ENTREGABLES:
- Codigo del proyecto
- N txts

 */
public class Principal {

	public static void main(String[] args) {
		// Declaro la función lambda
		InterfazLambda pib = (int numHambitantes, int salarioMinimo) -> (double)numHambitantes * salarioMinimo;
		try {
			String pais = null, continente = null, capital = null, clima = null;
			int salario = 0, habitantes = 0;
			List<Pais> listaPaises = new ArrayList<>();
			Scanner sc = new Scanner(new File("INFO_PAISES.txt"));
			while (sc.hasNext()) {
				String linea = sc.nextLine();
				String[] partes = linea.split(":");
				partes[0] = partes[0].trim().toLowerCase().replace(" ", "");
				partes[1] = partes[1].trim();
				switch (partes[0]) {
				case "pais":
					pais = partes[1];
					break;
				case "continente":
					continente = partes[1];
					break;
				case "capital":
					capital = partes[1];
					break;
				case "habitantes":
					habitantes = Integer.parseInt(partes[1]);
					break;
				case "climapredominante":
					clima = partes[1];
					break;
				case "salariominimo":
					salario = Integer.parseInt(partes[1]);
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + partes[0]);
				}
				// Compruebo si tengo los datos suficientes para añadir un pais a mi lista de
				// paises
				if (pais != null && continente != null && clima != null && salario != 0 && habitantes != 0
						&& capital != null) {
					listaPaises.add(new Pais(continente, pais, capital, clima, habitantes, salario));
					continente = pais = clima = capital = null;
					salario = habitantes = 0;
				}
			}
			sc.close();
			sc = new Scanner(System.in);
			System.out.println("Bienvenid@! Introduce tus datos.");
			System.out.print("Nombre de usuario: ");
			String nombreUsuario = sc.nextLine();
			System.out.print("Email: ");
			String email = sc.nextLine();
			Usuario usr = new Usuario(nombreUsuario, 22743, email);
			sc.close();
			try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigurarSpring.class)){
				Pais obj = ctx.getBean(Pais.class);
				obj.generaArchivo(listaPaises, usr, pib);
				usr.infoUsuario(usr);
			}catch (BeansException e) {
				System.out.println("Excepcion en el Bean");
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Excepción buscando INFO_PAISES.txt");
			e.printStackTrace();
		}
	}
}
