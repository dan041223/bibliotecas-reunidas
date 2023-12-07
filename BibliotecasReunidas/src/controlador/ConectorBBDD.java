package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import modelo.Incidencias;
import modelo.Socio;
import modelo.Usuario;
import modelo.Usuario.TIPO_PERFIL;
import vista.Ventana_Principal;

/*
 * Clase que reune todas las conexiones a la Base de Datos
 */
public class ConectorBBDD {

	final String url = "jdbc:postgresql://db.sbuicfeenrshatyqggbt.supabase.co:5432/postgres";
	final String user = "postgres";
	final String password = "Dalexiana1223";

	/*
	 * Metodo que se encarga de conectarse a la base de datos y devolver la conexion
	 * Con la conexion puedes realizar todas las operaciones que quieras
	 */

	public Connection connect() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/*
	 * Metodo que se encargar de consultar buscar en la base de datos el usuario
	 * especificado
	 * Devuelve un usuario
	 * Es usado en el Login para saber quien es el que ha iniciado sesion
	 */
	public void cambiarSocio(int id, String nombre, String dni, int telefono, String calle, int codigoPostal,
			String email) {
		Connection con = connect();
		Ventana_Principal vp = Ventana_Principal.getInstance();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("UPDATE socios SET nombre = '" + nombre + "', dni = '" + dni + "', telefono = "
					+ telefono + ", calle = '" + calle + "', codigo_postal = '" + codigoPostal + "', email = '" + email
					+ "' WHERE id_socio = " + id + ";");
			JOptionPane.showMessageDialog(vp, "Usuario modificado con éxito");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void eliminarSocio(int id) {
		Connection con = connect();
		Ventana_Principal vp = Ventana_Principal.getInstance();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("UPDATE socios SET nombre = 'Socio borrado', email = 'Email borrado', telefono = 000000000, dni = 'DNI borrado', calle = 'Calle borrada', codigo_postal = 00000"
					+ " WHERE id_socio = " + id + ";");
			JOptionPane.showMessageDialog(vp, "Usuario borrado con exito");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void agregarSocio(String nombre, String email, int telefono, String dni, int codigoPostal, String calle) {
		Connection con = connect();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(
					"INSERT INTO socios (nombre, fecha_creacion, dni, email , calle, codigo_postal, telefono)"
							+ " VALUES ('" + nombre + "', CURRENT_TIMESTAMP, '" + dni + "', '" + email + "', '" + calle
							+ "', '" + codigoPostal + "', '" + telefono + "');");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Usuario consultarUsuario(String correo, String password) {
		Connection con = connect();
		Usuario usuario = null;
		try {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT * FROM usuario WHERE email = '" + correo + "' AND password = '" + password + "';");																			// '1'='1
			if (rs.next()) {
				usuario = new Usuario();
				usuario.setNombre(rs.getString("nombre"));
				usuario.setTelefono(rs.getInt("telefono"));
				usuario.setEmail(rs.getString("email"));
				usuario.setCalle(rs.getString("calle"));
				usuario.setCodigo_postal(rs.getInt("codigo_postal"));
				usuario.setDni(rs.getString("dni"));
				usuario.setPassword(rs.getString("password"));

				if ("administrador".equals(rs.getString("tipo_perfil"))) {

					usuario.setTipo_perfil(TIPO_PERFIL.ADMINISTRADOR);

				} else if ("administrativo".equals(rs.getString("tipo_perfil"))) {

					usuario.setTipo_perfil(TIPO_PERFIL.ADMINISTRATIVO);

				}
				Ventana_Principal vp = Ventana_Principal.getInstance();
				JOptionPane.showMessageDialog(vp, "Se ha encontrado al usuario");
			} else if (!rs.next()) {
				JOptionPane.showMessageDialog(null, "No se ha encontrador ningún usuario con esta información",
						"Error al buscar usuario", JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}

	/*
	 * Metodo para consultar todos los socios en la base de datos
	 * Devuelve un ArrayList de Socios
	 * Usado para rellenar una tabla al iniciar esta ventana por primera vez
	 */
	public ArrayList<Socio> consultarSocios() {
		ArrayList<Socio> socios = new ArrayList<Socio>();
		Socio socio;
		Connection con = connect();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT id_socio, nombre, fecha_creacion, dni, telefono, calle, codigo_postal, email "
					+ "FROM socios "
					+ "WHERE nombre NOT ILIKE '%Socio borrado%';");
			while (rs.next()) {
				socio = new Socio();
				socio.setId(rs.getInt("id_socio"));
				socio.setNombre(rs.getString("nombre"));
				socio.setFecha_creacion(rs.getString("fecha_creacion"));
				socio.setDni(rs.getString("dni"));
				socio.setTelefono(rs.getInt("telefono"));
				socio.setCalle(rs.getString("calle"));
				socio.setCodigo_postal(rs.getInt("codigo_postal"));
				socio.setEmail(rs.getString("email"));

				socios.add(socio);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return socios;

	}

	/*
	 * Metodo para consultar en la base de datos los socios que coinciden con el id
	 * introducido por el usuario
	 * Devuelve un arrayList relleno solo con los usuario que tengan la misma id que
	 * ha dicho el usuario
	 */
	public ArrayList<Socio> consultarSociosPorId(int id) {
		ArrayList<Socio> socios = new ArrayList<Socio>();
		Socio socio;
		Connection con = connect();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT id_socio, nombre, fecha_creacion, dni, telefono, calle, codigo_postal, email FROM socios "
					+ "WHERE id_socio = " + id + " "
					+ "AND nombre NOT ILIKE '%Socio borrado%';");
			while (rs.next()) {
				socio = new Socio();
				socio.setId(rs.getInt("id_socio"));
				socio.setNombre(rs.getString("nombre"));
				socio.setFecha_creacion(rs.getString("fecha_creacion"));
				socio.setDni(rs.getString("dni"));
				socio.setTelefono(rs.getInt("telefono"));
				socio.setCalle(rs.getString("calle"));
				socio.setCodigo_postal(rs.getInt("codigo_postal"));
				socio.setEmail(rs.getString("email"));

				socios.add(socio);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return socios;
	}

	/*
	 * Metodo para consultar en la base de datos los socios que coinciden con el
	 * email introducido por el usuario
	 * Deveuelve un arrayList relleno solo con los socios que tengan el mismo email
	 * que ha dicho el usuario
	 */
	public ArrayList<Socio> consultarSociosPorEmail(String email) {
		ArrayList<Socio> socios = new ArrayList<Socio>();
		Socio socio;
		Connection con = connect();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT id_socio, nombre, fecha_creacion, dni, telefono, calle, codigo_postal, email "
							+ "FROM socios "
							+ "WHERE email = '" + email + "' "
							+ "AND nombre NOT ILIKE '%Socio borrado%';");
			while (rs.next()) {
				socio = new Socio();
				socio.setId(rs.getInt("id_socio"));
				socio.setNombre(rs.getString("nombre"));
				socio.setFecha_creacion(rs.getString("fecha_creacion"));
				socio.setDni(rs.getString("dni"));
				socio.setTelefono(rs.getInt("telefono"));
				socio.setCalle(rs.getString("calle"));
				socio.setCodigo_postal(rs.getInt("codigo_postal"));
				socio.setEmail(rs.getString("email"));

				socios.add(socio);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return socios;

	}

	public void agregarIncidencia(int idSeleccionado, int idLibro, String incidenciaDescripcion) {
		Connection con = connect();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(
					"INSERT INTO incidencias (id_socio, id_libro, descripcion_incidencia)"
							+ " VALUES (" + idSeleccionado + "," + idLibro + ",'" + incidenciaDescripcion + "');");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void modificarIncidencia(int idIncidenciaSeleccionada, int idSocioSeleccionado, int idLibro,
			String descripcionIncidencia) {
		Connection con = connect();
		Ventana_Principal vp = Ventana_Principal.getInstance();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("UPDATE incidencias SET id_socio = " + idSocioSeleccionado + ", id_libro = " + idLibro + ", descripcion_incidencia = '" + descripcionIncidencia + "'"
					+ " WHERE id_incidencia = " + idIncidenciaSeleccionada + ";");
			JOptionPane.showMessageDialog(vp, "Incidencia modificada con éxito");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void borrarIncidencia(int idIncidenciaSeleccionada) {
		Connection con = connect();
		Ventana_Principal vp = Ventana_Principal.getInstance();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM incidencias WHERE id_incidencia = " + idIncidenciaSeleccionada  + ";");
			JOptionPane.showMessageDialog(vp, "Incidencia borrada con exito");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void cambiarEstadoIncidenciaAResuelta(int idIncidenciaSeleccionada) {
		Connection con = connect();
		Ventana_Principal vp = Ventana_Principal.getInstance();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("UPDATE incidencias SET estado_incidencia = true"
					+ " WHERE id_incidencia = " + idIncidenciaSeleccionada + ";");
			JOptionPane.showMessageDialog(vp, "Incidencia marcada como resuelta");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void cambiarEstadoIncidenciaANoResuelta(int idIncidenciaSeleccionada) {
		Connection con = connect();
		Ventana_Principal vp = Ventana_Principal.getInstance();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("UPDATE incidencias SET estado_incidencia = false"
					+ " WHERE id_incidencia = " + idIncidenciaSeleccionada + ";");
			JOptionPane.showMessageDialog(vp, "Incidencia marcada como no resuelta");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Incidencias> consultarIncidencias(int id) {
		ArrayList<Incidencias> incidencias = new ArrayList<Incidencias>();
		Incidencias incidencia;
		Connection con = connect();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT id_incidencia, incidencias.id_libro, libros.titulo, incidencias.descripcion_incidencia, estado_incidencia "
							+ "FROM incidencias, libros "
							+ "WHERE id_socio = " + id + " "
							+ "AND libros.id_libro = incidencias.id_libro;");
			while (rs.next()) {
				incidencia = new Incidencias();
				incidencia.setId(rs.getInt("id_incidencia"));
				incidencia.setId_libro(rs.getInt("id_libro"));
				incidencia.setNombre_libro(rs.getString("titulo"));
				incidencia.setId_socio(id);
				incidencia.setTexto_incidencias(rs.getString("descripcion_incidencia"));
				if(rs.getBoolean("estado_incidencia") == false) {
					incidencia.setEstadoIncidencia("No resuelta");
				}else if(rs.getBoolean("estado_incidencia") == true) {
					incidencia.setEstadoIncidencia("Resuelta");
				}
				incidencias.add(incidencia);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return incidencias;
	}

	public ArrayList<Incidencias> consultarIncidenciasNoResueltas(int idSocioSeleccionado) {
		ArrayList<Incidencias> incidencias = new ArrayList<Incidencias>();
		Incidencias incidencia;
		Connection con = connect();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT id_incidencia, incidencias.id_libro, libros.titulo, incidencias.descripcion_incidencia, estado_incidencia "
							+ "FROM incidencias, libros "
							+ "WHERE id_socio = " + idSocioSeleccionado + " "
							+ "AND libros.id_libro = incidencias.id_libro "
							+ "AND estado_incidencia = FALSE;");
			while (rs.next()) {
				incidencia = new Incidencias();
				incidencia.setId(rs.getInt("id_incidencia"));
				incidencia.setId_libro(rs.getInt("id_libro"));
				incidencia.setNombre_libro(rs.getString("titulo"));
				incidencia.setId_socio(idSocioSeleccionado);
				incidencia.setTexto_incidencias(rs.getString("descripcion_incidencia"));
				if(rs.getBoolean("estado_incidencia") == false) {
					incidencia.setEstadoIncidencia("No resuelta");
				}else if(rs.getBoolean("estado_incidencia") == true) {
					incidencia.setEstadoIncidencia("Resuelta");
				}
				incidencias.add(incidencia);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return incidencias;
	}

	public ArrayList<Incidencias> consultarIncidenciasResueltas(int idSocioSeleccionado) {
		ArrayList<Incidencias> incidencias = new ArrayList<Incidencias>();
		Incidencias incidencia;
		Connection con = connect();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT id_incidencia, incidencias.id_libro, libros.titulo, incidencias.descripcion_incidencia, estado_incidencia "
							+ "FROM incidencias, libros "
							+ "WHERE id_socio = " + idSocioSeleccionado + " "
							+ "AND libros.id_libro = incidencias.id_libro "
							+ "AND estado_incidencia = TRUE;");
			while (rs.next()) {
				incidencia = new Incidencias();
				incidencia.setId(rs.getInt("id_incidencia"));
				incidencia.setId_libro(rs.getInt("id_libro"));
				incidencia.setNombre_libro(rs.getString("titulo"));
				incidencia.setId_socio(idSocioSeleccionado);
				incidencia.setTexto_incidencias(rs.getString("descripcion_incidencia"));
				if(rs.getBoolean("estado_incidencia") == false) {
					incidencia.setEstadoIncidencia("No resuelta");
				}else if(rs.getBoolean("estado_incidencia") == true) {
					incidencia.setEstadoIncidencia("Resuelta");
				}
				incidencias.add(incidencia);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return incidencias;
	}
	
	
}
