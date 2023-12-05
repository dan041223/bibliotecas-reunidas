package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.table.DefaultTableModel;

import controlador.DataMetodos;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BibliotecaPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable TablaBibliotecas;
	DefaultTableModel modeloTabla = new DefaultTableModel();
	private JTextField textFieldComunidad;
	private JTextField textFieldProvincia;
	private JTextField textFieldCalle;
	private JTextField textFieldPostal;
	private JTextField textFieldTelefono;

	/**
	 * Create the panel.
	 */
	public BibliotecaPanel() {
		setLayout(null);
		
		JLabel JLabelTitulo = new JLabel("Bibliotecas");
		JLabelTitulo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 19));
		JLabelTitulo.setBounds(38, 35, 111, 32);
		add(JLabelTitulo);
		
		JLabel lblCerrarSesion = new JLabel("Cerrar sesión");
		lblCerrarSesion.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCerrarSesion.setBounds(45, 12, 110, 14);
		lblCerrarSesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getDefaultCursor());
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Ventana_Principal.getInstance().cambiarPanel(new MenuPanel());
			}
		});
		add(lblCerrarSesion);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 78, 887, 524);
		add(scrollPane);
		
		
		TablaBibliotecas = new JTable() {

			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
		scrollPane.setViewportView(TablaBibliotecas);
		
		modeloTabla.setColumnIdentifiers(new Object[] {"Código ID", "Comunidad Autónoma", "Provincia", "Calle", "Código Postal", "Teléfono"});
		TablaBibliotecas.setModel(modeloTabla);
		
		textFieldComunidad = new JTextField();
		textFieldComunidad.setBounds(995, 105, 214, 42);
		add(textFieldComunidad);
		textFieldComunidad.setColumns(10);
		
		textFieldProvincia = new JTextField();
		textFieldProvincia.setColumns(10);
		textFieldProvincia.setBounds(995, 224, 214, 42);
		add(textFieldProvincia);
		
		textFieldCalle = new JTextField();
		textFieldCalle.setColumns(10);
		textFieldCalle.setBounds(995, 333, 214, 42);
		add(textFieldCalle);
		
		textFieldPostal = new JTextField();
		textFieldPostal.setColumns(10);
		textFieldPostal.setBounds(995, 447, 214, 42);
		add(textFieldPostal);
		
		textFieldTelefono = new JTextField();
		textFieldTelefono.setColumns(10);
		textFieldTelefono.setBounds(995, 543, 214, 42);
		add(textFieldTelefono);
		
		JLabel lblNewLabel = new JLabel("Comunidad Autónoma");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 16));
		lblNewLabel.setBounds(995, 61, 192, 32);
		add(lblNewLabel);
		
		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setFont(new Font("Tahoma", Font.ITALIC, 16));
		lblProvincia.setBounds(995, 181, 192, 32);
		add(lblProvincia);
		
		JLabel lblCalle = new JLabel("Calle");
		lblCalle.setFont(new Font("Tahoma", Font.ITALIC, 16));
		lblCalle.setBounds(995, 290, 192, 32);
		add(lblCalle);
		
		JLabel lblCdigoPostal = new JLabel("Código Postal");
		lblCdigoPostal.setFont(new Font("Tahoma", Font.ITALIC, 16));
		lblCdigoPostal.setBounds(995, 404, 192, 32);
		add(lblCdigoPostal);
		
		JLabel lblTelfono = new JLabel("Teléfono");
		lblTelfono.setFont(new Font("Tahoma", Font.ITALIC, 16));
		lblTelfono.setBounds(995, 500, 192, 32);
		add(lblTelfono);
		
		JButton btnNewButton = new JButton("Insertar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataMetodos.insertarBiblioteca(textFieldComunidad.getText(), textFieldProvincia.getText(), textFieldCalle.getText(), Integer.parseInt(textFieldPostal.getText()), Integer.parseInt(textFieldTelefono.getText()));
				limpiarTextFields();
				recargarTablaAutor();
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.ITALIC, 18));
		btnNewButton.setBounds(1005, 656, 122, 42);
		add(btnNewButton);
		
		recargarTablaBiblioteca();
	}
	
	public void recargarTablaBiblioteca() {
		modeloTabla.setRowCount(0);
		for (Object[] filaBiblio : DataMetodos.obtenerFilasTablaBiblioteca()) {
			modeloTabla.addRow(filaBiblio);
		}
	}
	
	private void limpiarTextFields() {
		// Metodo para limpiar los 3 Text Field
		textFieldComunidad.setText("");
		textFieldProvincia.setText("");
		textFieldCalle.setText("");
		textFieldPostal.setText("");
		textFieldTelefono.setText("");
	}
	
	private void recargarTablaAutor() {
		modeloTabla.setRowCount(0); // SIRVE PARA RESETEAR LA TABLA
		for (Object[] filaDeBiblio : DataMetodos.obtenerFilasTablaBiblioteca()) {
			modeloTabla.addRow(filaDeBiblio);
		}
	}
}
