
public class Pelicula {
	private String nombre;
	private long recaudacion;
	@Override
	public String toString() {
		return "Pelicula [nombre=" + nombre + ", recaudacion=" + recaudacion + "]";
	}
	public Pelicula(String nombre, long recaudacion) {
		super();
		this.nombre = nombre;
		this.recaudacion = recaudacion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public long getRecaudacion() {
		return recaudacion;
	}
	public void setRecaudacion(int recaudacion) {
		this.recaudacion = recaudacion;
	}
}
