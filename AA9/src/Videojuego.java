
public class Videojuego implements Ventas{

	private String titulo;
	private int ventas;
	private double precio;
	
	public Videojuego(String titulo, int ventas, double precio) {
		this.titulo = titulo;
		this.ventas = ventas;
		this.precio = precio;
	}
	
	@Override
	public String toString() {
		return "Videojuego [titulo=" + titulo + ", ventas=" + ventas + ", precio=" + precio + "]";
	}

	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getVentas() {
		return ventas;
	}
	public void setVentas(int ventas) {
		this.ventas = ventas;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	@Override
	public double beneficioVenta(int numVentas, double precioUnidad) {
		return this.ventas * this.precio;
	}
	
}
