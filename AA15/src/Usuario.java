import java.time.LocalDate;

public class Usuario {
	private String nombre;
	private String id;
	private LocalDate fechaLogin;
	
	public Usuario(String nombre, String id, LocalDate fechaLogin) {
		super();
		this.nombre = nombre;
		this.id = id;
		this.fechaLogin = fechaLogin;
	}
	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", id=" + id + ", fechaLogin=" + fechaLogin + "]";
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public LocalDate getFechaLogin() {
		return fechaLogin;
	}
	public void setFechaLogin(LocalDate fechaLogin) {
		this.fechaLogin = fechaLogin;
	}
	
	
}
