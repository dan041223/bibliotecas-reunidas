package modelo;

public class Ubicacion {
	
	private int ubicacion;
	private int pasillo;
	private int estanteria;
	private int id_biblioteca;
	
	public Ubicacion() {
		super();
	}
	
	public Ubicacion(int ubicacion, int pasillo, int estanteria, int id_biblioteca) {
		super();
		this.ubicacion = ubicacion;
		this.pasillo = pasillo;
		this.estanteria = estanteria;
		this.id_biblioteca = id_biblioteca;
	}
	
	public int getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(int ubicacion) {
		this.ubicacion = ubicacion;
	}
	public int getPasillo() {
		return pasillo;
	}
	public void setPasillo(int pasillo) {
		this.pasillo = pasillo;
	}
	public int getEstanteria() {
		return estanteria;
	}
	public void setEstanteria(int estanteria) {
		this.estanteria = estanteria;
	}
	public int getId_biblioteca() {
		return id_biblioteca;
	}
	public void setId_biblioteca(int id_biblioteca) {
		this.id_biblioteca = id_biblioteca;
	}
}
