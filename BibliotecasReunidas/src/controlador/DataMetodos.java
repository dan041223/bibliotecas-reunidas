package controlador;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Autor;
import modelo.Biblioteca;
import modelo.Libro;
import modelo.Libro.CategoriaLibro;

public class DataMetodos {

//============================= Metodos del Jpanel Autor ==============================================
	public static ArrayList<Autor> LeerTablaAutor() {

		ConectorBBDD conextor = new ConectorBBDD();

		ArrayList<Autor> arrlAutor = new ArrayList<>();

		Statement statement = null;
		ResultSet registro = null;
		Connection conexion = null;

		try {
			conexion = conextor.connect();
			statement = conexion.createStatement();
			String query = "Select * from Autor order by id_autor limit 100"; // porque son los primeros 100 libros que
																		// tenemos en nuestra
			// base de datos
			registro = statement.executeQuery(query);

			while (registro.next()) {

				Autor autor = new Autor();
				autor.setId(registro.getInt("id_autor"));
				autor.setNombre(registro.getString("nombre"));
				autor.setNacionalidad(registro.getString("nacionalidad"));
				autor.setFecha_nacimiento(registro.getString("fecha_nacimiento"));

				arrlAutor.add(autor);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Error al cerrar.\n");
			} catch (NullPointerException e) {

			}
		}

		return arrlAutor;

	}

	// recogemos el arrayList de autores y luego lo metemos en un arrayList de
	// Objetos
	public static ArrayList<Object[]> obtenerFilasTablaAutor() {

		ArrayList<Autor> autores = LeerTablaAutor();

		ArrayList<Object[]> arrlAutores = new ArrayList<>();

		for (Autor autor : autores) {

			Object[] fila = new Object[] { autor.getId(), autor.getNombre(), autor.getNacionalidad(),
					autor.getFecha_nacimiento() };
			arrlAutores.add(fila);
		}

		return arrlAutores;
	}

