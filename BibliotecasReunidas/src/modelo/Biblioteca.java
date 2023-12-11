package modelo;

public class Biblioteca  {

	private int id;
	private String provincia;
	private int codigo_postal;
	private int telefono;
	private String comunidad_autonoma;
	private String calle;
	
	
	
	public Biblioteca() {
		
	}



	public Biblioteca(int id, String provincia, int codigo_postal, int telefono, String comunidad_autonoma,
			String calle) {
		
		this.id = id;
		this.provincia = provincia;
		this.codigo_postal = codigo_postal;
		this.telefono = telefono;
		this.comunidad_autonoma = comunidad_autonoma;
		this.calle = calle;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getProvincia() {
		return provincia;
	}



	public void setProvincia(String provincia) {
		this.provincia = provincia;
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



	public String getComunidad_autonoma() {
		return comunidad_autonoma;
	}



	public void setComunidad_autonoma(String comunidad_autonoma) {
		this.comunidad_autonoma = comunidad_autonoma;
	}



	public String getCalle() {
		return calle;
	}



	public void setCalle(String calle) {
		this.calle = calle;
	}
	

	
	
	

}
