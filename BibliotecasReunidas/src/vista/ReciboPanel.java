package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controlador.DataMetodos;
import modelo.Libro;
import modelo.Recibo;
import modelo.Socio;

import javax.swing.JComboBox;

public class ReciboPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTable TablaRecibos;
	DefaultTableModel modeloRecibo = new DefaultTableModel();
	private JTextField textFieldMonto;
	private JTextField textFieldFechaRecibo;


	/**
	 * Create the panel.
	 */

	public ReciboPanel() {
		this.frame = frame;
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 133, 749, 478);
		add(scrollPane);
		
		TablaRecibos = new JTable() {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		scrollPane.setViewportView(TablaRecibos);
		
		modeloRecibo.setColumnIdentifiers(
				new Object[] {"Id Recibo", "Id Socios", "Id Libros", "Monto", "Fecha Recibos", "Tipo de Pago"});
		
		TablaRecibos.setModel(modeloRecibo);
		
		JLabel LabelTitulo = new JLabel("Recibos");
		LabelTitulo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		LabelTitulo.setBounds(40, 34, 101, 36);
		add(LabelTitulo);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnModificar.setBounds(40, 81, 148, 40);
		add(btnModificar);
		
 	    JComboBox comboBoxRecibo = new JComboBox();
 	   for(Recibo recibo: DataMetodos.obtenerCodigoRecibo()) {
			comboBoxRecibo.addItem(recibo.getId());
		}
 	    comboBoxRecibo.setBounds(975, 142, 194, 22);
 	    add(comboBoxRecibo);
 	    
 	    JComboBox comboBoxSocio = new JComboBox();
 	   for(Socio socio: DataMetodos.obtenerCodigoSocio()) {
			comboBoxSocio.addItem(socio.getId());
		}
 	    comboBoxSocio.setBounds(975, 189, 194, 22);
 	    add(comboBoxSocio);
 	    
 	    JComboBox comboBoxLibro = new JComboBox();
 	   for(Libro libro: DataMetodos.obtenerCodigoLibro()) {
			comboBoxLibro.addItem(libro.getId());
		}
 	    comboBoxLibro.setBounds(975, 246, 194, 22);
 	    add(comboBoxLibro);
		
		textFieldMonto = new JTextField();
		textFieldMonto.setColumns(10);
		textFieldMonto.setBounds(975, 291, 194, 25);
		add(textFieldMonto);
		
		textFieldFechaRecibo = new JTextField();
		textFieldFechaRecibo.setColumns(10);
		textFieldFechaRecibo.setBounds(975, 342, 194, 25);
		add(textFieldFechaRecibo);
		
		String[] Pagos = { "efectivo", "tarjeta" };
		JComboBox comboBoxPago = new JComboBox(Pagos);
		comboBoxPago.setBounds(975, 396, 194, 22);
		add(comboBoxPago);
		
		JLabel lblCodigoSocio = new JLabel("Codigo Socio");
		lblCodigoSocio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigoSocio.setBounds(820, 186, 95, 25);
		add(lblCodigoSocio);
		
		JLabel lblCodigoRecibo = new JLabel("Codigo Recibo");
		lblCodigoRecibo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigoRecibo.setBounds(820, 139, 95, 25);
		add(lblCodigoRecibo);
		
		JLabel lblCodigoLibro = new JLabel("Codigo Libro");
		lblCodigoLibro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigoLibro.setBounds(820, 243, 95, 25);
		add(lblCodigoLibro);
		
		JLabel lblMonto = new JLabel("Monto");
		lblMonto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMonto.setBounds(830, 289, 95, 25);
		add(lblMonto);
		
		JLabel lblFechaRecibo = new JLabel("Fecha Recibo");
		lblFechaRecibo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFechaRecibo.setBounds(820, 342, 95, 25);
		add(lblFechaRecibo);
		
		JLabel lblTipoPago = new JLabel("Tipo Pago");
		lblTipoPago.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTipoPago.setBounds(820, 393, 95, 25);
		add(lblTipoPago);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnEliminar.setBounds(249, 82, 148, 40);
		add(btnEliminar);
		
		// Obtener el modelo de selección de la tabla
 		ListSelectionModel selectionModel = TablaRecibos.getSelectionModel();
 		// Agregar un ListSelectionListener al modelo de selección
 		selectionModel.addListSelectionListener(new ListSelectionListener() {
 			@Override
 			 public void valueChanged(ListSelectionEvent e) {
 		        if (!e.getValueIsAdjusting()) {
 		            if (TablaRecibos.getSelectedRow() >= 0) {
 		                String id_recibo = modeloRecibo.getValueAt(TablaRecibos.getSelectedRow(), 0).toString();
 		                String id_socio = modeloRecibo.getValueAt(TablaRecibos.getSelectedRow(), 1).toString();
 		                String id_libro = modeloRecibo.getValueAt(TablaRecibos.getSelectedRow(), 2).toString();
 		                String monto = modeloRecibo.getValueAt(TablaRecibos.getSelectedRow(), 3).toString();
 		                String fecha_recibo = modeloRecibo.getValueAt(TablaRecibos.getSelectedRow(), 4).toString();
 		                String tipo_pago = modeloRecibo.getValueAt(TablaRecibos.getSelectedRow(), 5).toString();

 		               comboBoxRecibo.setSelectedItem(id_recibo);
 		               comboBoxSocio.setSelectedItem(id_socio);
 		               comboBoxLibro.setSelectedItem(id_libro);
 		                textFieldMonto.setText(monto);
 		                textFieldFechaRecibo.setText(fecha_recibo);
 		                comboBoxPago.setSelectedItem(tipo_pago);

 		                // Habilitar y deshabilitar campos según sea necesario
 		                btnEliminar.setEnabled(true);
 		                btnModificar.setEnabled(true); 
 		                textFieldFechaRecibo.setEnabled(false); 
 		            }
 		        }
 		    }
 		});
 		
 		recargarTablaRecibo();
 		btnEliminar.setEnabled(false);
 	    btnModificar.setEnabled(false);   
	}
	
	private void recargarTablaRecibo() {
		modeloRecibo.setRowCount(0); // SIRVE PARA RESETEAR LA TABLA
		for (Object[] filaDeRecibo : DataMetodos.obtenerFilasTablaRecibo()) {
			modeloRecibo.addRow(filaDeRecibo);
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
