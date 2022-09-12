import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Provincia extends Capital implements Salida{

	private String nombreProvincia;

	public Provincia(String nombreProvincia, String nombreCapital) {
		super(nombreCapital);
		this.nombreProvincia = nombreProvincia;
	}
	
	public String getNombreProvincia() {
		return nombreProvincia;
	}

	public void setNombreProvincia(String nombreProvincia) {
		this.nombreProvincia = nombreProvincia;
	}
	public String getNombreCapital() {
		return nombreCapital;
	}

	public void setNombreCapital(String nombreCapital) {
		this.nombreCapital = nombreCapital;
	}

	@Override
	public void generaSalida(List<Provincia> prov, Usuario usr) {
		List<String> jenk = new ArrayList<>();
		
		jenk.add("pipeline{");//{
		jenk.add("	agent any");
		jenk.add("	stages{");//{
		jenk.add("		stage(\"AA15\"){");//{
		jenk.add("			steps{");//{
		jenk.add("				script{");//{
		jenk.add("					println \"" + usr.getNombre() + " con ID " + usr.getId() + " registrado en fecha de " + usr.getFechaLogin() + " \"");
			for(Provincia iter:prov) {
				jenk.add("					println \"" + iter.getNombreProvincia() + " 	" + iter.getNombreCapital() + "\"");
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
