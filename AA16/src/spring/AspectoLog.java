package spring;


import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectoLog 
{
	@Before("execution(* generaArchivo(*, *))")
	public void log1()
	{
		System.out.println("Vas a generar la salida del programa...");
		
	}
	
	@Before("execution(* leoArchivos*(*))")
	public void log2() {
		System.out.println("Comprobando archivos TXT...");
		/*for(String iter:archivos) {
			if(iter.contains(".txt")) System.out.print(iter + " es correcto.");
			else System.out.print(iter + " NO es correcto");
		}*/
	}
	
	@After("execution(* generaArchivo(*, *))")
	public void log3() {
		System.out.println("Se ha generado la salida del programa.");
	}
}
