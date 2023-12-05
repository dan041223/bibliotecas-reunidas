package modelo;

public class Libro {
	private int id;
	private String titulo;
	public enum CategoriaLibro {
	    romance,
	    drama,
	    terror,
	    suspense,
	    ciencia_ficcion,
	    poesia,
	    literatura_infantil,
	    aventura,
	    historia,
	    geografia,
	    otros
	}
	private CategoriaLibro categoria;
	private String idioma;
	private String fecha_publicacion;
	private int id_editorial;
	private int id_ubicacion;
	private long isbn;
	
	public Libro() {
		this.id = 0;
		this.titulo = "";
		this.categoria = CategoriaLibro.otros;
		this.idioma = "";
		this.fecha_publicacion ="";
		this.id_editorial = 0;
		this.id_ubicacion = 0;
		this.isbn = 0;
	}

	public Libro(int id, String titulo, CategoriaLibro categoria, String idioma, String fecha_publicacion,
			int id_editorial, int id_ubicacion, int isbn) {
		this.id = id;
		this.titulo = titulo;
		this.categoria = categoria;
		this.idioma = idioma;
		this.fecha_publicacion = fecha_publicacion;
		this.id_editorial = id_editorial;
		this.id_ubicacion = id_ubicacion;
		this.isbn = isbn;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public CategoriaLibro getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaLibro categoria) {
		this.categoria = categoria;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getFecha_publicacion() {
		return fecha_publicacion;
	}

	public void setFecha_publicacion(String fecha_publicacion) {
		this.fecha_publicacion = fecha_publicacion;
	}

	public int getId_editorial() {
		return id_editorial;
	}

	public void setId_editorial(int id_editorial) {
		this.id_editorial = id_editorial;
	}

	public int getId_ubicacion() {
		return id_ubicacion;
	}

	public void setId_ubicacion(int id_ubicacion) {
		this.id_ubicacion = id_ubicacion;
	}

	public long getIsbn() {
		return isbn;
	}

	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}
}
