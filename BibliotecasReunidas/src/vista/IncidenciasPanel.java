package vista;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

import controlador.ConectorBBDD;
import modelo.Incidencias;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class IncidenciasPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public int idSeleccionado = SocioPanel.idSeleccionado;
	private JTable tablaIncidencias;
	private ConectorBBDD con;
	
	DefaultTableModel modeloTablaIncidencias = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};

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
		scrollPane.setBounds(356, 11, 800, 745);
		add(scrollPane);
		
		tablaIncidencias = new JTable();
		scrollPane.setViewportView(tablaIncidencias);
		modeloTablaIncidencias.setColumnIdentifiers(new Object[]{"Id Incidencia","Id Libro","Titulo del Libro","Descripcion de Incidencia"});
		tablaIncidencias.setModel(modeloTablaIncidencias);
		
		
		JButton btnBuscarIncidencias = new JButton("Buscar");
		btnBuscarIncidencias.setBounds(10, 632, 89, 23);
		add(btnBuscarIncidencias);
		
		JButton btnCrearIncidencia = new JButton("Crear");
		btnCrearIncidencia.setBounds(257, 632, 89, 23);
		add(btnCrearIncidencia);
		
		JButton btnBorrarIncidencia = new JButton("Borrar");
		btnBorrarIncidencia.setBounds(257, 666, 89, 23);
		add(btnBorrarIncidencia);
		
		JButton btnModificarIncidencia = new JButton("Modificar");
		btnModificarIncidencia.setBounds(10, 666, 89, 23);
		add(btnModificarIncidencia);
		
	}
	
	@Override
	public void addNotify() {
		// TODO Auto-generated method stub
		super.addNotify();
		System.out.println("Ventana creada");
		System.out.println(idSeleccionado);
		modeloTablaIncidencias.setRowCount(0);
		rellenarTablaAlPrincipio();
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
