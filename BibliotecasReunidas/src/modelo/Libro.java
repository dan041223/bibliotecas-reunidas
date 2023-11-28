package modelo;

public class Libro {
	private int id;
	private String titulo;
	public enum CategoriaLibro {
	    ROMANCE,
	    DRAMA,
	    TERROR,
	    SUSPENSE,
	    CIENCIA_FICCION,
	    POESIA,
	    LITERATURA_INFANTIL,
	    AVENTURA,
	    HISTORIA,
	    GEOGRAFIA,
	    OTRO
	}
	private CategoriaLibro categoria;
	private String idioma;
	private String fechaPublicacion;
	private int idEditorial;
	private int idUbicacion;
	private int isbn;
	
	public Libro() {
		this.id = 0;
		this.titulo = "";
		this.categoria = CategoriaLibro.OTRO;;
		this.idioma = "";
		this.fechaPublicacion ="";
		this.idEditorial = 0;
		this.idUbicacion = 0;
		this.isbn = 0;
	}
	
	public Libro(int id, String titulo, CategoriaLibro categoria, String idioma, String fechaPublicacion, int idEditorial,
			int idUbicacion, int isbn) {
		this.id = id;
		this.titulo = titulo;
		this.categoria = categoria;
		this.idioma = idioma;
		this.fechaPublicacion = fechaPublicacion;
		this.idEditorial = idEditorial;
		this.idUbicacion = idUbicacion;
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

	public String getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(String fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public int getIdEditorial() {
		return idEditorial;
	}

	public void setIdEditorial(int idEditorial) {
		this.idEditorial = idEditorial;
	}

	public int getIdUbicacion() {
		return idUbicacion;
	}

	public void setIdUbicacion(int idUbicacion) {
		this.idUbicacion = idUbicacion;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
}
