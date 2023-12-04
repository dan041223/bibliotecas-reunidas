package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controlador.DataMetodos;
import modelo.Autor;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		setBounds(0, 0, 1186, 711);

		JLabel codigoID = new JLabel("Código ID");
		codigoID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		codigoID.setBounds(776, 121, 80, 34);
		add(codigoID);

		JLabel lblNombre = new JLabel("Nombre ");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setBounds(776, 204, 60, 34);
		add(lblNombre);

		JLabel lblNacionalidad = new JLabel("Nacionalidad");
		lblNacionalidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNacionalidad.setBounds(776, 295, 80, 34);
		add(lblNacionalidad);

		JLabel lblFecha = new JLabel("Fecha de Nacimiento");
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFecha.setBounds(765, 379, 137, 34);
		add(lblFecha);

		textField_nombre = new JTextField();
		textField_nombre.setBounds(928, 207, 228, 34);
		add(textField_nombre);
		textField_nombre.setColumns(10);

		textField_Cod = new JTextField();
		textField_Cod.setColumns(10);
		textField_Cod.setBounds(928, 124, 228, 34);
		add(textField_Cod);

		textField_Nacionalidad = new JTextField();
		textField_Nacionalidad.setColumns(10);
		textField_Nacionalidad.setBounds(928, 298, 228, 34);
		add(textField_Nacionalidad);

		textField_Fecha = new JTextField();
		textField_Fecha.setColumns(10);
		textField_Fecha.setBounds(928, 382, 228, 34);
		add(textField_Fecha);

		JLabel lblNewLabel_3 = new JLabel("AUTOR ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_3.setBounds(82, 76, 94, 24);
		add(lblNewLabel_3);

		
		//
		JButton btnCrear = new JButton("Añadir");

		btnCrear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				DataMetodos.insertarAutor(textField_nombre.getText(), textField_Nacionalidad.getText(),
						textField_Fecha.getText());
				limpiarTextFields();
				recargarTablaAutor(); // es para actualizar la tabla en el Jpanel una vez insertado.
			}
		});
		btnCrear.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnCrear.setBounds(789, 487, 160, 34);

		add(btnCrear);

		//
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataMetodos.modificarTablaAutor(Integer.parseInt(textField_Cod.getText()), textField_nombre.getText(),
						textField_Nacionalidad.getText(), textField_Fecha.getText());
				limpiarTextFields();
				recargarTablaAutor();
			}
		});
		btnModificar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnModificar.setBounds(789, 566, 160, 34);
		add(btnModificar);

		//
		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(61, 121, 680, 507);
		add(scrollPane);

		//
		tablaAutores = new JTable() {
			// Vamos actualizar la IsCellEditerTable para impedir que las celdas de la tabla
			// sean modificadas.
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
		


		scrollPane.setViewportView(tablaAutores);
		
		modeloAutor.setColumnIdentifiers(new Object[] { "Código ID", "Nombre", "Nacionalidad", "Fecha de Nacimiento" });
		
		tablaAutores.setModel(modeloAutor);

		JButton btnBuscar_1 = new JButton("Buscar");
		btnBuscar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Autor> autores = DataMetodos.filtraPorCampos(textField_Cod.getText(), textField_nombre.getText(),
						textField_Nacionalidad.getText(), textField_Fecha.getText());
				//desminuirTamanyo();
				limpiarTextFields();
				recargarTablaAutor(autores);
			}
		});
		
		btnBuscar_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnBuscar_1.setBounds(985, 487, 160, 34);
		add(btnBuscar_1);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int id = Integer.parseInt(modeloAutor.getValueAt(tablaAutores.getSelectedRow(), 0).toString());
				DataMetodos.eliminarAutor(id);
				limpiarTextFields();
				recargarTablaAutor();
			}
		});
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnEliminar.setBounds(985, 566, 160, 34);
		add(btnEliminar);

		tablaAutores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// Solo se puede seleccionar una fila a la
																			// vez
		
		tablaAutores.getTableHeader().setReorderingAllowed(false);// Impedir la reordenación de columnas

		
		// Obtener el modelo de selección de la tabla
        ListSelectionModel selectionModel = tablaAutores.getSelectionModel();
     // Agregar un ListSelectionListener al modelo de selección
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Verificar si la selección está cambiando y no está ajustando
                if (!e.getValueIsAdjusting()) { 
                	//Habilito estos botones
                	btnModificar.setEnabled(true);
            		btnEliminar.setEnabled(true);
                	
                }
            }
        });
        
        
        
		recargarTablaAutor();
		
		
		
		//	//Sirve para desabilitar la caja de texto
		textField_Cod.setEnabled(false);
		//Sirve para desabilitar el Boton
		btnModificar.setEnabled(false);
		btnEliminar.setEnabled(false);
	}

	private void limpiarTextFields() {
		// Metodo para limpiar los 3 Text Field
		textField_Cod.setText("");
		textField_nombre.setText("");
		textField_Nacionalidad.setText("");
		textField_Fecha.setText("");
	}

	private void recargarTablaAutor() {

		// Cargo los elementos de la tabla en el modelo
		modeloAutor.setRowCount(0); // SIRVE PARA RESETEAR LA TABLA
		for (Object[] filaDeAutor : DataMetodos.obtenerFilasTablaAutor()) {
			modeloAutor.addRow(filaDeAutor);
		}
		/*
		 * // Si hay filas en la tabla, que seleccione la primera if
		 * (modeloAutor.getRowCount() > 0) { tablaAutores.setRowSelectionInterval(0, 0);
		 * }
		 */
	}

	private void recargarTablaAutor(List<Autor> autores) {

		modeloAutor.setRowCount(0); // SIRVE PARA RESETEAR LA TABLA

		// cargo todas las filas correspondientes al array pasado por parametro
		for (Autor autor : autores) {
			modeloAutor.addRow(new Object[] { autor.getId(), autor.getNombre(), autor.getNacionalidad(),
					autor.getFecha_nacimiento() });
		}
		/*
		 * //Si hay filas en la tabla, que seleccione la primera
		 * if(modeloAutor.getRowCount()>0) { tablaAutores.setRowSelectionInterval(0, 0);
		 * }
		 */
	}

	private void desminuirTamanyo() {
		JFrame frame = (JFrame)SwingUtilities.getWindowAncestor(this);
		System.out.println("Vamos a disminuir el tamaño");

		if(frame!=null) {
			int nuevoAncho = this.getWidth() - 300;
			int nuevoAlto = this.getHeight();
			frame.setSize(nuevoAncho, nuevoAlto);
			frame.invalidate();
			frame.repaint();
			System.out.println("Disminuido");
		}
	}
	
	private void aumentarTamanyo() {
		JFrame frame = (JFrame)SwingUtilities.getWindowAncestor(this);
		System.out.println("Vamos a aumentar el tamaño");
		if(frame!=null) {
			int nuevoAncho = this.getWidth() + 300;
			int nuevoAlto = this.getHeight();
			frame.setSize(nuevoAncho, nuevoAlto);
			System.out.println("Aumentado");
		}
	}

}
