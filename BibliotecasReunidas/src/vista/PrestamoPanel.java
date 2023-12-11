package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controlador.DataMetodos;
import modelo.Biblioteca;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PrestamoPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	DefaultTableModel modeloPrestamo = new DefaultTableModel();
	private JTable TablaPrestamos;
	private JTextField textFieldIdSocio;
	private JTextField textFieldIdLibro;
	private JTextField textFieldIdUsuario;
	private JTextField textFieldFechaEntrega;
	
	private JFrame frame;

	public PrestamoPanel() {
		this.frame = frame;
		setLayout(null);
		
		JLabel lblPrestamos = new JLabel("Prestamos");
		lblPrestamos.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblPrestamos.setBounds(31, 41, 159, 40);
		add(lblPrestamos);
		
		
		
		JButton btnConfirmarCambios = new JButton("Confirmar Cambios");
		btnConfirmarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        String idSocioStr = textFieldIdSocio.getText();
		        String idLibroStr = textFieldIdLibro.getText();
		        String idUsuarioStr = textFieldIdUsuario.getText();
		        String fechaEntregaStr = textFieldFechaEntrega.getText();

		        int idSocio = Integer.parseInt(idSocioStr);
		        int idLibro = Integer.parseInt(idLibroStr);
		        int idUsuario = Integer.parseInt(idUsuarioStr);

		        DataMetodos.insertarPrestamo(idSocio, idLibro, idUsuario, fechaEntregaStr);

		        //
				limpiarTextFields();
				recargarTablaPrestamo();	
			}
		});
		btnConfirmarCambios.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnConfirmarCambios.setBounds(921, 453, 246, 46);
		add(btnConfirmarCambios);
		
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
				btnConfirmarCambios.setVisible(true);
			}
		});
		btnAnyadir.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnAnyadir.setBounds(31, 92, 148, 40);
		add(btnAnyadir);

		JButton btnBuscar_Superior = new JButton("Buscar");
		btnBuscar_Superior.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnBuscar_Superior.setBounds(625, 92, 148, 40);
		add(btnBuscar_Superior);
		
		textFieldIdSocio = new JTextField();
		textFieldIdSocio.setColumns(10);
		textFieldIdSocio.setBounds(973, 144, 194, 25);
		add(textFieldIdSocio);
		
		textFieldIdLibro = new JTextField();
		textFieldIdLibro.setColumns(10);
		textFieldIdLibro.setBounds(973, 199, 194, 25);
		add(textFieldIdLibro);
		
		textFieldIdUsuario = new JTextField();
		textFieldIdUsuario.setColumns(10);
		textFieldIdUsuario.setBounds(973, 248, 194, 25);
		add(textFieldIdUsuario);
		
		textFieldFechaEntrega = new JTextField();
		textFieldFechaEntrega.setColumns(10);
		textFieldFechaEntrega.setBounds(973, 360, 194, 25);
		add(textFieldFechaEntrega);
		
		recargarTablaPrestamo();
		
		JLabel lblCodigo_1 =new JLabel("Codigo Socio");
		lblCodigo_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigo_1.setBounds(817, 148, 95, 25);
		add(lblCodigo_1);
		
		JLabel lblCodigo_2 = new JLabel("Codigo Libro");
		lblCodigo_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigo_2.setBounds(817, 197, 95, 25);
		add(lblCodigo_2);
		
		JLabel lblCodigo_3 = new JLabel("Codigo Usuario");
		lblCodigo_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigo_3.setBounds(809, 246, 114, 25);
		add(lblCodigo_3);
		
		JLabel lblCodigo_6 = new JLabel("Fecha de entrega");
		lblCodigo_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigo_6.setBounds(797, 358, 114, 25);
		add(lblCodigo_6);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(modeloPrestamo.getValueAt(TablaPrestamos.getSelectedRow(), 0).toString());
				DataMetodos.eliminarPrestamo(id);
				limpiarTextFields();
				recargarTablaPrestamo();
			}
		});
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(225, 92, 148, 40);
		add(btnEliminar);

	}
	
	// ====================== metodos para esta tablas==============

		private void limpiarTextFields() {
			textFieldIdSocio.setText("");
			textFieldIdLibro.setText("");
			textFieldIdUsuario.setText("");
			textFieldFechaEntrega.setText("");
		}

		private void recargarTablaPrestamo() {

			// Cargo los elementos de la tabla en el modelo
			modeloPrestamo.setRowCount(0); // SIRVE PARA RESETEAR LA TABLA
			for (Object[] filaDePrestamo : DataMetodos.obtenerFilasTablaPrestamos()) {
				modeloPrestamo.addRow(filaDePrestamo);
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
