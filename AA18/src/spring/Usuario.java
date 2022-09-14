package spring;

public class Usuario implements Archivo {
	private String nombreUsuario;
	private int idUsuario;
	private String email;

	public Usuario(String nombreUsuario, int idUsuario, String email) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.idUsuario = idUsuario;
		this.email = email;
	}

	@Override
	public String toString() {
		return "Usuario [nombreUsuario=" + nombreUsuario + ", idUsuario=" + idUsuario + ", email=" + email + "]";
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public void infoUsuario(Usuario usr) {
		System.out.println("El usuario " + usr.getNombreUsuario() + "(ID" + usr.getIdUsuario()
				+ ") ha generado los txt con info de todos los paises.");
	}

}
