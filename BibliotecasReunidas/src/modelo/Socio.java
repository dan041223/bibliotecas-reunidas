package modelo;

public class Socio  {
	
	private int id;
	private String nombre;
	private String fecha_creacion;
	private String dni;
	private int telefono;
	private String calle;
	private int codigo_postal;
	private String email;
	public enum TIPO_PAGO{
		EFECTIVO,
		TARJETA
	}
	private TIPO_PAGO tipo_pago;
	
	public Socio() {
		super();
	}
	
	public Socio(int id, String nombre, String fecha_creacion, String dni, int telefono, String calle,
			int codigo_postal, String email, TIPO_PAGO tipo_pago) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fecha_creacion = fecha_creacion;
		this.dni = dni;
		this.telefono = telefono;
		this.calle = calle;
		this.codigo_postal = codigo_postal;
		this.email = email;
		this.tipo_pago = tipo_pago;
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
	public String getFecha_creacion() {
		return fecha_creacion;
	}
	public void setFecha_creacion(String fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public TIPO_PAGO getTipo_pago() {
		return tipo_pago;
	}
	public void setTipo_pago(TIPO_PAGO tipo_pago) {
		this.tipo_pago = tipo_pago;
	}
}