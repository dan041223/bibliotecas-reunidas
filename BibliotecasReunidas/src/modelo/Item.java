package modelo;

public class Item {
    private String nombreMostradoEnElCombo;
    private int idEnLaTabla;

    public Item() {}
    
    public Item(String nombreMostradoEnElCombo, int idEnLaTabla) {
        this.nombreMostradoEnElCombo = nombreMostradoEnElCombo;
        this.idEnLaTabla = idEnLaTabla;
    }

    public void setNombreMostradoEnElCombo(String nombre) {
		this.nombreMostradoEnElCombo = nombre;
	}

	public void setIdEnLaTabla(int idEnLaTabla) {
		this.idEnLaTabla = idEnLaTabla;
	}

	public String getNombreMostradoEnElCombo() {
        return nombreMostradoEnElCombo;
    }

    public int getIdEnLaTabla() {
        return idEnLaTabla;
    }

    // Sobrescribir toString para que JComboBox muestre el nombre
    @Override
    public String toString() {
        return nombreMostradoEnElCombo;
    }
}