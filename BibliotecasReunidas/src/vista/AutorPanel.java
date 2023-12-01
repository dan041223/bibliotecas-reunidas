package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controlador.DataMetodos;
import modelo.Autor;

import javax.swing.JScrollPane;

public class AutorPanel extends JPanel {
	

	private static final long serialVersionUID = 1L;
	private JTextField textField_nombre;
	private JTextField textField_Cod;
	private JTextField textField_Nacionalidad;
	private JTextField textField_Fecha;
	DefaultTableModel modeloAutor = new DefaultTableModel();
	private JTable tablaAutores;

	/**
	 * Create the panel.
	 */
	public AutorPanel() {
		System.out.println("Ventana Autor Panel");

		setLayout(null);
		setBounds(0,0,1241,869);

		JLabel codigoID = new JLabel("Código ID");
		codigoID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		codigoID.setBounds(826, 141, 80, 34);
		add(codigoID);
		
		JLabel lblNombre = new JLabel("Nombre ");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setBounds(836, 194, 60, 34);
		add(lblNombre);
		
		JLabel lblNacionalidad = new JLabel("Nacionalidad");
		lblNacionalidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNacionalidad.setBounds(826, 256, 80, 34);
		add(lblNacionalidad);
		
		JLabel lblFecha = new JLabel("Fecha de Nacimiento");
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFecha.setBounds(792, 337, 137, 34);
		add(lblFecha);
		
		textField_nombre = new JTextField();
		textField_nombre.setBounds(969, 197, 228, 34);
		add(textField_nombre);
		textField_nombre.setColumns(10);
		
		textField_Cod = new JTextField();
		textField_Cod.setColumns(10);
		textField_Cod.setBounds(969, 133, 228, 34);
		add(textField_Cod);
		
		textField_Nacionalidad = new JTextField();
		textField_Nacionalidad.setColumns(10);
		textField_Nacionalidad.setBounds(969, 259, 228, 34);
		add(textField_Nacionalidad);
		
		textField_Fecha = new JTextField();
		textField_Fecha.setColumns(10);
		textField_Fecha.setBounds(969, 337, 228, 34);
		add(textField_Fecha);
		
		JLabel lblNewLabel_3 = new JLabel("AUTOR ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_3.setBounds(82, 76, 94, 24);
		add(lblNewLabel_3);
		
		JButton btnCrear = new JButton("Crear ");
		btnCrear.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnCrear.setBounds(826, 441, 99, 34);
		add(btnCrear);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnModificar.setBounds(1098, 430, 99, 34);
		add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnEliminar.setBounds(836, 502, 99, 34);
		add(btnEliminar);
		
		JButton btnBuscar_1 = new JButton("Buscar");
		btnBuscar_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnBuscar_1.setBounds(1098, 520, 99, 34);
		add(btnBuscar_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(82, 141, 680, 527);
		add(scrollPane);
		
		tablaAutores = new JTable();
		scrollPane.setViewportView(tablaAutores);
		modeloAutor.setColumnIdentifiers(new Object[] {"Código ID", "Nombre", "Nacionalidad","Fecha de Nacimiento"});
		tablaAutores.setModel(modeloAutor);
		
		//Cargo los elementos de la tabla en el modelo
		modeloAutor.setRowCount(0); // SIRVE PARA RESETEAR LA TABLA
		//for(Autor autor: DataMetodos.LeerTablaAutor()) {
		for(Object[] filaDeAutor: DataMetodos.obtenerFilasTablaAutor()) {

			/*
			modeloAutor.addRow(new Object[]{
					autor.getId(),
					autor.getNombre(),
					autor.getNacionalidad(),
					autor.getFecha_nacimiento()
				
			});
			*/
			modeloAutor.addRow(filaDeAutor);
		}
		
	}
	
}
