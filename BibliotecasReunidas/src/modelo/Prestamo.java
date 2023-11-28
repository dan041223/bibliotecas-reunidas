package modelo;



public class Prestamo  {
	private int id;
	private int idSocio;
	private int idLibro;
	private int idUsuario;
	private String fechaPrestamo;
	private String fechaPrevista;
	private String fechaEntrega;
	
	public Prestamo() {
		this.id = 0;
		this.idSocio = 0;
		this.idLibro = 0;
		this.idUsuario = 0;
		this.fechaPrestamo = "";
		this.fechaPrevista = "";
		this.fechaEntrega = "";
	}
	
	public Prestamo(int id, int idSocio, int idLibro, int idUsuario, String fechaPrestamo, String fechaPrevista,
			String fechaEntrega) {
		this.id = id;
		this.idSocio = idSocio;
		this.idLibro = idLibro;
		this.idUsuario = idUsuario;
		this.fechaPrestamo = fechaPrestamo;
		this.fechaPrevista = fechaPrevista;
		this.fechaEntrega = fechaEntrega;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdSocio() {
		return idSocio;
	}

	public void setIdSocio(int idSocio) {
		this.idSocio = idSocio;
	}

	public int getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getFechaPrestamo() {
		return fechaPrestamo;
	}

	public void setFechaPrestamo(String fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}

	public String getFechaPrevista() {
		return fechaPrevista;
	}

	public void setFechaPrevista(String fechaPrevista) {
		this.fechaPrevista = fechaPrevista;
	}

	public String getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(String fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
}
