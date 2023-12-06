package vista;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

import controlador.ConectorBBDD;
import controlador.DataMetodos;
import modelo.Incidencias;
import modelo.Libro;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

public class IncidenciasPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public int idSeleccionado = SocioPanel.idSeleccionado;
	private JTable tablaIncidencias;
	private ConectorBBDD con;
	public JComboBox comboBox;
	private DataMetodos data;
	
	DefaultTableModel modeloTablaIncidencias = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public IncidenciasPanel() {
		
		setLayout(null);
		
		JLabel lblIncidencias = new JLabel("Incidencias");
		lblIncidencias.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblIncidencias.setBounds(10, 11, 130, 30);
		add(lblIncidencias);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(356, 11, 650, 451);
		add(scrollPane);
		
		tablaIncidencias = new JTable();
		scrollPane.setViewportView(tablaIncidencias);
		modeloTablaIncidencias.setColumnIdentifiers(new Object[]{"Id Incidencia","Id Libro","Titulo del Libro","Descripcion de Incidencia"});
		tablaIncidencias.setModel(modeloTablaIncidencias);
		
		JButton btnCrearIncidencia = new JButton("Crear");
		btnCrearIncidencia.setBounds(135, 439, 89, 23);
		add(btnCrearIncidencia);
		
		JButton btnBorrarIncidencia = new JButton("Borrar");
		btnBorrarIncidencia.setBounds(257, 439, 89, 23);
		add(btnBorrarIncidencia);
		
		JButton btnModificarIncidencia = new JButton("Modificar");
		btnModificarIncidencia.setBounds(10, 439, 89, 23);
		add(btnModificarIncidencia);
		
		JLabel lblIdLibro = new JLabel("ID Libro:");
		lblIdLibro.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblIdLibro.setBounds(10, 52, 130, 30);
		add(lblIdLibro);
		
		JLabel lblTituloDelLibro = new JLabel("Titulo del libro:");
		lblTituloDelLibro.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTituloDelLibro.setBounds(10, 144, 214, 30);
		add(lblTituloDelLibro);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDescripcion.setBounds(10, 247, 130, 30);
		add(lblDescripcion);
		
		comboBox = new JComboBox();
		comboBox.setBounds(10, 93, 89, 22);
		add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(10, 198, 214, 20);
		add(textField);
		textField.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 288, 214, 87);
		add(textArea);
		
	}
	
	@Override
	public void addNotify() {
		// TODO Auto-generated method stub
		super.addNotify();
		modeloTablaIncidencias.setRowCount(0);
		rellenarTablaAlPrincipio();
	}

	public void rellenarComBoxIdLibros() {
		data = new DataMetodos();
		for(Libro l : data.LeerTablaLibro()) {
			comboBox.addItem(l.getId());
			comboBox.addItem(l.getTitulo());
		}
	}
	
	private void rellenarTablaAlPrincipio() {
		con = new ConectorBBDD();
		for(Incidencias i : con.consultarIncidencias(idSeleccionado)) {
			modeloTablaIncidencias.addRow(new Object[] {
					i.getId(),
					i.getId_libro(),
					i.getNombre_libro(),
					i.getTexto_incidencias()
			});
		}
	}
}
