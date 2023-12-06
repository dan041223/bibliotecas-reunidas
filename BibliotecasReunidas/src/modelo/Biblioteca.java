package modelo;

public class Biblioteca  {

	private int id;
	private String comunidad_autonoma;
	private String provincia;
	private String calle;
	private int codigo_postal;
	private int telefono;
	
	
	public Biblioteca() {
		id = 0;
		comunidad_autonoma = "nada";
		provincia = "a";
		calle = "sin_calle";
		codigo_postal = 0;
		telefono = 0;
	}

	public Biblioteca(int id, String comunidad_autonoma, String provincia, String calle, int codigo_postal, int telefono) {
		this.id = id;
		this.comunidad_autonoma = comunidad_autonoma;
		this.provincia = provincia;
		this.calle = calle;
		this.codigo_postal = codigo_postal;
		this.telefono = telefono;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComunidad_autonoma() {
		return comunidad_autonoma;
	}

	public void setComunidad_autonoma(String comunidad_autonoma) {
		this.comunidad_autonoma = comunidad_autonoma;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
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

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	
	

}
