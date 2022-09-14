import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
public class Principal {
	public static void main(String[] args) {
		
	
		//Declaro la función lambda
		Funciones funciones_lambda = (min, max) -> ThreadLocalRandom.current().nextInt(min, max+1);
		//Establezco los requisitos de la generación del aleatorio
		int maximo = 1000000;
		int minimo = 1;
		//genero el numero aleatorio
		int numAleatorio = funciones_lambda.generaAleatorio(minimo, maximo);
		//Establezco el numero de intentos
		int numIntentos = 5;
		//Empieza el juego
		Scanner sc = new Scanner(System.in);
		System.out.println("Bienvenid@ al juego del azar absoluto. Introduce tus datos."+numAleatorio);
		System.out.print("Nombre: ");
		String nombre = sc.nextLine();
		System.out.print("Email: ");
		String email = sc.nextLine();
		Usuario usr = new Usuario(nombre, 1, email);
		System.out.println("Bueno " + usr.getNombre() + " tienes 5 intentos para adivinar un número aleatorio entre 1 y 1.000.000");
		System.out.println("¿1.000.000?");
		System.out.println("¡1.000.000! Suerte...");
		for(int i = 0; i < numIntentos; i++) {
			switch(i) {
			case 0:
				System.out.print("Primer intento, este es para calentar: ");
				break;
			case 1:
				System.out.print("Segundo intento, no pierdas la esperanza: ");
				break;
			case 2:
				System.out.print("Tercer intento, esta es la buena: ");
				break;
			case 3:
				System.out.print("Dos intentos quedan: ");
				break;
			case 4:
				System.out.print("Ultimo intento ._. : ");
			default:
					
			}
			int opcion = sc.nextInt();
			if(opcion == numAleatorio) {
				System.out.println("ACERTASTE, qué pena que no haya ningún premio...");
				i = 5; //para romper ejecución
			}
			else {
				System.out.println("Ese no es. Ánimo, ya solo quedan " + (1000000-(i+1)) + " posibilidades.");
			}
			
			
		}
		System.out.print("¡Gracias por participar!");
	}
	
}