	public static boolean modificarTablaAutor(int id, String nombreNuevo, String nacionalidadNueva, String fechaNueva) {
		ConectorBBDD conextor = new ConectorBBDD();

		boolean actualizado = false;
		PreparedStatement preparedStatement = null;
		Connection conexion = null;

		try {

			conexion = conextor.connect();

			String query = "update autor set nombre=?, nacionalidad=?, fecha_nacimiento=? where id_autor=?";

			preparedStatement = conexion.prepareStatement(query);

			preparedStatement.setString(1, nombreNuevo);
			preparedStatement.setString(2, nacionalidadNueva);

			Date date = Date.valueOf(fechaNueva);
			preparedStatement.setDate(3, date);
			preparedStatement.setInt(4, id);

			int contador = preparedStatement.executeUpdate();

			if (contador > 0) {
				JOptionPane.showMessageDialog(null, "La Fila se ha actualizado correctamente",
						"Confirmación de los cambios", JOptionPane.INFORMATION_MESSAGE);
				actualizado = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("No se ha podido conectar con la BBDD.\n");
		} finally {
			try {
				preparedStatement.close();
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Error al cerrar.\n");
			} catch (NullPointerException e) {

			}
		}
		return actualizado;
	}

	public static ArrayList<Autor> filtraPorCampos(String id, String nombre, String nacionalidad,
			String fecha_nacimiento) {

		// trim sirve para quitar espacios a la derecha e izquierda
		id = id.trim();
		nombre = nombre.trim();
		nacionalidad = nacionalidad.trim();
		fecha_nacimiento = fecha_nacimiento.trim();

		ConectorBBDD conextor = new ConectorBBDD();

		ArrayList<Autor> autores = new ArrayList<>();

		PreparedStatement preparedStatement = null;
		ResultSet registro = null;
		Connection conexion = null;

		boolean idTieneValores = !id.equals("");// que NO este vacio = TIENE VALORES
		boolean nombreTieneValores = !nombre.equals(""); // que NO este vacio = TIENE VALORES
		boolean nacionalidadTieneValores = !nacionalidad.equals(""); // que NO este vacio = TIENE VALORES
		boolean fechaDeNacimientoTieneValores = !fecha_nacimiento.equals(""); // que NO este vacio = TIENE VALORES

		try {
			conexion = conextor.connect();
			String query = "Select id_autor, nombre, nacionalidad, fecha_nacimiento from Autor where 1=1";

			if (idTieneValores) {
				query = query + " and id_autor = ?";
			}

			// UPPER - sirve para convertir el valor del campo en Mayuscula
			// LOWER - sirve para convertir el valor del campor en minuscula
			// si el campo de texto tiene contenido = que no este vacio
			if (nombreTieneValores) {
				query = query + " and upper(nombre) like ?";
			}

			// si el campo de texto tiene contenido = que no este vacio
			if (nacionalidadTieneValores) {
				query = query + " and upper(nacionalidad) like ?";
			}

			if (fechaDeNacimientoTieneValores) {
				query = query + " and fecha_nacimiento=?";
			}

			preparedStatement = conexion.prepareStatement(query);

			int indice = 1;
			if (idTieneValores) {
				preparedStatement.setInt(indice, Integer.parseInt(id));
				indice++;
			}

			if (nombreTieneValores) {
				preparedStatement.setString(indice, "%" + nombre.toUpperCase() + "%");
				indice++;
			}

			if (nacionalidadTieneValores) {
				preparedStatement.setString(indice, "%" + nacionalidad.toUpperCase() + "%");
				indice++;
			}

			if (fechaDeNacimientoTieneValores) {
				Date date = Date.valueOf(fecha_nacimiento);
				preparedStatement.setDate(indice, date);
				indice++;
			}
			// base de datos
			registro = preparedStatement.executeQuery();

			while (registro.next()) {
				Autor autor = new Autor();
				autor.setId(registro.getInt("id_autor"));
				autor.setNombre(registro.getString("nombre"));
				autor.setNacionalidad(registro.getString("nacionalidad"));
				autor.setFecha_nacimiento(registro.getString("fecha_nacimiento"));
				autores.add(autor);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Error al cerrar.\n");
			} catch (NullPointerException e) {

			}
		}

		return autores;
	}

	/// Crear autor nuevo
	public static void insertarAutor(String nombre, String nacionalidad, String fecha_nacimiento) {

		ConectorBBDD conextor = new ConectorBBDD();

		PreparedStatement preparedStatement = null;
		Connection conexion = null;

		try {

			conexion = conextor.connect();

			String query = String.format("insert into autor (nombre, nacionalidad, fecha_nacimiento)values(?,?,?);");

			preparedStatement = conexion.prepareStatement(query);

			preparedStatement.setString(1, nombre);
			preparedStatement.setString(2, nacionalidad);

			Date date = Date.valueOf(fecha_nacimiento);
			preparedStatement.setDate(3, date);

			int contador = preparedStatement.executeUpdate();

			if (contador > 0) {
				JOptionPane.showMessageDialog(null, "La Fila se ha insertado correctamente",
						"Confirmación de los inserción", JOptionPane.INFORMATION_MESSAGE);
			}

			System.out.println("Inserción exitosa.");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Error al cerrar.\n");
			} catch (NullPointerException e) {

			}
		}

	}

	// Eliminar fisico

