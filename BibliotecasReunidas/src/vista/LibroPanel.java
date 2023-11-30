package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class LibroPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldCod;
	private JTextField textField_Titulo;
	private JTextField textField_Categoria;
	private JTextField textField_Idioma;
	private JTextField textField_Fecha;
	private JTextField textField_ISBN;

	/**
	 * Create the panel.
	 */
	public LibroPanel() {
		setLayout(null);
		
		JLabel lblCódigo = new JLabel("Código ID");
		lblCódigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCódigo.setBounds(199, 164, 89, 30);
		add(lblCódigo);
		
		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTitulo.setBounds(199, 227, 89, 30);
		add(lblTitulo);
		
		JLabel lblCategoriaDelLibro = new JLabel("Categoria Del Libro");
		lblCategoriaDelLibro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCategoriaDelLibro.setBounds(199, 277, 120, 30);
		add(lblCategoriaDelLibro);
		
		JLabel lblIdioma = new JLabel("Idioma");
		lblIdioma.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIdioma.setBounds(199, 347, 120, 30);
		add(lblIdioma);
		
		JLabel lblFechaDePublicacin = new JLabel("Fecha de Publicación");
		lblFechaDePublicacin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFechaDePublicacin.setBounds(199, 406, 156, 30);
		add(lblFechaDePublicacin);
		
		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIsbn.setBounds(199, 469, 156, 30);
		add(lblIsbn);
		
		textFieldCod = new JTextField();
		textFieldCod.setBounds(487, 167, 212, 30);
		add(textFieldCod);
		textFieldCod.setColumns(10);
		
		textField_Titulo = new JTextField();
		textField_Titulo.setColumns(10);
		textField_Titulo.setBounds(487, 230, 212, 30);
		add(textField_Titulo);
		
		textField_Categoria = new JTextField();
		textField_Categoria.setColumns(10);
		textField_Categoria.setBounds(487, 298, 212, 30);
		add(textField_Categoria);
		
		textField_Idioma = new JTextField();
		textField_Idioma.setColumns(10);
		textField_Idioma.setBounds(487, 350, 212, 30);
		add(textField_Idioma);
		
		textField_Fecha = new JTextField();
		textField_Fecha.setColumns(10);
		textField_Fecha.setBounds(487, 409, 212, 30);
		add(textField_Fecha);
		
		textField_ISBN = new JTextField();
		textField_ISBN.setColumns(10);
		textField_ISBN.setBounds(487, 472, 212, 30);
		add(textField_ISBN);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnCrear.setBounds(100, 593, 99, 30);
		add(btnCrear);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnModificar.setBounds(299, 593, 99, 30);
		add(btnModificar);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnBuscar.setBounds(498, 593, 99, 30);
		add(btnBuscar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnEliminar.setBounds(697, 593, 99, 30);
		add(btnEliminar);
		
		JLabel lblNewLabel = new JLabel("Libro");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(409, 89, 79, 45);
		add(lblNewLabel);

	}

}
