package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Checkbox;
import java.awt.Font;
import java.awt.Frame;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controlador.DataMetodos;
import modelo.Biblioteca;
import modelo.Libro;
import modelo.Socio;
import modelo.Usuario;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class PrestamoPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	DefaultTableModel modeloPrestamo = new DefaultTableModel();
	private JTable TablaPrestamos;
	
	private JFrame frame;
	
	// Creamos los JButton
    JButton btnAnyadir;
    JButton btnModificar;
    JButton btnEliminar;
    private JTextField textFieldFechaEntrega;
    private JTextField textFieldFechaPrevista;
    private JTextField textFieldFechaPrestamo;
    private JTextField textFieldIdPrestamo;
    private JCheckBox  CheckBoxEntregar;

	public PrestamoPanel() {
		this.frame = frame;
		setLayout(null);
			
		textFieldIdPrestamo = new JTextField();
		textFieldIdPrestamo.setText("");
		textFieldIdPrestamo.setEnabled(false);
		textFieldIdPrestamo.setColumns(10);
		textFieldIdPrestamo.setBounds(939, 193, 164, 25);
		add(textFieldIdPrestamo);
		
		JComboBox comboBoxSocio = new JComboBox();
		for(Socio socio: DataMetodos.obtenerCodigoSocio()) {
			comboBoxSocio.addItem(socio.getId());
		}
		comboBoxSocio.setBounds(939, 249, 164, 22);
		add(comboBoxSocio);
		
		JComboBox comboBoxLibro = new JComboBox();
		for(Libro libro: DataMetodos.obtenerCodigoLibro()) {
			comboBoxLibro.addItem(libro.getId());
		}
		comboBoxLibro.setBounds(939, 300, 164, 22);
		add(comboBoxLibro);
		
		JComboBox comboBoxUsuario = new JComboBox();
		for(Usuario usuario: DataMetodos.obtenerCodigoUsuario()) {
			comboBoxUsuario.addItem(usuario.getId());
		}
		comboBoxUsuario.setBounds(939, 351, 164, 22);
		add(comboBoxUsuario);
		
		textFieldFechaEntrega = new JTextField();
		textFieldFechaEntrega.setText("");
		textFieldFechaEntrega.setBounds(939, 505, 164, 25);
		add(textFieldFechaEntrega);
		textFieldFechaEntrega.setColumns(10);
		
		textFieldFechaPrevista = new JTextField();
		textFieldFechaPrevista.setText("");
		textFieldFechaPrevista.setEnabled(false);
		textFieldFechaPrevista.setColumns(10);
		textFieldFechaPrevista.setBounds(939, 453, 164, 25);
		add(textFieldFechaPrevista);
		
		textFieldFechaPrestamo = new JTextField();
		textFieldFechaPrestamo.setText("");
		textFieldFechaPrestamo.setEnabled(false);
		textFieldFechaPrestamo.setColumns(10);
		textFieldFechaPrestamo.setBounds(939, 403, 164, 25);
		add(textFieldFechaPrestamo);
			
		
		JLabel lblPrestamos = new JLabel("Prestamos");
		lblPrestamos.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblPrestamos.setBounds(31, 41, 159, 40);
		add(lblPrestamos);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 148, 742, 485);
		add(scrollPane);
		
		TablaPrestamos = new JTable() {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		scrollPane.setViewportView(TablaPrestamos);
		
		modeloPrestamo.setColumnIdentifiers(
				new Object[] { "Código Prestamo", "Código Socio", "Codigo libro", "Código Usuario", "Fecha Prestamo", "Fecha Prevista", "Fecha Entrega" });
		
		TablaPrestamos.setModel(modeloPrestamo);
		
		JButton btnAnyadir = new JButton("Añadir");
		btnAnyadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aumentarTamanyo();
				
				 int codigoSocio = (int) comboBoxSocio.getSelectedItem();
				 int codigoLibro = (int) comboBoxLibro.getSelectedItem();
				 int codigoUsuario = (int) comboBoxUsuario.getSelectedItem();

		        DataMetodos.insertarPrestamo(codigoSocio, codigoLibro, codigoUsuario);

				recargarTablaPrestamo();
				disminuirTamanyo();
			}
		});
		btnAnyadir.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnAnyadir.setBounds(31, 92, 148, 40);
		add(btnAnyadir);
		
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aumentarTamanyo();
				
				int id = Integer.parseInt(textFieldIdPrestamo.getText());
				int codigoSocio = (int) comboBoxSocio.getSelectedItem();
				int codigoLibro = (int) comboBoxLibro.getSelectedItem();
				int codigoUsuario = (int) comboBoxUsuario.getSelectedItem();
				
				DataMetodos.modificarPrestamo(id, codigoSocio, codigoLibro, codigoUsuario);
				
                recargarTablaPrestamo();
                disminuirTamanyo();
                btnAnyadir.setEnabled(true);
                btnEliminar.setEnabled(false);
				btnModificar.setEnabled(false);
				CheckBoxEntregar.setEnabled(false);	
			}
		});
		btnModificar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnModificar.setBounds(227, 92, 148, 40);
		add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aumentarTamanyo();
				
				int id = Integer.parseInt(textFieldIdPrestamo.getText());
				 DataMetodos.eliminarPrestamo(id);
				
                recargarTablaPrestamo();
                disminuirTamanyo();
                btnAnyadir.setEnabled(true);
                btnEliminar.setEnabled(false);
				btnModificar.setEnabled(false);
				CheckBoxEntregar.setEnabled(false);	
			}
		});
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnEliminar.setBounds(421, 92, 148, 40);
		add(btnEliminar);
		
		CheckBoxEntregar = new JCheckBox("Entregado");
		CheckBoxEntregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aumentarTamanyo();
				
				int id = Integer.parseInt(textFieldIdPrestamo.getText());
				
				DataMetodos.marcarEntregado(id);
				
                recargarTablaPrestamo();
                disminuirTamanyo();
                btnAnyadir.setEnabled(true);
                btnEliminar.setEnabled(false);
				btnModificar.setEnabled(false);
				CheckBoxEntregar.setSelected(false);
				CheckBoxEntregar.setEnabled(false);	
			}
		});
		CheckBoxEntregar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		CheckBoxEntregar.setBounds(939, 586, 123, 47);
		add(CheckBoxEntregar);
		
		JLabel lblCodigo_1 =new JLabel("Codigo Socio");
		lblCodigo_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigo_1.setBounds(798, 246, 95, 25);
		add(lblCodigo_1);
		
		JLabel lblCodigo_2 = new JLabel("Codigo Libro");
		lblCodigo_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigo_2.setBounds(798, 297, 95, 25);
		add(lblCodigo_2);
		
		JLabel lblCodigo_3 = new JLabel("Codigo Usuario");
		lblCodigo_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigo_3.setBounds(798, 348, 114, 25);
		add(lblCodigo_3);
		
		JLabel lblFechaEntrega = new JLabel("Fecha Entrega");
		lblFechaEntrega.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFechaEntrega.setBounds(798, 503, 114, 25);
		add(lblFechaEntrega);
		
		JLabel lblFechaPrevista = new JLabel("Fecha Prevista");
		lblFechaPrevista.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFechaPrevista.setBounds(798, 453, 114, 25);
		add(lblFechaPrevista);
		
		JLabel lblFechaPrestamo = new JLabel("Fecha Prestamo");
		lblFechaPrestamo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFechaPrestamo.setBounds(798, 401, 114, 25);
		add(lblFechaPrestamo);
		
		JLabel lblCodigo_1_1 = new JLabel("Codigo Prestamo");
		lblCodigo_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigo_1_1.setBounds(798, 191, 131, 25);
		add(lblCodigo_1_1);
		
		 // Obtener el modelo de selección de la tabla
 		ListSelectionModel selectionModel = TablaPrestamos.getSelectionModel();
 		// Agregar un ListSelectionListener al modelo de selección
 		selectionModel.addListSelectionListener(new ListSelectionListener() {
 			@Override
 			 public void valueChanged(ListSelectionEvent e) {
 		        if (!e.getValueIsAdjusting()) {
 		            if (TablaPrestamos.getSelectedRow() >= 0) {
 		                String id = modeloPrestamo.getValueAt(TablaPrestamos.getSelectedRow(), 0).toString();
 		                String id_socio = modeloPrestamo.getValueAt(TablaPrestamos.getSelectedRow(), 1).toString();
 		                String id_libro = modeloPrestamo.getValueAt(TablaPrestamos.getSelectedRow(), 2).toString();
 		                String id_usuario = modeloPrestamo.getValueAt(TablaPrestamos.getSelectedRow(), 3).toString();
 		                String fecha_prestamo = modeloPrestamo.getValueAt(TablaPrestamos.getSelectedRow(), 4).toString();
 		                String fecha_prevista = modeloPrestamo.getValueAt(TablaPrestamos.getSelectedRow(), 5).toString();
 		                String fecha_entrega = "No entregado"; // Valor predeterminado si está vacío

 		                // Verifica si la columna 6 no está vacía antes de intentar obtener su valor
 		                Object valorColumna6 = modeloPrestamo.getValueAt(TablaPrestamos.getSelectedRow(), 6);
 		                if (valorColumna6 != null) {
 		                    fecha_entrega = valorColumna6.toString();
 		                }

 		                textFieldIdPrestamo.setText(id);
 		                comboBoxSocio.setSelectedItem(id_socio);
 		                comboBoxLibro.setSelectedItem(id_libro);
 		                comboBoxUsuario.setSelectedItem(id_usuario);
 		                textFieldFechaPrestamo.setText(fecha_prestamo);
 		                textFieldFechaPrevista.setText(fecha_prevista);
 		                textFieldFechaEntrega.setText(fecha_entrega);

 		                // Habilitar y deshabilitar campos según sea necesario
 		                btnEliminar.setEnabled(true);
 		                btnModificar.setEnabled(true); 
 		                CheckBoxEntregar.setEnabled(true); 
 		                btnAnyadir.setEnabled(false);
 		            }
 		        }
 		    }
 		});
		
		recargarTablaPrestamo();
		btnModificar.setEnabled(false);
		btnEliminar.setEnabled(false);
		textFieldIdPrestamo.setEnabled(false);	
		textFieldFechaPrestamo.setEnabled(false);	
		textFieldFechaPrevista.setEnabled(false);	
		textFieldFechaEntrega.setEnabled(false);	
		CheckBoxEntregar.setEnabled(false);	
	}
	
	// ====================== metodos para esta tablas==============

		private void recargarTablaPrestamo() {

			// Cargo los elementos de la tabla en el modelo
			modeloPrestamo.setRowCount(0); // SIRVE PARA RESETEAR LA TABLA
			for (Object[] filaDePrestamo : DataMetodos.obtenerFilasTablaPrestamos()) {
				modeloPrestamo.addRow(filaDePrestamo);
			}

		}
		
		private void disminuirTamanyo() {
	        if (frame != null) {
	            int nuevoAncho = 810;
	            int nuevoAlto = frame.getHeight();
	            frame.setSize(nuevoAncho, nuevoAlto);
	            System.out.println("Disminuido");
	        }
	    }
		
		private void aumentarTamanyo() {
			if (frame != null) {
				int nuevoAncho = 1250;
				int nuevoAlto = frame.getHeight();
				frame.setSize(nuevoAncho, nuevoAlto);

			}
		}
}