	public static void eliminarAutor(int id) {

		ConectorBBDD conextor = new ConectorBBDD();

		Statement statement = null;
		Connection conexion = null;

		try {
			int confirmacion = JOptionPane.showConfirmDialog(null, "Esta seguro que desea eliminar la fila ??",
					"Confirmación de eliminacion ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (confirmacion == JOptionPane.YES_OPTION) {
				conexion = conextor.connect();
				statement = conexion.createStatement();
				System.out.println(id);
				String query = String.format("delete from autor where id_autor = %d;", id);
				int count = statement.executeUpdate(query);// esta funcion devuelve el numero de filas que han sido
															// afectadas

				if (count > 0) {
					JOptionPane.showMessageDialog(null, "El proceso de eliminacion ha terminado correctamente",
							"Confirmación de eliminacion", JOptionPane.INFORMATION_MESSAGE);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("No se ha podido conectar con la BBDD.\n");
		} finally {
			try {
				statement.close();
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Error al cerrar.\n");
			} catch (NullPointerException e) {

			}

		}
	}

//====================================Aqui comienzan los metodos de Libros ============================

	public static ArrayList<Libro> LeerTablaLibro() {

		ConectorBBDD conextor = new ConectorBBDD();

		ArrayList<Libro> arrlLibro = new ArrayList<>();

		Statement statement = null;
		ResultSet registro = null;
		Connection conexion = null;

		try {
			conexion = conextor.connect();
			statement = conexion.createStatement();
			String query = "Select * from libros order by id limit 100"; // porque son los primeros 100 libros que
																			// tenemos en nuestra
			// base de datos
			registro = statement.executeQuery(query);

			while (registro.next()) {

				Libro libro = new Libro();
				libro.setId(registro.getInt("id_libro"));
				libro.setTitulo(registro.getString("titulo"));
				libro.setCategoria(obtenerCategoriaLibro(registro.getString("categoria")));
				libro.setIdioma(registro.getString("idioma"));
				libro.setFecha_publicacion(registro.getString("fecha_publicacion"));
				libro.setId_editorial(registro.getInt("id_editorial"));
				libro.setId_ubicacion(registro.getInt("id_ubicacion"));
				libro.setIsbn(registro.getInt("isbn"));
				arrlLibro.add(libro);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Error al cerrar.\n");
			} catch (NullPointerException e) {

			}
		}

		return arrlLibro;

	}

	public static CategoriaLibro obtenerCategoriaLibro(String categoria) {
		/*
		 * ROMANCE, DRAMA, TERROR, SUSPENSE, CIENCIA_FICCION, POESIA,
		 * LITERATURA_INFANTIL, AVENTURA, HISTORIA, GEOGRAFIA, OTRO
		 * 
		 */
		CategoriaLibro result = null;

		switch (categoria.toUpperCase()) {
		case "ROMANCE":
			result = CategoriaLibro.romance;
			break;

		case "DRAMA":
			result = CategoriaLibro.drama;
			break;

		case "TERROR":
			result = CategoriaLibro.terror;
			break;

		case "SUSPENSE":
			result = CategoriaLibro.suspense;
			break;

		case "CIENCIA_FICCION":
			result = CategoriaLibro.ciencia_ficcion;
			break;

		case "POESIA":
			result = CategoriaLibro.poesia;
			break;

		case "LITERATURA_INFANTIL":
			result = CategoriaLibro.literatura_infantil;
			break;

		case "AVENTURA":
			result = CategoriaLibro.aventura;
			break;

		case "HISTORIA":
			result = CategoriaLibro.historia;
			break;

		case "GEOGRAFIA":
			result = CategoriaLibro.geografia;
			break;

		case "OTROS":
			result = CategoriaLibro.otros;
			break;

		default:
			result = CategoriaLibro.otros;
		}
		return result;

	}

	public static ArrayList<Object[]> obtenerFilasTablaLibro() {

		ArrayList<Libro> libros = LeerTablaLibro();

		ArrayList<Object[]> arrlLibros = new ArrayList<>();

		for (Libro libro : libros) {

			Object[] fila = new Object[] { libro.getId(), libro.getTitulo(), libro.getCategoria(), libro.getIdioma(),
					libro.getFecha_publicacion(), libro.getId_editorial(), libro.getId_ubicacion(), libro.getIsbn() };
			arrlLibros.add(fila);
		}

		return arrlLibros;
	}

	/// Crear libro nuevo
	public static void insertarLibro(String titulo, String categoria, String idioma, String fecha_publicacion,
			int id_editorial, int id_ubicacion, int isbn) {

		ConectorBBDD conextor = new ConectorBBDD();
		
		PreparedStatement preparedStatement = null;
		Connection conexion = null;

		try {

			conexion = conextor.connect();

			String query = "insert into libros (titulo, categoria, idioma, fecha_publicacion,id_editorial, id_ubicacion,\"ISBN\")values(?,?,?,?,?,?,?);";

			preparedStatement = conexion.prepareStatement(query);

			preparedStatement.setString(1, titulo);
			preparedStatement.setObject(2, obtenerCategoriaLibro(categoria), java.sql.Types.OTHER);// java.sql.Types.OTHER
																									// indica que
																									// queremos insertar
																									// un tipo de dato
																									// personalizado
			preparedStatement.setString(3, idioma);

			Date date = Date.valueOf(fecha_publicacion);
			preparedStatement.setDate(4, date);

			preparedStatement.setInt(5, id_editorial);
			preparedStatement.setInt(6, id_ubicacion);
			preparedStatement.setLong(7, isbn);

			int contador = preparedStatement.executeUpdate();

			if (contador > 0) {
				JOptionPane.showMessageDialog(null, "La Fila se ha insertado correctamente",
						"Confirmación de los inserción", JOptionPane.INFORMATION_MESSAGE);
			}

			System.out.println("Inserción exitosa.");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Error al cerrar.\n");
			} catch (NullPointerException e) {

			}
		}

	}

	// Buscar por todos los campos
	public static ArrayList<Libro> filtraPorCamposLibros(String id, String titulo, String categoria, String idioma,
			String fecha_publicacion, String id_editorial, String id_ubicacion, String isbn) {

		// trim sirve para quitar espacios a la derecha e izquierda
		id = id.trim();
		titulo = titulo.trim();
		categoria = categoria.trim();
		idioma = idioma.trim();
		fecha_publicacion = fecha_publicacion.trim();
		id_editorial = id_editorial.trim();
		id_ubicacion = id_ubicacion.trim();
		isbn = isbn.trim();

		ConectorBBDD conextor = new ConectorBBDD();

		ArrayList<Libro> libros = new ArrayList<>();

		PreparedStatement preparedStatement = null;
		ResultSet registro = null;
		Connection conexion = null;

		boolean idTieneValores = !id.equals("");// que NO este vacio = TIENE VALORES
		boolean tituloTieneValores = !titulo.equals(""); // que NO este vacio = TIENE VALORES
		boolean categoriaTieneValores = !categoria.equals("");
		boolean idiomaTieneValor = !idioma.equals("");
		boolean fechaTieneValores = !fecha_publicacion.equals("");
		boolean id_editorialTieneValores = !id_editorial.equals("");
		boolean id_ubicacionTieneValores = !id_ubicacion.equals("");
		boolean isbnTieneValores = !isbn.equals("");

		try {
			conexion = conextor.connect();

			String query = "Select id_libro, titulo, categoria, idioma, fecha_publicacion,id_editorial, id_ubicacion,\"ISBN\" from libros where 1=1";

			if (idTieneValores) {
				query = query + " and id_libro = ?";
			}

			// UPPER - sirve para convertir el valor del campo en Mayuscula
			// LOWER - sirve para convertir el valor del campor en minuscula
			// si el campo de texto tiene contenido = que no este vacio
			if (tituloTieneValores) {
				query = query + " and upper(titulo) like ?";
			}

			// si el campo de texto tiene contenido = que no este vacio
			if (categoriaTieneValores) {
				query = query + " and upper(categoria) like ?";
			}

			if (idiomaTieneValor) {
				query = query + " and upper(idioma) like ? ";
			}

			if (fechaTieneValores) {
				query = query + " and fecha_publicacion=?";
			}
			if (id_editorialTieneValores) {
				query = query + " and id_editorial =?";
			}
			if (id_ubicacionTieneValores) {
				query = query + " and id_ubicacion = ? ";
			}
			if (isbnTieneValores) {
				query = query + " and \"ISBN\" = ?";
			}

			preparedStatement = conexion.prepareStatement(query);

			int indice = 1;
			if (idTieneValores) {
				preparedStatement.setInt(indice, Integer.parseInt(id));
				indice++;
			}

			if (tituloTieneValores) {
				preparedStatement.setString(indice, "%" + titulo.toUpperCase() + "%");
				indice++;
			}

			if (categoriaTieneValores) {
				preparedStatement.setString(indice, "%" + categoria.toUpperCase() + "%");
				indice++;
			}
			if (idiomaTieneValor) {
				preparedStatement.setString(indice, "%" + idioma.toUpperCase() + "%");
			}

			if (fechaTieneValores) {
				Date date = Date.valueOf(fecha_publicacion);
				preparedStatement.setDate(indice, date);
				indice++;
			}

			if (id_editorialTieneValores) {
				preparedStatement.setInt(indice, Integer.parseInt(id_editorial));
				indice++;
			}

			if (id_ubicacionTieneValores) {
				preparedStatement.setInt(indice, Integer.parseInt(id_ubicacion));
				indice++;
			}

			if (isbnTieneValores) {
				preparedStatement.setLong(indice, Long.parseLong(isbn));
			}

			// base de datos
			registro = preparedStatement.executeQuery();

			while (registro.next()) {
				Libro libro = new Libro();
				libro.setId(registro.getInt("id_libro"));
				libro.setTitulo(registro.getString("titulo"));
				libro.setCategoria(obtenerCategoriaLibro(registro.getString("categoria")));
				libro.setIdioma(registro.getString("idioma"));
				libro.setFecha_publicacion(registro.getString("fecha_publicacion"));
				libro.setId_editorial(registro.getInt("id_editorial"));
				libro.setId_ubicacion(registro.getInt("id_ubicacion"));
				libro.setIsbn(registro.getLong("ISBN"));
				libros.add(libro);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Error al cerrar.\n");
			} catch (NullPointerException e) {

			}
		}

		return libros;
	}
    public static boolean modificarTablaLibro(int id, String tituloNuevo, String categoriaNueva, String idiomaNuevo,
			String fecha_publicacionNueva, String id_editorialNuevo, String id_ubicacionNuevo, String isbnNuevo ) {
		
		ConectorBBDD conextor = new ConectorBBDD();

		boolean actualizado = false;
		PreparedStatement preparedStatement = null;
		Connection conexion = null;

		try {

			conexion = conextor.connect();

			String query = "update libros set titulo=?, categoria_libro=?, idioma=?, fecha_publicacion=?, id_editorial =?, id_ubicacion=?,\"ISBN\"=? where id_libro=?";
					
			
			preparedStatement = conexion.prepareStatement(query);

			preparedStatement.setString(1, tituloNuevo);
			preparedStatement.setString(2, categoriaNueva);
			preparedStatement.setString(3, idiomaNuevo );

			Date date = Date.valueOf(fecha_publicacionNueva);
			preparedStatement.setDate(4, date);
			
			preparedStatement.setString(5,id_editorialNuevo);
			preparedStatement.setString(6, id_ubicacionNuevo);
			preparedStatement.setString(7,isbnNuevo);
			
			preparedStatement.setInt(8,id);

			int contador = preparedStatement.executeUpdate();

			if (contador > 0) {
				JOptionPane.showMessageDialog(null, "La Fila se ha actualizado correctamente",
						"Confirmación de los cambios", JOptionPane.INFORMATION_MESSAGE);
				actualizado = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("No se ha podido conectar con la BBDD.\n");
		} finally {
			try {
				preparedStatement.close();
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Error al cerrar.\n");
			} catch (NullPointerException e) {

			}
		}
		return actualizado;
	}
	
	public static void eliminarLibro(int id) {

		ConectorBBDD conextor = new ConectorBBDD();

		Statement statement = null;
		Connection conexion = null;

		try {
			int confirmacion = JOptionPane.showConfirmDialog(null, "Esta seguro que desea eliminar la fila ??",
					"Confirmación de eliminacion ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (confirmacion == JOptionPane.YES_OPTION) {
				conexion = conextor.connect();
				statement = conexion.createStatement();
				System.out.println(id);
				String query = String.format("delete from libros where id_libro = %d;", id);
				int count = statement.executeUpdate(query);// esta funcion devuelve el numero de filas que han sido
															// afectadas

				if (count > 0) {
					JOptionPane.showMessageDialog(null, "El proceso de eliminacion ha terminado correctamente",
							"Confirmación de eliminacion", JOptionPane.INFORMATION_MESSAGE);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("No se ha podido conectar con la BBDD.\n");
		} finally {
			try {
				statement.close();
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Error al cerrar.\n");
			} catch (NullPointerException e) {

			}

		}
	}    

//====================================Aqui comienzan los metodos de Biblioteca ============================
	
	//Metodo para leer todos los datos de la tabla biblioteca
	public static ArrayList<Biblioteca> LeerTablaBiblioteca() {

		ConectorBBDD conextor = new ConectorBBDD();

		ArrayList<Biblioteca> arrlBiblio = new ArrayList<>();

		Statement statement = null;
		ResultSet registro = null;
		Connection conexion = null;
		
		try {
			conexion = conextor.connect();
			statement = conexion.createStatement();
			String query = "Select * from biblioteca order by id_biblioteca"; 
																		// tenemos en nuestra
			// base de datos
			registro = statement.executeQuery(query);

			while (registro.next()) {

				Biblioteca biblioteca = new Biblioteca();
				biblioteca.setId(registro.getInt("id_biblioteca"));
				biblioteca.setComunidad_autonoma(registro.getString("comunidad_autonoma"));
				biblioteca.setProvincia(registro.getString("provincia"));
				biblioteca.setCalle(registro.getString("calle"));
				biblioteca.setCodigo_postal(registro.getInt("codigo_postal"));
				biblioteca.setTelefono(registro.getInt("telefono"));

				arrlBiblio.add(biblioteca);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Error al cerrar.\n");
			} catch (NullPointerException e) {

			}
		}
		
		return arrlBiblio;
	}
	
	public static ArrayList<Object[]> obtenerFilasTablaBiblioteca() {

		ArrayList<Biblioteca> bibliotecas = LeerTablaBiblioteca();

		ArrayList<Object[]> arrlBibliotecas = new ArrayList<>();

		for (Biblioteca biblioteca : bibliotecas) {

			Object[] fila = new Object[] { biblioteca.getId(), biblioteca.getComunidad_autonoma(), biblioteca.getProvincia(),
					biblioteca.getCalle(), biblioteca.getCodigo_postal(), biblioteca.getTelefono() };
			arrlBibliotecas.add(fila);
		}

		return arrlBibliotecas;
	}
	
	/// Crear biblioteca nueva
		public static void insertarBiblioteca(String comunidadAutonoma, String provincia, String calle, int postal, int telefono) {

			ConectorBBDD conextor = new ConectorBBDD();

			ArrayList<Biblioteca> arrlBiblioteca = new ArrayList<>();

			PreparedStatement preparedStatement = null;
			ResultSet registro = null;
			Connection conexion = null;

			try {

				conexion = conextor.connect();

				String query = String.format("insert into biblioteca (comunidad_autonoma, provincia, calle, codigo_postal, telefono)values(?,?,?,?,?);");

				preparedStatement = conexion.prepareStatement(query);

				preparedStatement.setString(1, comunidadAutonoma);
				preparedStatement.setString(2, provincia);
				preparedStatement.setString(3, calle);
				preparedStatement.setInt(4, postal);
				preparedStatement.setInt(5, telefono);

				int contador = preparedStatement.executeUpdate();

				if (contador > 0) {
					JOptionPane.showMessageDialog(null, "La Fila se ha insertado correctamente",
							"Confirmación de los inserción", JOptionPane.INFORMATION_MESSAGE);
				}

				System.out.println("Inserción exitosa.");

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					preparedStatement.close();
					conexion.close();
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("Error al cerrar.\n");
				} catch (NullPointerException e) {

				}
			}

		}

}
