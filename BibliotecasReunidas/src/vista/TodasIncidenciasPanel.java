package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;

import controlador.ConectorBBDD;
import controlador.DataMetodos;
import modelo.Incidencias;
import modelo.Libro;
import modelo.Socio;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class TodasIncidenciasPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	public ConectorBBDD con;
	public DataMetodos data;
	public JRadioButton rdbtnMostrarResueltas;
	public JRadioButton rdbtnMostrarNoResueltas;
	public JRadioButton rdbtnMostrarTodas;
	public JComboBox cmbxLibros;
	public JComboBox cmbxSocios;
	public JButton btnResolver;
	public int idIncidenciaSeleccionada;
	private String estadoActualIncidencia;
	private JFrame frame;

	DefaultTableModel modeloTablaIncidencias = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	private JButton btnFiltrar;
	/**
	 * Create the panel.
	 */
	public TodasIncidenciasPanel(JFrame frame) {
		setBackground(new Color(128, 128, 192));
		this.frame = frame;
		setLayout(null);
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("res\\imagenes\\flechita_atras.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int labelWidth = 40;
	    int labelHeight = 40;
	    
	    Image scaledImg = img.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
		
	    ImageIcon icon = new ImageIcon(scaledImg);
	    
		JLabel imgs = new JLabel(icon);
		imgs.setForeground(new Color(255, 255, 255));
		imgs.addMouseListener(new MouseAdapter() {
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
				Ventana_Principal.getInstance().cambiarPanel(new MenuPanel(frame));
			}
		});
		imgs.setBounds(10, 11, 47, 40);
		
		add(imgs);
		
		JLabel lblCerrarSesion = new JLabel("Volver al menu");
		lblCerrarSesion.setForeground(new Color(255, 255, 255));
		lblCerrarSesion.setBackground(new Color(255, 255, 255));
		lblCerrarSesion.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCerrarSesion.setBounds(55, 23, 181, 14);
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
				Ventana_Principal.getInstance().cambiarPanel(new MenuPanel(frame));
			}
		});
		add(lblCerrarSesion);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 82, 835, 618);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		modeloTablaIncidencias.setColumnIdentifiers(new Object[]{"Id Incidencia","Id Socio","Nombre Socio", "Id Libro","Titulo del Libro","Estado","Descripcion de Incidencia"});
		table.setModel(modeloTablaIncidencias);
		
		ListSelectionModel modeloSeleccionTablaIncidencias = table.getSelectionModel();
		modeloSeleccionTablaIncidencias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		modeloSeleccionTablaIncidencias.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				btnResolver.setEnabled(true);
				cogerCamposParaResolver();
				
				if(modeloSeleccionTablaIncidencias.isSelectionEmpty()) {
					btnResolver.setEnabled(false);
				}
			}
		});
		
		JLabel lblSocio = new JLabel("Socio:");
		lblSocio.setForeground(new Color(255, 255, 255));
		lblSocio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSocio.setBounds(855, 102, 93, 14);
		add(lblSocio);
		
		JLabel lblLibro = new JLabel("Libro:");
		lblLibro.setForeground(new Color(255, 255, 255));
		lblLibro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLibro.setBounds(855, 158, 93, 14);
		add(lblLibro);
		
		cmbxLibros = new JComboBox();
		cmbxLibros.setBounds(958, 156, 154, 22);
		cmbxLibros.addItem("-Listado de libros-");
		add(cmbxLibros);
		
		cmbxSocios = new JComboBox();
		cmbxSocios.setBounds(958, 100, 154, 22);
		cmbxSocios.addItem("-Listado de Socios-");
		add(cmbxSocios);
		
		rdbtnMostrarResueltas = new JRadioButton("Mostrar resueltas");
		rdbtnMostrarResueltas.setOpaque(false);
		rdbtnMostrarResueltas.setForeground(new Color(255, 255, 255));
		rdbtnMostrarResueltas.setBounds(851, 232, 214, 23);
		add(rdbtnMostrarResueltas);
		
		rdbtnMostrarNoResueltas = new JRadioButton("Mostrar no resueltas");
		rdbtnMostrarNoResueltas.setOpaque(false);
		rdbtnMostrarNoResueltas.setForeground(new Color(255, 255, 255));
		rdbtnMostrarNoResueltas.setBounds(851, 258, 214, 23);
		add(rdbtnMostrarNoResueltas);
		
		rdbtnMostrarTodas = new JRadioButton("Mostrar todas");
		rdbtnMostrarTodas.setOpaque(false);
		rdbtnMostrarTodas.setForeground(new Color(255, 255, 255));
		rdbtnMostrarTodas.setSelected(true);
		rdbtnMostrarTodas.setBounds(851, 284, 214, 23);
		add(rdbtnMostrarTodas);
		
		ButtonGroup grupoRadio = new ButtonGroup();
		grupoRadio.add(rdbtnMostrarTodas);
		grupoRadio.add(rdbtnMostrarResueltas);
		grupoRadio.add(rdbtnMostrarNoResueltas);
		
		JButton btnLimpiarCampos = new JButton("Limpiar campos");
		btnLimpiarCampos.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnLimpiarCampos.setBounds(658, 48, 154, 23);
		add(btnLimpiarCampos);
		
		btnResolver = new JButton("Resuelta / no resuelta");
		btnResolver.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnResolver.setEnabled(false);
		btnResolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modeloTablaIncidencias.setRowCount(0);
				resolverNoResolver();
				rellenarALPorParametros();
			}
		});
		btnResolver.setBounds(245, 47, 214, 23);
		add(btnResolver);
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rellenarALPorParametros();
				
				
			}
		});
		btnFiltrar.setBounds(515, 47, 89, 23);
		add(btnFiltrar);
		
		JLabel lblSocios = new JLabel("Incidencias");
		lblSocios.setForeground(new Color(255, 255, 255));
		lblSocios.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblSocios.setBounds(10, 36, 184, 45);
		add(lblSocios);

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
	
	public void cogerCamposParaResolver() {
		int filaSeleccionada = table.getSelectedRow();
		int columnaSeleccionada = table.getSelectedColumn();
		
		if(filaSeleccionada != -1 && columnaSeleccionada != -1) {
			idIncidenciaSeleccionada = (int) table.getValueAt(filaSeleccionada, 0);
			estadoActualIncidencia = (String) table.getValueAt(filaSeleccionada, 5);
		}
	}
	
	public void resolverNoResolver() {
		if(estadoActualIncidencia.equalsIgnoreCase("No resuelta")) {
			con.cambiarEstadoIncidenciaAResuelta(idIncidenciaSeleccionada);
		}else if(estadoActualIncidencia.equalsIgnoreCase("Resuelta")) {
			con.cambiarEstadoIncidenciaANoResuelta(idIncidenciaSeleccionada);
		}
	}
	
	public void rellenarALPorParametros() {
		int id_socio = 0;
		int id_libro = 0;
		String estado = null;
		if(cmbxSocios.getSelectedIndex() != 0) {
			String[] idYNombre = (cmbxSocios.getSelectedItem().toString().split(" - "));
			id_socio = Integer.parseInt(idYNombre[0]);
		}
		if(cmbxLibros.getSelectedIndex() != 0) {
			String[] idYTitulo = (cmbxLibros.getSelectedItem().toString().split(" - "));
			id_libro = Integer.parseInt(idYTitulo[0]);
		}
		if(rdbtnMostrarTodas.isSelected()) {
			estado = "todas";
		}else if(rdbtnMostrarResueltas.isSelected()) {
			estado = "resueltas";
		}else if(rdbtnMostrarNoResueltas.isSelected()) {
			estado = "no resueltas";
		}
		
		modeloTablaIncidencias.setRowCount(0);
		
		con = new ConectorBBDD();
		for(Incidencias i : con.tablaIncidenciasSegunParametros(id_socio, id_libro, estado)) {
			modeloTablaIncidencias.addRow(new Object[] {
					i.getId(),
					i.getId_socio(),
					i.getNombre_socio(),
					i.getId_libro(),
					i.getNombre_libro(),
					i.getEstadoIncidencia(),
					i.getTexto_incidencias(),
			});
		}
	}
	
	@Override
	public void addNotify() {
		// TODO Auto-generated method stub
		super.addNotify();
		modeloTablaIncidencias.setRowCount(0);
		rellenarTablaAlPrincipio();
		rellenarComBoxes();
	}
	
	public void rellenarComBoxes() {
		data = new DataMetodos();
		con = new ConectorBBDD();
		for(Libro l : data.LeerTablaLibro()) {
			cmbxLibros.addItem(l.getId() + " - " + l.getTitulo());
		}
		for(Socio s : con.consultarSocios()) {
			cmbxSocios.addItem(s.getId() + " - " + s.getNombre());
		}
	}
	
	public void rellenarTablaAlPrincipio() {
		con = new ConectorBBDD();
		for(Incidencias i : con.consultarTodasIncidencias()) {
			modeloTablaIncidencias.addRow(new Object[] {
					i.getId(),
					i.getId_socio(),
					i.getNombre_socio(),
					i.getId_libro(),
					i.getNombre_libro(),
					i.getEstadoIncidencia(),
					i.getTexto_incidencias(),
			});
		}
	}
}
