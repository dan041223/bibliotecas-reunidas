package modelo;

public class Autor {
	
	private int id;
	private String nombre;
	private String nacionalidad;
	private String fecha_nacimiento;
	
	
	public Autor() {
		
		id = 0;
		nombre = "";
		nacionalidad = "";
		fecha_nacimiento = "";
		
	}


	public Autor(int id, String nombre, String nacionalidad, String fecha_nacimiento) {
		
		this.id = id;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.fecha_nacimiento = fecha_nacimiento;
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


	public String getNacionalidad() {
		return nacionalidad;
	}


	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}


	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}


	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}
	

}
