package spring;

public class Artista extends Persona{
	private String nombreArtistico;
	private String generoMusical;
	private int anioListado;
	
	public Artista(String nombreArtistico, int anioListado) {
		super();
		this.nombreArtistico = nombreArtistico;
		this.anioListado = anioListado;
	}
	public Artista() {
		
	}
	@Override
	public String toString() {
		return "Artista [nombreArtistico=" + nombreArtistico + ", generoMusical=" + generoMusical + ", anioListado="
				+ anioListado + "]";
	}
	public String getNombreArtistico() {
		return nombreArtistico;
	}
	public void setNombreArtistico(String nombreArtistico) {
		this.nombreArtistico = nombreArtistico;
	}
	public String getGeneroMusical() {
		return generoMusical;
	}
	public void setGeneroMusical(String generoMusical) {
		this.generoMusical = generoMusical;
	}
	public int getAnioListado() {
		return this.anioListado;
	}
	public void setAnioListado(int anioListado) {
		this.anioListado = anioListado;
	}
	
	
	
}
