package modelo;



public class Prestamo  {
	private int id;
	private int id_socio;
	private int id_libro;
	private int id_usuario;
	private String fecha_prestamo;
	private String fecha_prevista;
	private String fecha_entrega;
	
	public Prestamo() {
		this.id = 0;
		this.id_socio = 0;
		this.id_libro = 0;
		this.id_usuario = 0;
		this.fecha_prestamo = "";
		this.fecha_prevista = "";
		this.fecha_entrega = "";
	}

	public Prestamo(int id, int id_socio, int id_libro, int id_usuario, String fecha_prestamo, String fecha_prevista,
			String fecha_entrega) {
		this.id = id;
		this.id_socio = id_socio;
		this.id_libro = id_libro;
		this.id_usuario = id_usuario;
		this.fecha_prestamo = fecha_prestamo;
		this.fecha_prevista = fecha_prevista;
		this.fecha_entrega = fecha_entrega;
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

	public int getId_libro() {
		return id_libro;
	}

	public void setId_libro(int id_libro) {
		this.id_libro = id_libro;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getFecha_prestamo() {
		return fecha_prestamo;
	}

	public void setFecha_prestamo(String fecha_prestamo) {
		this.fecha_prestamo = fecha_prestamo;
	}

	public String getFecha_prevista() {
		return fecha_prevista;
	}

	public void setFecha_prevista(String fecha_prevista) {
		this.fecha_prevista = fecha_prevista;
	}

	public String getFecha_entrega() {
		return fecha_entrega;
	}

	public void setFecha_entrega(String fecha_entrega) {
		this.fecha_entrega = fecha_entrega;
	}
}
