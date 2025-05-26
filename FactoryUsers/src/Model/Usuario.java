package Model;

public class Usuario {

	private int id;
	private String Nombre, Apellido, Email;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getApellido() {
		return Apellido;
	}
	public void setApellido(String apellido) {
		Apellido = apellido;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	
	public Usuario(int id, String nombre, String apellido, String email) {
		this.id = id;
		Nombre = nombre;
		Apellido = apellido;
		Email = email;
	}
}