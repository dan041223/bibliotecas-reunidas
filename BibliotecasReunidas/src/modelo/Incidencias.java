package modelo;



public class Incidencias {
	
	private int id;
	private int id_socio;
	public String nombre_socio;
	private int id_libro;
	private String nombre_libro;
	private String texto_incidencias;
	public String estadoIncidencia;
	
	public Incidencias() {
		super();
	}
	
	public Incidencias(int id, int id_socio, String nombre_socio, int id_libro, String nombre_libro,
			String texto_incidencias, String estadoIncidencia) {
		super();
		this.id = id;
		this.id_socio = id_socio;
		this.nombre_socio = nombre_socio;
		this.id_libro = id_libro;
		this.nombre_libro = nombre_libro;
		this.texto_incidencias = texto_incidencias;
		this.estadoIncidencia = estadoIncidencia;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_socio() {
		return id_socio;
	}
	public void setId_socio(int id_socio) {
		this.id_socio = id_socio;
	}
	public String getNombre_socio() {
		return nombre_socio;
	}
	public void setNombre_socio(String nombre_socio) {
		this.nombre_socio = nombre_socio;
	}
	public int getId_libro() {
		return id_libro;
	}
	public void setId_libro(int id_libro) {
		this.id_libro = id_libro;
	}
	public String getNombre_libro() {
		return nombre_libro;
	}
	public void setNombre_libro(String nombre_libro) {
		this.nombre_libro = nombre_libro;
	}
	public String getTexto_incidencias() {
		return texto_incidencias;
	}
	public void setTexto_incidencias(String texto_incidencias) {
		this.texto_incidencias = texto_incidencias;
	}
	public String getEstadoIncidencia() {
		return estadoIncidencia;
	}
	public void setEstadoIncidencia(String estadoIncidencia) {
		this.estadoIncidencia = estadoIncidencia;
	}
	
}
