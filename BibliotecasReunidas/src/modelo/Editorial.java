package modelo;



public class Editorial  {
	
	private int id;
	private String nombre;
	private int telefono;
	private String calle;
	private int codigo_postal;
	
	
	
	public Editorial() {
		id = 0;
		nombre = "sin_nombre";
		telefono = 0;
		calle = "sin_nombre";
		codigo_postal = 0;
		
	}

	public Editorial(int id, String nombre, int telefono, String calle, int codigo_postal) {
		this.id = id;
		this.nombre = nombre;
		this.telefono = telefono;
		this.calle = calle;
		this.codigo_postal = codigo_postal;
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
	
		

}
