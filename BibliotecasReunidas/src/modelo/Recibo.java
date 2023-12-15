package modelo;



public class Recibo  {
	private int id;
	private int id_socio;
	private int id_libro;
	private int monto;
	private String fech_recibo;
	public enum tipoPago{
		efectivo,
		tarjeta
	}
	private tipoPago pago;
	
	public Recibo() {
		this.id = 0;
		this.id_socio = 0;
		this.id_libro = 0;
		this.monto = 0;
		this.fech_recibo = "";
		this.pago = tipoPago.efectivo;
	}
	
	public Recibo(int id,  int id_socio, int id_libro, int monto, String fech_recibo, tipoPago pago) {
		this.id = id;
		this.id_libro = id_libro;
		this.id_socio = id_socio;
		this.monto = monto;
		this.fech_recibo = fech_recibo;
		this.pago = pago;
	}
	
	public String obtenerTipoPagoComoString() {
        return pago.name();
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_libro() {
		return id_libro;
	}

	public void setId_libro(int id_libro) {
		this.id_libro = id_libro;
	}

	public int getId_socio() {
		return id_socio;
	}

	public void setId_socio(int id_socio) {
		this.id_socio = id_socio;
	}

	public int getMonto() {
		return monto;
	}

	public void setMonto(int monto) {
		this.monto = monto;
	}

	public String getFech_recibo() {
		return fech_recibo;
	}

	public void setFech_recibo(String fech_recibo) {
		this.fech_recibo = fech_recibo;
	}

	public tipoPago getPago() {
		return pago;
	}

	public void setPago(tipoPago pago) {
		this.pago = pago;
	}
}
