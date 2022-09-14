package spring;



import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectosLog {

	@After("execution(* generaArchivo(*,*,*))")
	public void log() {
		System.out.println("Se ha generado los archivos con la información de los paises");
	}

	
}
