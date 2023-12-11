package controlador;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;
import modelo.Autor;
import modelo.Biblioteca;
import modelo.Item;
import modelo.Libro;
import modelo.Libro.CategoriaLibro;
import modelo.Prestamo;
import modelo.Ubicacion;
import modelo.Usuario;
import modelo.Usuario.TIPO_PERFIL;
import vista.UbicacionPanel;

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
			
			query = query + " order by id_autor";

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
			String query = "select id_libro,titulo,categoria,idioma,fecha_publicacion,e.nombre, 'Biblioteca:' || u.id_biblioteca || ' - Pasillo:' || u.pasillo || ' - Estanteria:' || u.estanteria as \"Ubicacion\","
					+ "\"ISBN\" from  libros l, editorial e,ubicacion u where l.id_editorial = e.id_editorial and l.id_ubicacion = u.id_ubicacion and activo = true order by id_libro"
					+ " limit 100";
			// base de datos
			registro = statement.executeQuery(query);

			while (registro.next()) {

				Libro libro = new Libro();
				libro.setId(registro.getInt("id_libro"));
				libro.setTitulo(registro.getString("titulo"));
				libro.setCategoria(obtenerCategoriaLibro(registro.getString("categoria")));
				libro.setIdioma(registro.getString("idioma"));
				libro.setFecha_publicacion(registro.getString("fecha_publicacion"));
				libro.setNombreDeEditorial(registro.getString("nombre"));
				libro.setUbicacion(registro.getString("ubicacion"));
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
					libro.getFecha_publicacion(), libro.getNombreDeEditorial(), libro.getUbicacion(), libro.getIsbn() };
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
			String fecha_publicacion, int id_editorial, int id_ubicacion, String isbn) {

		// trim sirve para quitar espacios a la derecha e izquierda
		id = id.trim();
		titulo = titulo.trim();
		categoria = categoria.trim();
		idioma = idioma.trim();
		fecha_publicacion = fecha_publicacion.trim();
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

		boolean isbnTieneValores = !isbn.equals("");

		try {
			conexion = conextor.connect();

			String query = "select id_libro,titulo,categoria,idioma,fecha_publicacion,e.nombre, 'Biblioteca:' || u.id_biblioteca || ' - Pasillo:' || u.pasillo || ' - Estanteria:' || u.estanteria as \"Ubicacion\","
					+ "\"ISBN\" from libros l, editorial e,ubicacion u where l.id_editorial = e.id_editorial and l.id_ubicacion = u.id_ubicacion and activo = true";

			if (idTieneValores) {
				query = query + " and id_libro = ?";
			}

			// UPPER - sirve para convertir el valor del campo en Mayuscula
			// LOWER - sirve para convertir el valor del campor en minuscula
			// si el campo de texto tiene contenido = que no este vacio
			if (tituloTieneValores) {
				query = query + " and lower(titulo) like ?";
			}

			// si el campo de texto tiene contenido = que no este vacio
			if (categoriaTieneValores) {
				query = query + " and categoria = ?";
			}

			if (idiomaTieneValor) {
				query = query + " and lower(idioma) like ? ";
			}

			if (fechaTieneValores) {
				query = query + " and fecha_publicacion=?";
			}
			
			query = query + " and l.id_editorial =?";
			query = query + " and l.id_ubicacion = ? ";
			
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
				preparedStatement.setString(indice, "%" + titulo.toLowerCase() + "%");
				indice++;
			}

			if (categoriaTieneValores) {
				preparedStatement.setObject(indice, obtenerCategoriaLibro(categoria), java.sql.Types.OTHER);
				indice++;
			}
			if (idiomaTieneValor) {
				preparedStatement.setString(indice, "%" + idioma.toLowerCase() + "%");
				indice++;
			}

			if (fechaTieneValores) {
				Date date = Date.valueOf(fecha_publicacion);
				preparedStatement.setDate(indice, date);
				indice++;
			}

				preparedStatement.setInt(indice, id_editorial);
				indice++;

				preparedStatement.setInt(indice, id_ubicacion);
				indice++;
			

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
				libro.setNombreDeEditorial(registro.getString("nombre"));
				libro.setUbicacion(registro.getString("Ubicacion"));
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
			String fecha_publicacionNueva, int id_editorialNuevo, int id_ubicacionNuevo, String isbnNuevo) {

		ConectorBBDD conextor = new ConectorBBDD();

		boolean actualizado = false;
		PreparedStatement preparedStatement = null;
		Connection conexion = null;

		try {

			conexion = conextor.connect();

			String query = "update libros set titulo=?, categoria=?, idioma=?, fecha_publicacion=?, id_editorial =?, id_ubicacion=?,\"ISBN\"=? where id_libro=?";

			preparedStatement = conexion.prepareStatement(query);

			preparedStatement.setString(1, tituloNuevo);
			preparedStatement.setObject(2, obtenerCategoriaLibro(categoriaNueva), java.sql.Types.OTHER);
			preparedStatement.setString(3, idiomaNuevo);

			Date date = Date.valueOf(fecha_publicacionNueva);
			preparedStatement.setDate(4, date);

			preparedStatement.setInt(5, id_editorialNuevo);
			preparedStatement.setInt(6, id_ubicacionNuevo);
			preparedStatement.setInt(7, Integer.parseInt(isbnNuevo));

			preparedStatement.setInt(8, id);

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


	public static void eliminarLibroLogicamente(int id) {

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
				String query = String.format("Update libros set activo=false where id_libro = %d;", id);
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

	
	// ===============================================Comenzamos con Biblioteca
	// =====================================

	public static ArrayList<Biblioteca> LeerTablaBiblioteca() {

		ConectorBBDD conextor = new ConectorBBDD();

		ArrayList<Biblioteca> arrlBiblioteca = new ArrayList<>();

		Statement statement = null;
		ResultSet registro = null;
		Connection conexion = null;

		try {
			conexion = conextor.connect();
			statement = conexion.createStatement();
			String query = "Select * from biblioteca order by id_biblioteca limit 100"; // porque son los primeros 100
																						// libros
			// que
			// tenemos en nuestra
			// base de datos
			registro = statement.executeQuery(query);

			while (registro.next()) {

				Biblioteca biblioteca = new Biblioteca();
				biblioteca.setId(registro.getInt("id_biblioteca"));
				biblioteca.setProvincia(registro.getString("provincia"));
				biblioteca.setCodigo_postal(registro.getInt("codigo_postal"));
				biblioteca.setTelefono(registro.getInt("telefono"));
				biblioteca.setComunidad_autonoma(registro.getString("comunidad_autonoma"));
				biblioteca.setCalle(registro.getString("calle"));
				arrlBiblioteca.add(biblioteca);
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

		return arrlBiblioteca;

	}

	// recogemos el arrayList de autores y luego lo metemos en un arrayList de
	// Objetos
	public static ArrayList<Object[]> obtenerFilasTablaBiblioteca() {

		ArrayList<Biblioteca> bibliotecas = LeerTablaBiblioteca();

		ArrayList<Object[]> arrlBibliotecas = new ArrayList<>();

		for (Biblioteca biblioteca : bibliotecas) {

			Object[] fila = new Object[] {

					biblioteca.getId(), biblioteca.getProvincia(), biblioteca.getCodigo_postal(),
					biblioteca.getTelefono(), biblioteca.getComunidad_autonoma(), biblioteca.getCalle() };

			arrlBibliotecas.add(fila);

		}

		return arrlBibliotecas;
	}

	/// Crear libro nuevo
	public static void insertarbiblioteca(String provincia, String codigo_Postal, String telefono,
			String comunidad_autonoma, String calle) {

		ConectorBBDD conextor = new ConectorBBDD();

		PreparedStatement preparedStatement = null;
		Connection conexion = null;

		try {

			conexion = conextor.connect();

			String query = "insert into biblioteca (provincia, codigo_postal, telefono, comunidad_autonoma,calle)values(?,?,?,?,?);";

			preparedStatement = conexion.prepareStatement(query);

			preparedStatement.setString(1, provincia);
			preparedStatement.setInt(2, Integer.parseInt(codigo_Postal));
			preparedStatement.setInt(3, Integer.parseInt(telefono));
			preparedStatement.setString(4, comunidad_autonoma);
			preparedStatement.setString(5, calle);

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

	public static boolean modificarTablaBiblioteca(int id, String provinciaNueva, String codigoPostalNuevo,
			String telefonoNuevo, String comunidadNueva, String calleNueva) {

		ConectorBBDD conextor = new ConectorBBDD();

		boolean actualizado = false;
		PreparedStatement preparedStatement = null;
		Connection conexion = null;

		try {

			conexion = conextor.connect();

			String query = "update biblioteca set provincia=?, codigo_postal=? , telefono=?, comunidad_autonoma=?, calle=? where id_biblioteca=?";

			preparedStatement = conexion.prepareStatement(query);

			preparedStatement.setString(1, provinciaNueva);
			preparedStatement.setInt(2, Integer.parseInt(codigoPostalNuevo));
			preparedStatement.setInt(3, Integer.parseInt(telefonoNuevo));
			preparedStatement.setString(4, comunidadNueva);
			preparedStatement.setString(5, calleNueva);

			preparedStatement.setInt(6, id);

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

	// Buscar por todos los campos
	public static ArrayList<Biblioteca> filtraPorCamposBiblioteca(String id, String provincia, String codigo_postal,
			String telefono, String comunidad_autonoma, String calle) {

		// trim sirve para quitar espacios a la derecha e izquierda
		id = id.trim();
		provincia = provincia.trim();
		codigo_postal = codigo_postal.trim();
		telefono = telefono.trim();
		telefono = telefono.trim();
		calle = calle.trim();

		ConectorBBDD conextor = new ConectorBBDD();

		ArrayList<Biblioteca> bibliotecas = new ArrayList<>();

		PreparedStatement preparedStatement = null;
		ResultSet registro = null;
		Connection conexion = null;

		boolean idTieneValores = !id.equals("");// que NO este vacio = TIENE VALORES
		boolean provinciaTieneValores = !provincia.equals(""); // que NO este vacio = TIENE VALORES
		boolean codigo_postalTieneValores = !codigo_postal.equals("");
		boolean telefonoTieneValores = !telefono.equals("");
		boolean comunidad_autonomaTieneValores = !comunidad_autonoma.equals("");
		boolean calleTieneValores = !calle.equals("");

		try {
			conexion = conextor.connect();

			String query = "Select id_biblioteca, provincia, codigo_postal, telefono, comunidad_autonoma,calle from biblioteca where 1=1";

			if (idTieneValores) {
				query = query + " and id_biblioteca = ?";
			}

			// UPPER - sirve para convertir el valor del campo en Mayuscula
			// LOWER - sirve para convertir el valor del campor en minuscula
			// si el campo de texto tiene contenido = que no este vacio
			if (provinciaTieneValores) {
				query = query + " and lower(provincia) like ?";
			}

			// si el campo de texto tiene contenido = que no este vacio
			if (codigo_postalTieneValores) {
				query = query + " and codigo_postal = ?";
			}

			if (telefonoTieneValores) {
				query = query + " and telefono = ? ";
			}

			if (comunidad_autonomaTieneValores) {
				query = query + " and lower(comunidad_autonoma) like ?";
			}
			if (calleTieneValores) {
				query = query + " and lower(calle) like ?";
			}
			
			query = query + " order by id_biblioteca";

			preparedStatement = conexion.prepareStatement(query);

			int indice = 1;
			if (idTieneValores) {
				preparedStatement.setInt(indice, Integer.parseInt(id));
				indice++;
			}

			if (provinciaTieneValores) {
				preparedStatement.setString(indice, "%" + provincia.toLowerCase() + "%");
				indice++;
			}

			if (codigo_postalTieneValores) {
				preparedStatement.setInt(indice, Integer.parseInt(codigo_postal));
				indice++;
			}
			if (telefonoTieneValores) {
				preparedStatement.setInt(indice, Integer.parseInt(telefono));
			}

			if (comunidad_autonomaTieneValores) {
				preparedStatement.setString(indice, "%" + comunidad_autonoma.toLowerCase() + "%");
				indice++;
			}

			if (calleTieneValores) {
				preparedStatement.setString(indice, "%" + calle.toLowerCase() + "%");
				indice++;
			}

			registro = preparedStatement.executeQuery();

			while (registro.next()) {
				Biblioteca biblioteca = new Biblioteca();

				biblioteca.setId(registro.getInt("id_biblioteca"));
				biblioteca.setProvincia(registro.getString("provincia"));
				biblioteca.setCodigo_postal(registro.getInt("codigo_postal"));
				biblioteca.setTelefono(registro.getInt("telefono"));
				biblioteca.setComunidad_autonoma(registro.getString("comunidad_autonoma"));
				biblioteca.setCalle(registro.getString("calle"));
				bibliotecas.add(biblioteca);

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

		return bibliotecas;
	}

	public static void eliminarBiblioteca(int id) {

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
				String query = String.format("delete from biblioteca where id_biblioteca = %d;", id);
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

		// ======================================== Metodos Ubicacion ======================================

	}

	public static ArrayList<Ubicacion> LeerTablaUbicacion() {

		ConectorBBDD conextor = new ConectorBBDD();

		ArrayList<Ubicacion> arrlUbicacion = new ArrayList<>();

		Statement statement = null;
		ResultSet registro = null;
		Connection conexion = null;

		try {
			conexion = conextor.connect();
			statement = conexion.createStatement();
			String query = "Select id_ubicacion,pasillo,estanteria,id_biblioteca from ubicacion order by id_ubicacion limit 100"; // porque
																																	// son
																																	// los
																																	// primeros
																																	// 100
			// libros
			// que
			// tenemos en nuestra
			// base de datos
			registro = statement.executeQuery(query);

			while (registro.next()) {
				Ubicacion ubicacion = new Ubicacion();
				ubicacion.setUbicacion(registro.getInt("id_ubicacion"));
				ubicacion.setPasillo(registro.getInt("pasillo"));
				ubicacion.setEstanteria(registro.getInt("estanteria"));
				ubicacion.setId_biblioteca(registro.getInt("id_biblioteca"));
				arrlUbicacion.add(ubicacion);
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

		return arrlUbicacion;

	}

	// recogemos el arrayList de autores y luego lo metemos en un arrayList de
	// Objetos
	public static ArrayList<Object[]> obtenerFilasTablaUbicacion() {

		ArrayList<Ubicacion> ubicaciones = LeerTablaUbicacion();

		ArrayList<Object[]> arrlUbicaciones = new ArrayList<>();

		for (Ubicacion ubicacion : ubicaciones) {

			Object[] fila = new Object[] {

					ubicacion.getUbicacion(), ubicacion.getPasillo(), ubicacion.getEstanteria(),
					ubicacion.getId_biblioteca() };

			arrlUbicaciones.add(fila);

		}

		return arrlUbicaciones;
	}

	/// Crear Ubicacion nuevo
	public static void insertarUbicacion(String pasillo, String estanteria, String id_biblioteca) {

		ConectorBBDD conextor = new ConectorBBDD();

		PreparedStatement preparedStatement = null;
		Connection conexion = null;

		try {

			conexion = conextor.connect();

			String query = "insert into ubicacion ( pasillo, estanteria, id_biblioteca)values(?,?,?);";

			preparedStatement = conexion.prepareStatement(query);

			preparedStatement.setInt(1, Integer.parseInt(pasillo));
			preparedStatement.setInt(2, Integer.parseInt(estanteria));
			preparedStatement.setInt(3, Integer.parseInt(id_biblioteca));

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
	public static ArrayList<Ubicacion> filtraPorCamposUbicacion(String id, String pasillo, String estanteria,
			String id_biblioteca) {

		// trim sirve para quitar espacios a la derecha e izquierda
		id = id.trim();
		pasillo = pasillo.trim();
		estanteria = estanteria.trim();
		id_biblioteca = id_biblioteca.trim();

		ConectorBBDD conextor = new ConectorBBDD();

		ArrayList<Ubicacion> Ubicaciones = new ArrayList<>();

		PreparedStatement preparedStatement = null;
		ResultSet registro = null;
		Connection conexion = null;

		boolean idTieneValores = !id.equals("");// que NO este vacio = TIENE VALORES
		boolean pasilloTieneValores = !pasillo.equals(""); // que NO este vacio = TIENE VALORES
		boolean estanteriaTieneValores = !estanteria.equals("");
		boolean id_bibliotecaTieneValores = !id_biblioteca.equals("");

		try {
			conexion = conextor.connect();

			String query = "Select id_ubicacion, pasillo, estanteria, id_biblioteca from ubicacion where 1=1";

			if (idTieneValores) {
				query = query + " and id_biblioteca = ?";
			}

			// UPPER - sirve para convertir el valor del campo en Mayuscula
			// LOWER - sirve para convertir el valor del campor en minuscula
			// si el campo de texto tiene contenido = que no este vacio
			if (pasilloTieneValores) {
				query = query + " and pasillo = ?";
			}

			// si el campo de texto tiene contenido = que no este vacio
			if (estanteriaTieneValores) {
				query = query + " and estanteria = ?";
			}

			if (id_bibliotecaTieneValores) {
				query = query + " and id_biblioteca = ? ";
			}

			preparedStatement = conexion.prepareStatement(query);

			int indice = 1;
			if (idTieneValores) {
				preparedStatement.setInt(indice, Integer.parseInt(id));
				indice++;
			}

			if (pasilloTieneValores) {
				preparedStatement.setInt(indice, Integer.parseInt(pasillo));
				indice++;
			}

			if (estanteriaTieneValores) {
				preparedStatement.setInt(indice, Integer.parseInt(estanteria));
				indice++;
			}
			if (id_bibliotecaTieneValores) {
				preparedStatement.setInt(indice, Integer.parseInt(id_biblioteca));
			}

			registro = preparedStatement.executeQuery();

			while (registro.next()) {
				Ubicacion ubicacion = new Ubicacion();

				ubicacion.setUbicacion(registro.getInt("id_ubicacion"));
				ubicacion.setPasillo(registro.getInt("pasillo"));
				ubicacion.setEstanteria(registro.getInt("estanteria"));
				ubicacion.setUbicacion(registro.getInt("id_biblioteca"));
				Ubicaciones.add(ubicacion);

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

		return Ubicaciones;
	}
	
	public static boolean modificarTablaUbicacion(int id, String pasilloNueva, String estanteriaNueva,
			String id_bibliotecaNueva) {

		ConectorBBDD conextor = new ConectorBBDD();

		boolean actualizado = false;
		PreparedStatement preparedStatement = null;
		Connection conexion = null;

		try {

			conexion = conextor.connect();

			String query = "update ubicacion set pasillo=?, estanteria=? , id_biblioteca=? where id_ubicacion=?";

			preparedStatement = conexion.prepareStatement(query);

			preparedStatement.setInt(1, Integer.parseInt(pasilloNueva));
			preparedStatement.setInt(2, Integer.parseInt(estanteriaNueva));
			preparedStatement.setInt(3, Integer.parseInt(id_bibliotecaNueva));
			
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
	

	public static void eliminarUbicacion(int id) {

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
				String query = String.format("delete from ubicacion where id_ubicacion = %d;", id);
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
	
	public static Object[] obtenerUbicacionesParaJComboBox() {

		ConectorBBDD conextor = new ConectorBBDD();

		ArrayList<Item> ubicaciones = new ArrayList<>();

		Statement statement = null;
		ResultSet registro = null;
		Connection conexion = null;

		try {
			conexion = conextor.connect();
			statement = conexion.createStatement();
			String query = "Select id_ubicacion,"
					+ "'Biblioteca:' || id_biblioteca || ' - Pasillo:' || pasillo || ' - Estanteria:' || estanteria as \"Ubicacion\" "
					+ "from ubicacion "
					+ "order by id_ubicacion "
					+ "limit 100"; 
			
			// libros
			// que
			// tenemos en nuestra
			// base de datos
			registro = statement.executeQuery(query);

			while (registro.next()) {
				Item item = new Item();
				item.setNombreMostradoEnElCombo(registro.getString("Ubicacion"));
				item.setIdEnLaTabla(registro.getInt("id_ubicacion"));				
				ubicaciones.add(item);
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

		return ubicaciones.toArray();

	}
	
	public static Object[] obtenerEditorialesParaJComboBox() {

		ConectorBBDD conector = new ConectorBBDD();

		ArrayList<Item> editoriales = new ArrayList<>();

		Statement statement = null;
		ResultSet registro = null;
		Connection conexion = null;

		try {
			conexion = conector.connect();
			statement = conexion.createStatement();
			String query = "Select id_editorial,nombre "
					+ "from editorial "
					+ "order by id_editorial "
					+ "limit 100"; 
			
			registro = statement.executeQuery(query);

			while (registro.next()) {
				Item item = new Item();
				item.setNombreMostradoEnElCombo(registro.getString("nombre"));
				item.setIdEnLaTabla(registro.getInt("id_editorial"));				
				editoriales.add(item);
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

		return editoriales.toArray();

	}
	
	// ======================================== Metodos Prestamos ======================================
	
	public static ArrayList<Prestamo> LeerTablaPrestamo() {

		ConectorBBDD conextor = new ConectorBBDD();

		ArrayList<Prestamo> arrlPrestamo= new ArrayList<>();

		Statement statement = null;
		ResultSet registro = null;
		Connection conexion = null;

		try {
			conexion = conextor.connect();
			statement = conexion.createStatement();
			String query = "Select * from prestamo order by id_prestamo";
			registro = statement.executeQuery(query);

			while (registro.next()) {

				Prestamo pres = new Prestamo();
				pres.setId(registro.getInt("id_prestamo"));
				pres.setId_socio(registro.getInt("id_socio"));
				pres.setId_libro(registro.getInt("id_libro"));
				pres.setId_usuario(registro.getInt("id_usuario"));
				pres.setFecha_prestamo(registro.getString("fecha_prestamo"));
				pres.setFecha_prevista(registro.getString("fecha_prevista"));
				pres.setFecha_entrega(registro.getString("fecha_entrega"));

				arrlPrestamo.add(pres);
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

		return arrlPrestamo;

	}
	
	public static ArrayList<Object[]> obtenerFilasTablaPrestamos() {

		ArrayList<Prestamo> prestamos = LeerTablaPrestamo();

		ArrayList<Object[]> arrlPrestamos = new ArrayList<>();

		for (Prestamo prestamo : prestamos) {

			Object[] fila = new Object[] {

					prestamo.getId(), prestamo.getId_socio(), prestamo.getId_libro(), prestamo.getId_usuario(), prestamo.getFecha_prestamo(), 
					prestamo.getFecha_prevista(), prestamo.getFecha_entrega()};

			arrlPrestamos.add(fila);

		}

		return arrlPrestamos;
	}
	
	/// Crear prestamo nuevo
	public static void insertarPrestamo(int cod_socio, int cod_libro, int cod_user ) {

	    ConectorBBDD conector = new ConectorBBDD();

	    PreparedStatement preparedStatement = null;
	    Connection conexion = null;

	    try {
	        conexion = conector.connect();

	        String query = "insert into prestamo (id_socio, id_libro, id_usuario, fecha_prestamo, fecha_pevista) values(?,?,?,?,?,?);";

	        preparedStatement = conexion.prepareStatement(query);

	        preparedStatement.setInt(1, cod_socio);
	        preparedStatement.setInt(2, cod_libro);
	        preparedStatement.setInt(3, cod_user);

	        LocalDate fechaActual = LocalDate.now();

	        LocalDate fecha15DiasDespues = fechaActual.plusDays(15);

	        java.sql.Date fechaPrestamoSql = java.sql.Date.valueOf(fechaActual);
	        java.sql.Date fecha15DiasDespuesSql = java.sql.Date.valueOf(fecha15DiasDespues);

	        preparedStatement.setDate(4, fechaPrestamoSql);
	        preparedStatement.setDate(5, fecha15DiasDespuesSql);

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
	
	public static void eliminarPrestamo(int id) {

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
				String query = String.format("delete from prestamo where id_biblioteca = %d;", id);
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
	
	// ======================================== Metodos Usuarios ======================================
	
	public static ArrayList<Usuario> LeerTablaUsuario() {

		ConectorBBDD conextor = new ConectorBBDD();

		ArrayList<Usuario> arrlUsuario= new ArrayList<>();

		Statement statement = null;
		ResultSet registro = null;
		Connection conexion = null;

		try {
			conexion = conextor.connect();
			statement = conexion.createStatement();
			String query = "Select * from usuario order by id_usuario";
			registro = statement.executeQuery(query);

			while (registro.next()) {

				Usuario user = new Usuario();
				user.setId(registro.getInt("id_usuario"));
				user.setNombre(registro.getString("nombre"));
				user.setTelefono(registro.getInt("telefono"));
				user.setEmail(registro.getString("email"));
				user.setCodigo_postal(registro.getInt("codigo_postal"));
				user.setDni(registro.getString("dni"));
				user.setTipo_perfil(obtenerTipoPerfil(registro.getString("tipo_perfil")));
				user.setPassword(registro.getString("contraseña"));

				arrlUsuario.add(user);
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

		return arrlUsuario;

	}
	
	public static TIPO_PERFIL obtenerTipoPerfil(String perfil) {
		TIPO_PERFIL result = null;

		switch (perfil.toUpperCase()) {
		case "administrativo":
			result = TIPO_PERFIL.ADMINISTRATIVO;
			break;

		case "administrador":
			result = TIPO_PERFIL.ADMINISTRATIVO;
			break;
		}
		return result;
	}
	
	public static ArrayList<Object[]> obtenerFilasTablaUsuario() {

		ArrayList<Usuario> usuarios = LeerTablaUsuario();

		ArrayList<Object[]> arrlUsuarios = new ArrayList<>();

		for (Usuario usuario : usuarios) {

			Object[] fila = new Object[] {

					usuario.getId(), usuario.getNombre(), usuario.getTelefono(), usuario.getEmail(), usuario.getCodigo_postal(),
					usuario.getDni(), usuario.getTipo_perfil(), usuario.getPassword()};

			arrlUsuarios.add(fila);

		}

		return arrlUsuarios;
	}
	
	/// Crear usuario nuevo
		public static void insertarUsuario(String nombre, int telefono, String email,
				String calle, int codigoPostal, String dni, String perfil, String password) {

			ConectorBBDD conextor = new ConectorBBDD();

			PreparedStatement preparedStatement = null;
			Connection conexion = null;

			try {

				conexion = conextor.connect();

				String query = "insert into usuario (nombre, telefono, email, calle, codigo_postal, dni, tipo_perfil, password)values(?,?,?,?,?,?,?,?);";

				preparedStatement = conexion.prepareStatement(query);

				preparedStatement.setString(1, nombre);
				preparedStatement.setInt(2, telefono);
				preparedStatement.setString(3, email);
				preparedStatement.setString(4, calle);
				preparedStatement.setInt(5, codigoPostal);
				preparedStatement.setString(6, dni);
				preparedStatement.setString(7, perfil);
				preparedStatement.setString(8, password);

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
