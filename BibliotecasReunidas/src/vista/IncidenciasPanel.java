package vista;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import controlador.ConectorBBDD;
import controlador.DataMetodos;
import modelo.Incidencias;
import modelo.Libro;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.Color;

public class IncidenciasPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public int idSocioSeleccionado = SocioPanel.idSocioSeleccionado;
	public static int idIncidenciaSeleccionada;
	private String estadoActualIncidencia;
	private JTable tablaIncidencias;
	private ConectorBBDD con;
	public JComboBox comboBox;
	private DataMetodos data;
	public JTextArea textArea;
	public JButton btnBorrarIncidencia;
	public JButton btnModificarIncidencia;
	public JRadioButton rdbtnMostrarTodas;
	public JRadioButton rdbtnMostrarNoResueltas;
	public JRadioButton rdbtnMostrarResueltas;
	public JButton btnResolver;
	private JFrame frame;

	DefaultTableModel modeloTablaIncidencias = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};

	/**
	 * Create the panel.
	 */
	public IncidenciasPanel(JFrame frame) {
		setBackground(new Color(128, 128, 192));
		this.frame = frame;
		setLayout(null);
		
		JLabel lblIncidencias = new JLabel("Incidencias");
		lblIncidencias.setForeground(new Color(255, 255, 255));
		lblIncidencias.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblIncidencias.setBounds(10, 11, 130, 30);
		add(lblIncidencias);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 93, 786, 502);
		add(scrollPane);
		
		
		
		tablaIncidencias = new JTable();
		scrollPane.setViewportView(tablaIncidencias);
		modeloTablaIncidencias.setColumnIdentifiers(new Object[]{"Id Incidencia","Id Libro","Titulo del Libro","Estado","Descripcion de Incidencia"});
		tablaIncidencias.setModel(modeloTablaIncidencias);
		
		ListSelectionModel modeloSeleccionTablaIncidencias = tablaIncidencias.getSelectionModel();
		modeloSeleccionTablaIncidencias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		modeloSeleccionTablaIncidencias.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				btnModificarIncidencia.setEnabled(true);
				btnBorrarIncidencia.setEnabled(true);
				btnResolver.setEnabled(true);
				
				camposATextFieldIncidencias();
				if(modeloSeleccionTablaIncidencias.isSelectionEmpty()) {
					btnBorrarIncidencia.setEnabled(false);
					btnModificarIncidencia.setEnabled(false);
					btnResolver.setEnabled(false);
				}
			}
		});
		
		JButton btnCrearIncidencia = new JButton("Crear");
		btnCrearIncidencia.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnCrearIncidencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				crearIncidencia();
				
				modeloTablaIncidencias.setRowCount(0);
				rellenarTablaAlPrincipio();
				Ventana_Principal vp = Ventana_Principal.getInstance();	
				
				JOptionPane.showMessageDialog(vp, "Se ha agregado satisafactoriamente");
			}
		});
		btnCrearIncidencia.setBounds(112, 59, 89, 23);
		add(btnCrearIncidencia);
		
		btnBorrarIncidencia = new JButton("Borrar");
		btnBorrarIncidencia.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnBorrarIncidencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarIncidencia();
				modeloTablaIncidencias.setRowCount(0);
				rellenarTablaAlPrincipio();
			}
		});
		btnBorrarIncidencia.setBounds(586, 59, 172, 23);
		btnBorrarIncidencia.setEnabled(false);
		add(btnBorrarIncidencia);
		
		btnModificarIncidencia = new JButton("Modificar");
		btnModificarIncidencia.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnModificarIncidencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarIncidencia();
				modeloTablaIncidencias.setRowCount(0);
				rellenarTablaAlPrincipio();
			}
		});
		btnModificarIncidencia.setBounds(211, 59, 130, 23);
		btnModificarIncidencia.setEnabled(false);
		add(btnModificarIncidencia);
		
		JLabel lblLibro = new JLabel("Libro:");
		lblLibro.setForeground(new Color(255, 255, 255));
		lblLibro.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLibro.setBounds(10, 52, 130, 30);
		add(lblLibro);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setForeground(new Color(255, 255, 255));
		lblDescripcion.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDescripcion.setBounds(820, 177, 130, 30);
		add(lblDescripcion);
		
		comboBox = new JComboBox();
		comboBox.addItem("-Listado de libros-");
		comboBox.setBounds(820, 144, 214, 22);
		add(comboBox);
		
		textArea = new JTextArea();
		textArea.setBounds(820, 218, 214, 87);
		add(textArea);
		
		JButton btnLimpiarCampos = new JButton("Limpiar campos");
		btnLimpiarCampos.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnLimpiarCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.setSelectedItem(0);
				textArea.setText("");
				comboBox.setSelectedItem("-Listado de libros-");
			}
		});
		btnLimpiarCampos.setBounds(820, 438, 178, 23);
		add(btnLimpiarCampos);
		
		btnResolver = new JButton("Resuelta / no resuelta");
		btnResolver.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnResolver.setEnabled(false);
		btnResolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modeloTablaIncidencias.setRowCount(0);
				resolverNoResolver();
				
				if(rdbtnMostrarTodas.isSelected()) {
					modeloTablaIncidencias.setRowCount(0);
					rellenarTablaAlPrincipio();
					
				}else if(rdbtnMostrarResueltas.isSelected()) {
					modeloTablaIncidencias.setRowCount(0);
					rellenarTablaConResueltas();
					
				}else if(rdbtnMostrarNoResueltas.isSelected()) {
					modeloTablaIncidencias.setRowCount(0);
					rellenarTablaConNoResueltas();
				}
			}
		});
		btnResolver.setBounds(351, 59, 225, 23);
		add(btnResolver);
		
		rdbtnMostrarResueltas = new JRadioButton("Mostrar resueltas");
		rdbtnMostrarResueltas.setOpaque(false);
		rdbtnMostrarResueltas.setForeground(new Color(255, 255, 255));
		rdbtnMostrarResueltas.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(rdbtnMostrarResueltas.isSelected()) {
					modeloTablaIncidencias.setRowCount(0);
					rellenarTablaConResueltas();
				}
			}
		});
		rdbtnMostrarResueltas.setBounds(820, 333, 214, 23);
		add(rdbtnMostrarResueltas);
		
		rdbtnMostrarNoResueltas = new JRadioButton("Mostrar no resueltas");
		rdbtnMostrarNoResueltas.setOpaque(false);
		rdbtnMostrarNoResueltas.setForeground(new Color(255, 255, 255));
		rdbtnMostrarNoResueltas.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(rdbtnMostrarNoResueltas.isSelected()) {
					modeloTablaIncidencias.setRowCount(0);
					rellenarTablaConNoResueltas();
				}
			}
		});
		rdbtnMostrarNoResueltas.setBounds(820, 359, 214, 23);
		add(rdbtnMostrarNoResueltas);
		
		rdbtnMostrarTodas = new JRadioButton("Mostrar todas");
		rdbtnMostrarTodas.setOpaque(false);
		rdbtnMostrarTodas.setForeground(new Color(255, 255, 255));
		rdbtnMostrarTodas.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(rdbtnMostrarTodas.isSelected()) {
					modeloTablaIncidencias.setRowCount(0);
					rellenarTablaAlPrincipio();
				}
			}
		});
		rdbtnMostrarTodas.setSelected(true);
		rdbtnMostrarTodas.setBounds(820, 385, 214, 23);
		add(rdbtnMostrarTodas);
		
		ButtonGroup grupoRadio = new ButtonGroup();
		grupoRadio.add(rdbtnMostrarTodas);
		grupoRadio.add(rdbtnMostrarResueltas);
		grupoRadio.add(rdbtnMostrarNoResueltas);
		
		JLabel lblLibro_1 = new JLabel("Libro:");
		lblLibro_1.setForeground(new Color(255, 255, 255));
		lblLibro_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLibro_1.setBounds(820, 103, 130, 30);
		add(lblLibro_1);
		
		BufferedImage img1 = null;
		try {
			img1 = ImageIO.read(new File("res\\imagenes\\posibleFondo.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int labelWidth1 = 1424;
	    int labelHeight1 = 825;
	    
	    Image scaledImg1 = img1.getScaledInstance(labelWidth1, labelHeight1, Image.SCALE_SMOOTH);
		
	    ImageIcon icon1 = new ImageIcon(scaledImg1);
	    
		JLabel lblNewLabel = new JLabel(icon1);
		lblNewLabel.setBounds(0, 0, 1434, 825);
		add(lblNewLabel);
	}
	
	public void rellenarTablaConNoResueltas() {
		con = new ConectorBBDD();
		for(Incidencias i : con.consultarIncidenciasNoResueltas(idSocioSeleccionado)) {
			modeloTablaIncidencias.addRow(new Object[] {
					i.getId(),
					i.getId_libro(),
					i.getNombre_libro(),
					i.getEstadoIncidencia(),
					i.getTexto_incidencias(),
			});
		}
		
	}

	public void rellenarTablaConResueltas() {
		con = new ConectorBBDD();
		for(Incidencias i : con.consultarIncidenciasResueltas(idSocioSeleccionado)) {
			modeloTablaIncidencias.addRow(new Object[] {
					i.getId(),
					i.getId_libro(),
					i.getNombre_libro(),
					i.getEstadoIncidencia(),
					i.getTexto_incidencias(),
			});
		}
		
	}
	
	public void rellenarTablaAlPrincipio() {
		con = new ConectorBBDD();
		for(Incidencias i : con.consultarIncidenciasPorIdSocio(idSocioSeleccionado)) {
			modeloTablaIncidencias.addRow(new Object[] {
					i.getId(),
					i.getId_libro(),
					i.getNombre_libro(),
					i.getEstadoIncidencia(),
					i.getTexto_incidencias(),
			});
		}
	}

	public void resolverNoResolver() {
		if(estadoActualIncidencia.equalsIgnoreCase("No resuelta")) {
			con.cambiarEstadoIncidenciaAResuelta(idIncidenciaSeleccionada);
		}else if(estadoActualIncidencia.equalsIgnoreCase("Resuelta")) {
			con.cambiarEstadoIncidenciaANoResuelta(idIncidenciaSeleccionada);
		}
	}

	public void borrarIncidencia() {
		Ventana_Principal vp = Ventana_Principal.getInstance();
		int opcion = JOptionPane.showConfirmDialog(vp, "¿Quiere borrar la incidencia?", "Confirmacion", 0);
		if(opcion == 0) {
			con.borrarIncidencia(idIncidenciaSeleccionada);
		}else if(opcion == 1) {
			
		}
	}

	public void modificarIncidencia() {
		String[] idYTitulo = (comboBox.getSelectedItem().toString()).split(" - ");
		int idLibro = Integer.parseInt(idYTitulo[0]);
		String descripcionIncidencia = textArea.getText();
		
		con.modificarIncidencia(idIncidenciaSeleccionada, idSocioSeleccionado, idLibro, descripcionIncidencia);
		
	}

	public void crearIncidencia() {
		String idTitulo = (String) comboBox.getSelectedItem();
		String[] separaIdTitulo = idTitulo.split(" - ");
		
		int idLibro = Integer.parseInt(separaIdTitulo[0]);
		String titulo = separaIdTitulo[1];
		String incidenciaDescripcion = textArea.getText();
		
		con.agregarIncidencia(idSocioSeleccionado, idLibro, incidenciaDescripcion);	
	}

	public void camposATextFieldIncidencias() {
		int filaSeleccionada = tablaIncidencias.getSelectedRow();
		int columnaSeleccionada = tablaIncidencias.getSelectedColumn();
		
		if(filaSeleccionada != -1 && columnaSeleccionada != -1) {
			idIncidenciaSeleccionada = (int) tablaIncidencias.getValueAt(filaSeleccionada, 0);
			estadoActualIncidencia = (String) tablaIncidencias.getValueAt(filaSeleccionada, 3);
			
			
			String idConcat = (tablaIncidencias.getValueAt(filaSeleccionada, 1)).toString();
			String tituloConcat = (String) tablaIncidencias.getValueAt(filaSeleccionada, 2);
			String idYTitulo = (idConcat + " - " + tituloConcat);
			
			comboBox.setSelectedItem(idYTitulo);
			textArea.setText((String) tablaIncidencias.getValueAt(filaSeleccionada, 4));
		}
	}
	
	@Override
	public void addNotify() {
		// TODO Auto-generated method stub
		super.addNotify();
		modeloTablaIncidencias.setRowCount(0);
		rellenarComBoxIdLibros();
		rellenarTablaAlPrincipio();
	}

	public void rellenarComBoxIdLibros() {
		data = new DataMetodos();
		for(Libro l : data.LeerTablaLibro()) {
			comboBox.addItem(l.getId() + " - " + l.getTitulo());
		}
	}
}
