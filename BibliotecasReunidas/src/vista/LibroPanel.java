package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controlador.DataMetodos;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class LibroPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldCod;
	private JTextField textField_Titulo;
	private JTextField textField_Categoria;
	private JTextField textField_Idioma;
	private JTextField textField_Fecha;
	private JTextField textField_ISBN;
	private JTable tableLibros;
	DefaultTableModel modeloLibro = new DefaultTableModel();

	/**
	 * Create the panel.
	 */
	public LibroPanel() {
		
		setLayout(null);
		setBounds(0, 0, 1186, 711);
		
		JLabel lblCódigo = new JLabel("Código ID");
		lblCódigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCódigo.setBounds(649, 118, 89, 30);
		add(lblCódigo);
		
		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTitulo.setBounds(649, 198, 89, 30);
		add(lblTitulo);
		
		JLabel lblCategoriaDelLibro = new JLabel("Categoria Del Libro");
		lblCategoriaDelLibro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCategoriaDelLibro.setBounds(654, 280, 120, 30);
		add(lblCategoriaDelLibro);
		
		JLabel lblIdioma = new JLabel("Idioma");
		lblIdioma.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIdioma.setBounds(654, 366, 120, 30);
		add(lblIdioma);
		
		JLabel lblFechaDePublicacin = new JLabel("Fecha de Publicación");
		lblFechaDePublicacin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFechaDePublicacin.setBounds(649, 427, 156, 30);
		add(lblFechaDePublicacin);
		
		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIsbn.setBounds(649, 495, 156, 30);
		add(lblIsbn);
		
		textFieldCod = new JTextField();
		textFieldCod.setBounds(782, 121, 212, 30);
		add(textFieldCod);
		textFieldCod.setColumns(10);
		
		textField_Titulo = new JTextField();
		textField_Titulo.setColumns(10);
		textField_Titulo.setBounds(782, 201, 212, 30);
		add(textField_Titulo);
		
		textField_Categoria = new JTextField();
		textField_Categoria.setColumns(10);
		textField_Categoria.setBounds(782, 283, 212, 30);
		add(textField_Categoria);
		
		textField_Idioma = new JTextField();
		textField_Idioma.setColumns(10);
		textField_Idioma.setBounds(784, 369, 212, 30);
		add(textField_Idioma);
		
		textField_Fecha = new JTextField();
		textField_Fecha.setColumns(10);
		textField_Fecha.setBounds(782, 430, 212, 30);
		add(textField_Fecha);
		
		textField_ISBN = new JTextField();
		textField_ISBN.setColumns(10);
		textField_ISBN.setBounds(782, 498, 212, 30);
		add(textField_ISBN);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnCrear.setBounds(878, 581, 99, 30);
		add(btnCrear);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnModificar.setBounds(695, 679, 99, 30);
		add(btnModificar);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnBuscar.setBounds(695, 581, 99, 30);
		add(btnBuscar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnEliminar.setBounds(878, 668, 99, 30);
		add(btnEliminar);
		
		JLabel lblNewLabel = new JLabel("Libro");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(57, 38, 79, 45);
		add(lblNewLabel);
		
		//------------------Creo la tabla ---------------
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(57, 115, 582, 508);
		add(scrollPane);
		
		// Vamos actualizar la IsCellEditerTable para impedir que las celdas de la tabla sean modificadas.
		tableLibros = new JTable(){
									
				public boolean isCellEditable(int row, int column) {
					return false;
				}

			};
		
		scrollPane.setViewportView(tableLibros);
		modeloLibro.setColumnIdentifiers(new Object [] { "Código ID","Titulo","Categoria del Libro","Idioma","Fecha de publiacion", "ISBN"});
		tableLibros.setModel(modeloLibro);
		
		recargarTablaAutor();
	}
	
	private void recargarTablaAutor() {

		// Cargo los elementos de la tabla en el modelo
		modeloLibro.setRowCount(0); // SIRVE PARA RESETEAR LA TABLA
		for (Object[] filaLibro : DataMetodos.obtenerFilasTablaLibro()) {
			modeloLibro.addRow(filaLibro);
		}
		/*
		 * // Si hay filas en la tabla, que seleccione la primera if
		 * (modeloAutor.getRowCount() > 0) { tablaAutores.setRowSelectionInterval(0, 0);
		 * }
		 */
	}
	
}
