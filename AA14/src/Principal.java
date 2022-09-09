import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/*
 * (!) AA14 (!)
ESTRUCTURA DEL PROGRAMA:
- Proyecto Maven (Lo van a necesitar para el JSON)
- Herencia entre clases
- Implementación de una interfaz
- Conectividad con Github
- JSON / API
- Implementar un jenkinsfile

PROBLEMATICA:
Nos contrataron desde la empresa Charlie y la fabrica de chocolate situados en Sevilla para ayudarlos a mejorar su sistema de generación de chocolates. Para esto, el cliente nos dio algunas
especificaciones. Al ser una fabrica de chocolates, tienen en consideración las condiciones climaticas, ya que si la temperatura actual es mayor a 40° no se fabrican
chocolates ese día dado que existe un riesgo de que el mismo se derrita. Si las condiciones climaticas son favorables la producción se hace habitualmente.
Para informar al cliente de como esta yendo el proceso, nos pidio que se lo informemos a traves de un archivo plano. Informandole por producto las cantidades generadas
diariamente. 

ESPECIFICACIONES TECNICAS:
- El programa debe conectar con Github y dejar el codigo en la rama de Desarrollo
- Obtener la información del clima a traves de la API del sitio https://www.el-tiempo.net/ HECHO
- La interfaz debe implementar el metodo produccionActiva() que es la que indica si ese día se produce chocolate o no. HECHO
- Clase Chocolate que herede de la clase Golosina con sus respectivos atributos. HECHO
- El jenkinsfile debe mostrar la información por consola de los chocolates generados. 

ENTREGABLES:
- Codigo del proyecto
- Captura de consola de jenkins
- Captura de consola de Java. La consola de Java debe informar si se puede producir chocolate o no, y en el caso de poderse, informar que fue lo que se produjo
el día de hoy. 

SET DE DATOS:
Nombre					CANTIDAD PRODUCIDA
- Chocolate Blanco			1000
- Choclate Negro				1500
- Chocolate con almendras		1200
- Chocolate con castañas de caju	1300
- Chocolate en rama			100
- Chocolate con 70% de cacao		1500
 */
public class Principal {

	public static void main(String[] args) {
		List<Chocolate> listaProduccion = new ArrayList<>();
		Chocolate prueba = new Chocolate(null, null, 0);
		if(prueba.produccionActiva()) {//La temperatura es adecuada para producir chocolate
			System.out.println("Temperatura adecuada en Sevilla. ¡¡Arrancando la maquinaria!!");
			listaProduccion.add(new Chocolate("Chocolate", "Blanco", 1000));
			listaProduccion.add(new Chocolate("Chocolate", "Negro", 1500));
			listaProduccion.add(new Chocolate("Chocolate", "con almendras", 1200));
			listaProduccion.add(new Chocolate("Chocolate", "con castañas caju", 1300));
			listaProduccion.add(new Chocolate("Chocolate", "en Rama", 100));
			listaProduccion.add(new Chocolate("Chocolate", "70% Cacao", 1500));
			for(Chocolate iter : listaProduccion) {
				System.out.println(iter.getNombre() + " " + iter.getTipo() + "		" + iter.getCantidadProducida());
			}
			creaJenkins(listaProduccion);
		}
		else {//la temperatura para producir chocolate no es correcta
			System.out.println("Hoy no se puede producir chocolate debido a las altas temperaturas :(");
		}
		

	}
	public static void creaJenkins(List<Chocolate> choco) {
		List<String> jenk = new ArrayList<>();
		jenk.add("import java.time.LocalDate");
		jenk.add("pipeline{");//{
		jenk.add("	agent any");
		jenk.add("	stages{");//{
		jenk.add("		stage(\"holaMundo\"){");//{
		jenk.add("			steps{");//{
		jenk.add("				script{");//{
		jenk.add("					println \"¡Hola, hoy es un dia espectacular para producir chocolate! Mira lo que se ha producido: \"");
			for(Chocolate iter:choco) {
				jenk.add("					println \"" + iter.getNombre() + " " + iter.getTipo() + "	" + iter.getCantidadProducida() + "\"");
			}
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
