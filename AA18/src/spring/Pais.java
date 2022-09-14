package spring;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class Pais extends Continente {
	private String nombrePais;
	private int numHabitantes;
	private int salarioMinimo;
	private String climaPredominante;
	private String capital;

	public Pais(String nombreContinente, String nombrePais, String capital, String climaPredominante, int numHabitantes,
			int salarioMinimo) {
		super(nombreContinente);
		this.nombrePais = nombrePais;
		this.numHabitantes = numHabitantes;
		this.salarioMinimo = salarioMinimo;
		this.climaPredominante = climaPredominante;
		this.capital = capital;
	}

	public Pais() {
		super();
	}

	public String getNombrePais() {
		return nombrePais;
	}

	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}

	public int getNumHabitantes() {
		return numHabitantes;
	}

	public void setNumHabitantes(int numHabitantes) {
		this.numHabitantes = numHabitantes;
	}

	public int getSalarioMinimo() {
		return salarioMinimo;
	}

	public void setSalarioMinimo(int salarioMinimo) {
		this.salarioMinimo = salarioMinimo;
	}

	public String getClimaPredominante() {
		return climaPredominante;
	}

	public void setClimaPredominante(String climaPredominante) {
		this.climaPredominante = climaPredominante;
	}

	public String getCapital() {
		return this.capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public void generaArchivo(List<Pais> listaPaises, Usuario usr, InterfazLambda pib) {
		for (Pais iter : listaPaises) {
			Path file = Paths.get("info_" + iter.getNombrePais() + ".txt");
			List<String> listaString = new ArrayList<>();
			listaString.add("Información generada por " + usr.getNombreUsuario() + "(ID" + usr.getIdUsuario() + ") "
					+ usr.getEmail());
			listaString.add("Pais: " + iter.getNombrePais());
			listaString.add("Capital: " + iter.getCapital());
			listaString.add("Continente: " + iter.getNombreContinente());
			listaString.add("Habitantes: " + iter.getNumHabitantes());
			listaString.add("Clima: " + iter.getClimaPredominante());
			listaString.add("Salario minimo: " + iter.getSalarioMinimo());
			listaString.add("PIB: " + ((long) pib.ejecutar(iter.getNumHabitantes(), iter.getSalarioMinimo())) + "€");
			try {
				Files.write(file, listaString, StandardCharsets.UTF_8);
			} catch (IOException e) {
				System.out.println("Excepción intentando escibir listaString.");
				e.printStackTrace();
			}
		}

	}

}
