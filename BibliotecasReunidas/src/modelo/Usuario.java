package modelo;

public class Usuario {

	private int id;
	private String nombre;
	private int telefono;
	private String email;
	private String calle;
	private int codigo_postal;
	private String dni;

	public enum TIPO_PERFIL {
		ADMINISTRADOR,
		ADMINISTRATIVO
	}

	private TIPO_PERFIL tipo_perfil;
	private String password;

	public Usuario() {
		super();
	}

	public Usuario(int id, String nombre, int telefono, String email, String calle, int codigo_postal, String dni,
			String password, TIPO_PERFIL tipo_perfil) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.calle = calle;
		this.codigo_postal = codigo_postal;
		this.dni = dni;
		this.tipo_perfil = tipo_perfil;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public int getCodigo_postal() {
		return codigo_postal;
	}

	public void setCodigo_postal(int codigo_postal) {
		this.codigo_postal = codigo_postal;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public TIPO_PERFIL getTipo_perfil() {
		return tipo_perfil;
	}

	public void setTipo_perfil(TIPO_PERFIL tipo_perfil) {
		this.tipo_perfil = tipo_perfil;
	}
}
