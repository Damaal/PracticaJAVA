package spring;

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

import org.springframework.stereotype.Service;

@Service
public class Cancion{

	private String nombreCancion;
	private int visualizaciones;
	private int anioListado;

	public Cancion(String nombreCancion, int visualizaciones, int anioListado) {
		super();
		this.nombreCancion = nombreCancion;
		this.visualizaciones = visualizaciones;
		this.anioListado = anioListado;
	}

	public Cancion() {

	}

	@Override
	public String toString() {
		return "Cancion [nombreCancion=" + nombreCancion + ", visualizaciones=" + visualizaciones + ", anioListado="
				+ anioListado + "]\n";
	}

	public String getNombreCancion() {
		return nombreCancion;
	}

	public void setNombreCancion(String nombreCancion) {
		this.nombreCancion = nombreCancion;
	}

	public int getVisualizaciones() {
		return visualizaciones;
	}

	public void setVisualizaciones(int visualizaciones) {
		this.visualizaciones = visualizaciones;
	}

	public int getAnioListado() {
		return anioListado;
	}

	public void setAnioListado(int anioListado) {
		this.anioListado = anioListado;
	}

	public void generaArchivo(List<Cancion> canciones, List<Artista> artistas) { // Metodo que creará el ficheo de// salida a partir de una lista de// canciones y una lista de artistas
		List<String> miStream = new ArrayList<>();		
		Usuario usr = new Usuario(8736817, "Danut Marian", "email@email.com");
		miStream.add("Lista de éxitos generada por el usuario " + usr.getUserName() + "(ID" + usr.getId() + ")	 " + usr.getEmail());
		miStream.add("");
		miStream.add("TOP 10 CANCIONES DE 2020 + Recaudación de cada una");
		
		for(int i = 2020; i <= 2021; i++) {
			for(Cancion iter:canciones) {
			if(iter.getAnioListado() == i)
				miStream.add(iter.getNombreCancion() + "	" + iter.ventas() + "€");
			}
			miStream.add("");
			if(i == 2020) miStream.add("TOP 10 CANCIONES DE 2021 + Recaudación de cada una");
		}
		miStream.add("TOP 10 ARTISTAS DE 2020");
		for(int i = 2020; i <= 2021; i++) {
			for(Artista iter:artistas) {
			if(iter.getAnioListado() == i)
				miStream.add(iter.getNombreArtistico());
			}
			miStream.add("");
			if(i == 2020) miStream.add("TOP 10 ARTISTAS DE 2021");
		}
		Path file = Paths.get("artistas_canciones_2020_2021.txt");
		try {
			Files.write(file, miStream, StandardCharsets.UTF_8);
		} catch (IOException e) {
			System.out.println("Excepción escribiendo miString en fichero de salida.");
			e.printStackTrace();
		}
		System.out.println(miStream);
	}

	public List<Cancion> leoArchivosC(List<String> txtCanciones) {
		List<Cancion> listaCanciones = new ArrayList<>();
		int anio = 2020;
		for (String iter : txtCanciones) {
			try {
				Scanner sc = new Scanner(new File(iter), "UTF-8"); // Forzar el set de caracteres
				if (!iter.contains(".txt"))
					System.out.println(iter + " NO es un archivo txt válido.");
				else {// Compruebo que sea TXT
					while (sc.hasNext()) {
						String linea = sc.nextLine();
						if (linea != "") {
							linea = linea.replaceFirst("	", ";");
							// System.out.println(linea);
							String[] partes = linea.split(";");
							partes[0] = partes[0].trim();
							partes[1] = partes[1].trim();
							partes[1] = partes[1].replace(".", "");
							listaCanciones.add(new Cancion(partes[0], Integer.parseInt(partes[1]), anio));
						}
					}
				}
				anio++;
				sc.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		// System.out.println(listaCanciones.size());
		return listaCanciones;
	}

	public List<Artista> leoArchivosA(List<String> archivosArtistas) {
		List<Artista> listaArtistas = new ArrayList<>();
		int anio = 2020;
		for (String iter : archivosArtistas) {
			try {
				Scanner sc = new Scanner(new File(iter), "UTF-8"); // Forzar el set de caracteres
				if (!iter.contains(".txt"))
					System.out.println(iter + " NO es un archivo txt válido.");
				else {// Compruebo que sea TXT
					while (sc.hasNext()) {
						String linea = sc.nextLine();
						linea = linea.trim();
						listaArtistas.add(new Artista(linea, anio));
					}
				}
				anio++;
				sc.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		System.out.println(listaArtistas);
		return listaArtistas;
	}

	
	public long ventas() {
		return (long)2.0*this.visualizaciones;
	}
}
